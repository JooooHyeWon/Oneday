package lesson.db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

public class LessonDAO {
	DataSource ds;
	Connection con;
	PreparedStatement pstmt;
	ResultSet rs;
	int result;

	public LessonDAO() {
		try {
			Context init = new InitialContext();
			ds = (DataSource) init.lookup("java:comp/env/jdbc/OracleDB");
		} catch (Exception ex) {
			System.out.println("DB 연결 실패 : " + ex);
		}
	}
	
	public int addlesson(Lesson lesson) {
		try {
			con=ds.getConnection();
			String sql="insert into lesson "
					+ "values( lesson_seq.nextval , ? ,? , ?, ?, ?, ?, 0 ,? ,?,?) ";
			pstmt=con.prepareStatement(sql);
			pstmt.setString(1, lesson.getTea_id());
			pstmt.setString(2, lesson.getLesson_type());
			pstmt.setString(3, lesson.getLesson_title());
			pstmt.setString(4, lesson.getLesson_price());
			pstmt.setString(5, lesson.getLesson_location());
			pstmt.setString(6, lesson.getLesson_content());
			pstmt.setString(7, lesson.getLesson_chat());
			pstmt.setString(8, lesson.getTea_info());
			pstmt.setString(9, lesson.getLesson_info());
			
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
	
	public int getlesson_code() {
		try {
			con=ds.getConnection();
			String sql="select lesson_seq.currval from dual ";
			pstmt=con.prepareStatement(sql);
			
			rs=pstmt.executeQuery();
			
			if(rs.next()) {
				result=rs.getInt(1);
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
	
	
	public List<Lesson> getlessonlist(String tea_id){
		List<Lesson> list=new ArrayList<Lesson>();
		try {
			con=ds.getConnection();
			String sql="select * from lesson where tea_id = ? order by lesson_code desc";
			pstmt=con.prepareStatement(sql);
			pstmt.setString(1, tea_id);
			rs=pstmt.executeQuery();
			
			while(rs.next()) {
				Lesson lesson=new Lesson();
				lesson.setLesson_code(rs.getInt("lesson_code"));
				lesson.setTea_id(rs.getString("tea_id"));
				lesson.setLesson_type(rs.getString("lesson_type"));
				lesson.setLesson_title(rs.getString("lesson_title"));
				lesson.setLesson_price(rs.getString("lesson_price"));
				lesson.setLesson_location(rs.getString("lesson_location"));
				lesson.setLesson_content(rs.getString("lesson_content"));
				lesson.setBookmark(rs.getInt("bookmark"));
				lesson.setLesson_chat(rs.getString("lesson_chat"));
				lesson.setTea_info(rs.getString("tea_info"));
				lesson.setLesson_info(rs.getString("lesson_info"));
				list.add(lesson);
			}
		
			}catch (Exception e) {
				e.printStackTrace();
				System.out.println("getlessonlist() 에러입니다.");
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
	
	public int delete_lesson(int lesson_code) {
		try {
			con=ds.getConnection();
			String sql="delete lesson where lesson_code = ? ";
			pstmt=con.prepareStatement(sql);
			pstmt.setInt(1, lesson_code);
			
			result=pstmt.executeUpdate();
			
		}catch (Exception e) {
			e.printStackTrace();
			System.out.println("delete_lesson() 에러입니다.");
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
	
	public int getListCount(String id) {
		int x=0;
		try {
			con=ds.getConnection();
			String sql="select count(*) from lesson where tea_id = ? ";
			pstmt=con.prepareStatement(sql);
			pstmt.setString(1, id);
			
			rs=pstmt.executeQuery();
			if(rs.next()) {
				x=rs.getInt(1);
			}
		}catch(Exception e) {
			System.out.println("getListCount() 에러 : "+e);
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
		
		return x;
	}
	
	public List<Lesson> getlessonlist(String tea_id,int page,int limit){
		List<Lesson> list=new ArrayList<Lesson>();
		
		String sql="select * from (select rownum rnum, l.* "
				+"from (select * from lesson where tea_id = ? order by lesson_code desc ) l ) "
				+"where rnum >= ? and rnum < = ? ";
		int startrow=(page-1)*limit+1;
		int endrow=startrow+limit-1;
		
		try {
			con=ds.getConnection();
			pstmt=con.prepareStatement(sql);
			pstmt.setString(1, tea_id);
			pstmt.setInt(2, startrow);
			pstmt.setInt(3, endrow);
			rs=pstmt.executeQuery();
			
			while(rs.next()) {
				Lesson lesson=new Lesson();
				lesson.setLesson_code(rs.getInt("lesson_code"));
				lesson.setTea_id(rs.getString("tea_id"));
				lesson.setLesson_type(rs.getString("lesson_type"));
				lesson.setLesson_title(rs.getString("lesson_title"));
				lesson.setLesson_price(rs.getString("lesson_price"));
				lesson.setLesson_location(rs.getString("lesson_location"));
				lesson.setLesson_content(rs.getString("lesson_content"));
				lesson.setBookmark(rs.getInt("bookmark"));
				lesson.setLesson_chat(rs.getString("lesson_chat"));
				lesson.setTea_info(rs.getString("tea_info"));
				lesson.setLesson_info(rs.getString("lesson_info"));
				list.add(lesson);
			}
		}catch (Exception e) {
			e.printStackTrace();
			System.out.println("getlessonlist() 에러입니다.");
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
	
	// 수업 정보 가져오는 메소드
		public Lesson lesson_info(int lesson_code) {
			
			Lesson l = null;
			
			try {
				con = ds.getConnection();
				
				String sql = "select lesson_code, lesson_title, lesson_price, lesson_location, lesson_content, lesson_chat, tea_info "
						+ "	  from lesson where lesson_code = ?";
				pstmt = con.prepareStatement(sql);
				
				pstmt.setInt(1, lesson_code);
				rs = pstmt.executeQuery();
				
				if(rs.next()) {
					l = new Lesson();
					l.setLesson_code(rs.getInt(1));			//code
					l.setLesson_title(rs.getString(2));		//title		
					l.setLesson_price(rs.getString(3));		//price
					l.setLesson_location(rs.getString(4));	//location
					l.setLesson_content(rs.getString(5));	//content
					l.setLesson_chat(rs.getString(6));		//chat
					l.setTea_info(rs.getString(7));			//tea_info
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
			
			return l;
		}
		
		public int getlessoncount(String id) {
			int x=0;
			try {
				con=ds.getConnection();
				String sql="select count(*) from lesson where tea_id = ? ";
				pstmt=con.prepareStatement(sql);
				pstmt.setString(1, id);
				rs=pstmt.executeQuery();
				
				if(rs.next()) {
					x=rs.getInt(1);
				}
			} catch (Exception e) {
				e.printStackTrace();
				System.out.println("getlessoncount() 에러입니다.");
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
			
			return x;
		}
	}
	
	