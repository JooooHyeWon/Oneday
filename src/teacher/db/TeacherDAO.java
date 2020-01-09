package teacher.db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

import userInfo.db.UserInfo;

public class TeacherDAO {

	DataSource ds;
	Connection con;
	PreparedStatement pstmt;
	ResultSet rs;
	int result;

	public TeacherDAO() {
		try {
			Context init = new InitialContext();
			ds = (DataSource) init.lookup("java:comp/env/jdbc/OracleDB");
		} catch (Exception ex) {
			System.out.println("DB 연결 실패 : " + ex);
		}
	}
	
	public int idcheck(String id) {
		result=0;
		try {
			String sql="select tea_id from teacher where tea_id = ? ";
			con=ds.getConnection();
			pstmt=con.prepareStatement(sql);
			pstmt.setString(1, id);
			rs=pstmt.executeQuery();
			
			if(rs.next()) {
				result=1;
			}else {
				result=-1;
			}
		}catch(Exception e) {
			e.printStackTrace();
			System.out.println("Teacher idcheck()에러 입니다.");
		
		}finally {
			try {
				if (pstmt != null)
					pstmt.close();
			} catch (SQLException ex) {
				ex.printStackTrace();
			}
			try {
				if (con != null)
					con.close();
			} catch (SQLException ex) {
				ex.printStackTrace();
			}
		}
		return result;
	}
	
	public int addteacher(Teacher teacher) {
		try {
			String sql="insert into teacher values(?,?,?,?,?,?)";
			con=ds.getConnection();
			pstmt=con.prepareStatement(sql);
			pstmt.setString(1, teacher.getTea_id());
			pstmt.setString(2, teacher.getTea_pass());
			pstmt.setString(3, teacher.getTea_name());
			pstmt.setString(4, teacher.getTea_phone());
			pstmt.setString(5, teacher.getTea_email());
			pstmt.setString(6, teacher.getUser_type());
			
			result=pstmt.executeUpdate();
			
		}catch(java.sql.SQLIntegrityConstraintViolationException e) {
			result=-1;
			System.out.println("아이디가 이미 존재합니다.");
		}catch(Exception e) {
			e.printStackTrace();
		
		}finally {
			try {
				if (pstmt != null)
					pstmt.close();
			} catch (SQLException ex) {
				ex.printStackTrace();
			}
			try {
				if (con != null)
					con.close();
			} catch (SQLException ex) {
				ex.printStackTrace();
			}
		}
		return result;	
	}
	
	public int login(String id,String password){
		try {
			con=ds.getConnection();
			System.out.println("getConnection");
			
			String sql="select * from teacher where tea_id = ? ";
			pstmt=con.prepareStatement(sql);
			pstmt.setString(1,id);
			
			rs=pstmt.executeQuery();
			
			if(rs.next()) {
				if(rs.getString(2).equals(password)) {
					result=1;
					System.out.println("아이디 비밀번호 모두 일치");
				}else {
					result=0;
					System.out.println("비밀번호가 일치하지 않습니다..");
				}
			}else {
				result=-1;
				System.out.println("아이디가 존재하지 않습니다.");
			}
		}catch(Exception e) {
			e.printStackTrace();
		
		}finally {
			try {
				if (pstmt != null)
					pstmt.close();
			} catch (SQLException ex) {
				ex.printStackTrace();
			}
			try {
				if (con != null)
					con.close();
			} catch (SQLException ex) {
				ex.printStackTrace();
			}
		}
		return result;	
	}
	
	public int getlesson_num(String id) {
		int num=0;
		String sql="select count(*) from lesson where tea_id= ?";
		try {
			con=ds.getConnection();
			pstmt=con.prepareStatement(sql);
			pstmt.setString(1, id);
			rs=pstmt.executeQuery();
			
			if(rs.next()) {
				num=rs.getInt(1);
			}
		}catch(Exception e) {
			e.printStackTrace();
		
		}finally {
			try {
				if (pstmt != null)
					pstmt.close();
			} catch (SQLException ex) {
				ex.printStackTrace();
			}
			try {
				if (con != null)
					con.close();
			} catch (SQLException ex) {
				ex.printStackTrace();
			}
		}
		return num;
	}

	public Teacher info(String id) {
		Teacher teacher=new Teacher();
		try {
			con=ds.getConnection();
			String sql="select * from teacher where tea_id = ?";
			pstmt=con.prepareStatement(sql);
			pstmt.setString(1, id);
			
			rs=pstmt.executeQuery();
			
			while(rs.next()) {
				teacher.setTea_id(rs.getString(1));
				teacher.setTea_pass(rs.getString(2));
				teacher.setTea_name(rs.getString(3));
				teacher.setTea_phone(rs.getString(4));
				teacher.setTea_email(rs.getString(5));
				
			}
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
		return teacher;
		}
	
		
		
	public int infoUpdate(String id,String tea_name,String tea_phone,String tea_email) {
		try {
			con=ds.getConnection();
			String sql="update teacher set tea_name = ? , tea_phone = ? , tea_email = ? where tea_id = ? ";
			pstmt=con.prepareStatement(sql);
			pstmt.setString(1, tea_name);
			pstmt.setString(2, tea_phone);
			pstmt.setString(3, tea_email);
			pstmt.setString(4, id);
			
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
	
	public int passcheck(String id,String origin_pass) {
		try {
			con=ds.getConnection();
			String sql="select tea_pass from teacher where tea_id = ?";
			pstmt=con.prepareStatement(sql);
			pstmt.setString(1, id);
			
			rs=pstmt.executeQuery();
			
			while(rs.next()) {
				if(rs.getString(1).equals(origin_pass)) {
					result=1;
				}else {
					result=0;
				}
			}
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
	
	
	public int passupdate(String id,String alter_pass) {
		try {
			con=ds.getConnection();
			String sql="update teacher set tea_pass = ? where tea_id = ? ";
			pstmt=con.prepareStatement(sql);
			pstmt.setString(1, alter_pass);
			pstmt.setString(2, id);
			
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
	
	
	public int isTeacher(String id) {
		int result = 0;
		
		String sql = "select tea_id from teacher where tea_id = ?";
		
		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(sql);
			
			pstmt.setString(1, id);
			rs = pstmt.executeQuery();
			
			if(rs.next() == true) { 
				result = 0;
			} else {
				result = -1;
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
		return result;
	}

	}

