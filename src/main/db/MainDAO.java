package main.db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import lesson.db.Lesson;
import teacher.db.Teacher;
import userInfo.db.UserInfo;

public class MainDAO {
	private DataSource ds; 
	private Connection con;
	private PreparedStatement pstmt;
	private ResultSet rs;	
	
	public MainDAO() {
		try {
			Context initContext = new InitialContext();
			Context envContext  = (Context)initContext.lookup("java:/comp/env"); 
			ds = (DataSource)envContext.lookup("jdbc/OracleDB");
		} catch (NamingException e) {
			e.printStackTrace();
		} 
	}	
	
	private void close() {
		try {
			if (rs != null) {
				rs.close();
				rs = null;
			}
			if (pstmt != null) {
				pstmt.close();
				pstmt = null;
			}
			if (con != null) {
				con.close();
				con = null;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	// 페이징용 리스트 카운트 
	public int getListCount(String type) {
		int x = 0;
		System.out.println("리스트카운트 "+type);
		String sql="";
		if(type.equals("userInfo")) {
			sql = "select count(*) from " + type +" where user_id != 'admin' ";
		}else {
		 sql = "select count(*) from " + type;
		}
		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(sql);
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				x = rs.getInt(1);
			}			
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("getListCount() 에러 : "+e);
		}finally {
			close();
		}
		return x;
	}
	
	// 수업 목록 불러오기
	public List<Lesson> getLessonList(int page, int limit) {
		List<Lesson> list = new ArrayList<Lesson>();
		try {
			String sql = "select * from(select b.*, rownum rnum "
					+ 				"from(select * from lesson "
					+ 					" order by lesson_code) b"
					+ 				")"
					+ "where rnum>=? and rnum<=?";
			con = ds.getConnection();
			pstmt = con.prepareStatement(sql);	
			
			int startrow = (page -1) * limit + 1;
			int endrow = startrow + limit -1;
			pstmt.setInt(1, startrow);
			pstmt.setInt(2, endrow);
			
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				Lesson lesson = new Lesson();
				lesson.setLesson_code(rs.getInt("lesson_code"));
				lesson.setLesson_title(rs.getString("lesson_title"));
				lesson.setTea_id(rs.getString("tea_id"));
				list.add(lesson);				
			}			
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			close();
		}		
		return list;
	}

	// 강사 목록 불러오기
	public List<Teacher> getTeacherList(int page, int limit) {
		List<Teacher> list = new ArrayList<Teacher>();
		try {
			String sql = "select * from(select b.*, rownum rnum "
					+ 				"from(select * from teacher "
					+ 					") b"
					+ 				")"
					+ "where rnum>=? and rnum<=?";
			con = ds.getConnection();
			pstmt = con.prepareStatement(sql);	
			
			int startrow = (page -1) * limit + 1;
			int endrow = startrow + limit -1;
			pstmt.setInt(1, startrow);
			pstmt.setInt(2, endrow);
			
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				Teacher teacher = new Teacher();
				teacher.setTea_id(rs.getString("tea_id"));
				teacher.setTea_pass(rs.getString("tea_pass"));
				list.add(teacher);				
			}			
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			close();
		}		
		return list;
	}
	
	// 회원 목록 불러오기
	public List<UserInfo> getUserList(int page, int limit) {
		List<UserInfo> list = new ArrayList<UserInfo>();
		try {
			String sql = "select * from (select b.*, rownum rnum "
					+ 				"from(select * from userInfo "
					+ 					"where user_id != 'admin' ) b"
					+ 				")"
					+ "where rnum>=? and rnum<=?";
			con = ds.getConnection();
			pstmt = con.prepareStatement(sql);	
			
			int startrow = (page -1) * limit + 1;
			int endrow = startrow + limit -1;
			pstmt.setInt(1, startrow);
			pstmt.setInt(2, endrow);
			
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				UserInfo user = new UserInfo();
				user.setUser_id(rs.getString("user_id"));
				user.setUser_pass(rs.getString("user_pass"));
				user.setUser_name(rs.getString("user_name"));
				list.add(user);				
			}			
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			close();
		}		
		return list;
	}
	
	// 관리자 - 삭제
	public int delete(String code, String type) {
		int result=0;
		
		try {
			con = ds.getConnection();
			String sql="";
			
			if(type.equals("lesson")) {
				sql = "delete lesson " 
						+ "where lesson_code =?";
				pstmt = con.prepareStatement(sql);
				pstmt.setInt(1, Integer.parseInt(code));
			
			}else if(type.equals("teacher")) {
				sql = "delete teacher " 
						+ "where tea_id =?";
				pstmt = con.prepareStatement(sql);
				pstmt.setString(1, code);
			
			}else if(type.equals("userInfo")) {
				sql = "delete userInfo " 
						+ "where user_id =?";
				pstmt = con.prepareStatement(sql);
				pstmt.setString(1, code);
			}
			
			result = pstmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			close();
		}
		return result;
	}
}
