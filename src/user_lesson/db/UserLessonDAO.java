package user_lesson.db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

public class UserLessonDAO {
	DataSource ds;
	Connection con;
	PreparedStatement pstmt;
	ResultSet rs;
	int result;

	public UserLessonDAO() {
		try {
			Context init = new InitialContext();
			ds = (DataSource) init.lookup("java:comp/env/jdbc/OracleDB");
		} catch (Exception ex) {
			System.out.println("DB 연결 실패 : " + ex);
		}
	}
	
	public List<UserLesson> getuser_lessonlist(int lesson_code){
		List<UserLesson> list=new ArrayList<UserLesson>();
		try {
			con=ds.getConnection();
			String sql="select * from user_lesson where lesson_code = ? ";
			pstmt=con.prepareStatement(sql);
			pstmt.setInt(1, lesson_code);
			
			rs=pstmt.executeQuery();
			
			while(rs.next()) {
				UserLesson userlesson=new UserLesson();
				userlesson.setLesson_code(rs.getInt("lesson_code"));
				userlesson.setUser_id(rs.getString("user_id"));
				userlesson.setLesson_de_code(rs.getString("lesson_de_code"));
				list.add(userlesson);
			}
		}catch (Exception e) {
			e.printStackTrace();
			System.out.println("getuser_lessonlist() 에러입니다.");
		} finally {
			try {
				if (pstmt != null)
					pstmt.close();
			} catch (SQLException e) {
				System.out.println(e.getMessage());
			}
			try {
				if (con != null)
					con.close();
			} catch (SQLException e) {
				System.out.println(e.getMessage());
			}
		}
		return list;
	}
	
	// 수강신청 하면 수강내역 테이블에 입력되는 메소드
		public int register(UserLesson ul) {
			int result = 0;
			
			try {
				
				con = ds.getConnection();
				System.out.println("get Connection");
				
				String sql = "insert into user_lesson values(?, ?, ?)";
				
				pstmt = con.prepareStatement(sql);
				
				pstmt.setString(1, ul.getUser_id());
				pstmt.setInt(2, ul.getLesson_code());
				pstmt.setString(3, ul.getLesson_de_code());
				
				result = pstmt.executeUpdate();
			} catch (java.sql.SQLIntegrityConstraintViolationException e) {
				
				result = -1;
				System.out.println("이미 수강신청한 회원입니다.");
				
			} catch (Exception e) {
				e.printStackTrace();
				
			} finally {
				try {
					if (pstmt != null)
						pstmt.close();
				} catch (SQLException e) {
					System.out.println(e.getMessage());
				}
				try {
					if (con != null)
						con.close();
				} catch (SQLException e) {
					System.out.println(e.getMessage());
				}
			}
			return result;
		}
		
		
		//수강내역 목록에 해당 아이디 있는지 찾는 메소드
		//아이디 발견되면 이미 수강신청 하신 수업입니다 alert
		public int isId (String user_id, String lesson_de_code) {
			int isid = 0;
			
			try {
				con = ds.getConnection();
				System.out.println("get Connection");
				
				String sql = "select user_id from user_lesson where user_id = ? and lesson_de_code = ?";
				pstmt = con.prepareStatement(sql);
				
				pstmt.setString(1, user_id);
				pstmt.setString(2, lesson_de_code);
				rs = pstmt.executeQuery();
				
				if(rs.next()) {
					isid = 1;	//이미 수강중인 아이디 (신청 불가능)
				} else {
					isid = -1;	//수강중이지 않은 아이디 (신청 가능)
				}
			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				try {
					if (pstmt != null)
						pstmt.close();
				} catch (SQLException e) {
					System.out.println(e.getMessage());
				}
				try {
					if (con != null)
						con.close();
				} catch (SQLException e) {
					System.out.println(e.getMessage());
				}
			}
			return isid;
		}
	
}
	

