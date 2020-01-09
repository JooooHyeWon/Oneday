package schedule.db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

public class ScheduleDAO {
	DataSource ds;
	Connection con;
	PreparedStatement pstmt;
	ResultSet rs;
	int result;

	public ScheduleDAO() {
		try {
			Context init = new InitialContext();
			ds = (DataSource) init.lookup("java:comp/env/jdbc/OracleDB");
		} catch (Exception ex) {
			System.out.println("DB 연결 실패 : " + ex);
		}
	}
	
	public int addschedule(Schedule schedule) {
		result=0;
		try {
			
			con=ds.getConnection();
			String sql="insert into schedule "
					+ " values ( ? , ?, ?, ?, ? , 0 , ?)  ";
			pstmt=con.prepareStatement(sql);
			pstmt.setInt(1, schedule.getLesson_code());
			pstmt.setString(2, schedule.getLesson_date());
			pstmt.setString(3, schedule.getLesson_start());
			pstmt.setString(4, schedule.getLesson_end());
			pstmt.setInt(5, schedule.getMax_user());
			pstmt.setString(6, schedule.getLesson_de_code());
			
			result=pstmt.executeUpdate();
			
		}catch (Exception e) {
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
	
	public List<Schedule> getschedulelist(int lesson_code){
		List<Schedule> list=new ArrayList<Schedule>();
		try {
			con=ds.getConnection();
			String sql="select * from schdule where lesson_code = ? ";
			pstmt=con.prepareStatement(sql);
			pstmt.setInt(1, lesson_code);
			rs=pstmt.executeQuery();
			
			while(rs.next()) {
				Schedule schedule=new Schedule();
				schedule.setLesson_code(rs.getInt("lesson_code"));
				schedule.setLesson_date(rs.getString("lesson_date"));
				schedule.setLesson_start(rs.getString("lesson_start"));
				schedule.setLesson_end(rs.getString("lesson_end"));
				schedule.setMax_user(rs.getInt("max_user"));
				schedule.setUser_count(rs.getInt("user_count"));
				String lesson_de_code=rs.getString("lesson_code")+"-"+rs.getString("lesson_date");
				schedule.setLesson_de_code(lesson_de_code);
				list.add(schedule);
				}
			
			}catch (Exception e) {
				e.printStackTrace();
				System.out.println("getschedulelist() 에러입니다.");
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
	
	//수업 스케줄 정보 가져오는 메소드
		public List<Schedule> schedule_info(int lesson_code) {
			
			List<Schedule> slist = new ArrayList<Schedule>();
			
			try {
				con = ds.getConnection();
				
				String sql = "select lesson_date, lesson_start, lesson_end, max_user, user_count "
						+ 	 " from schedule where lesson_code = ? order by lesson_date";
				pstmt = con.prepareStatement(sql);
				
				pstmt.setInt(1, lesson_code);
				rs = pstmt.executeQuery();
				
				while (rs.next()) {
					Schedule s = new Schedule();
					s.setLesson_date(rs.getString(1));
					s.setLesson_start(rs.getString(2));
					s.setLesson_end(rs.getString(3));
					s.setMax_user(rs.getInt(4));
					s.setUser_count(rs.getInt(5));
					slist.add(s);
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
			return slist;
		}
		
		
		 //스케줄 테이블의 user_count 속성 update
		public int cnt_update(String lesson_de_code) {
			int result = 0;
			
			try {
				con = ds.getConnection();
				String sql = "update schedule set user_count = (select count(*) from user_lesson where lesson_de_code = ?) where lesson_de_code = ?";
						
				pstmt = con.prepareStatement(sql);
				pstmt.setString(1, lesson_de_code);
				pstmt.setString(2, lesson_de_code);
				
				result = pstmt.executeUpdate();
				
				if(result == 1) {
					System.out.println("수강인원이 한 명 늘었습니다");
				}
				
			} catch (java.sql.SQLIntegrityConstraintViolationException e) {
				result = -1;
				System.out.println("해당 수업은 마감 되었습니다.");
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
		
		
		//특정 수업의 max_user와 user_count 값 알아오는 메소드
		public Schedule thislesson(String lesson_de_code) {
			Schedule s = null;
			
			try {
				con = ds.getConnection();
				
				String sql = "select max_user, user_count from schedule where lesson_de_code = ?";
				
				pstmt = con.prepareStatement(sql);
				
				pstmt.setString(1, lesson_de_code);
				rs = pstmt.executeQuery();
				
				if(rs.next()) {
					s = new Schedule();
					s.setMax_user(rs.getInt(1));
					s.setUser_count(rs.getInt(2));
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
			return s;
			
		}

		public String getLesson_date(String lesson_de_code) {
			String lesson_date="";
			try {
				con = ds.getConnection();
				
				String sql = "select lesson_date from schedule where lesson_de_code = ?";
				
				pstmt = con.prepareStatement(sql);
				
				pstmt.setString(1, lesson_de_code);
				rs = pstmt.executeQuery();
				
				if(rs.next()) {
					lesson_date=rs.getString(1);
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
			return lesson_date;
			
		}
}
