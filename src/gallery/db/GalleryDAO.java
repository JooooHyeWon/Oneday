package gallery.db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

public class GalleryDAO {
	DataSource ds;
	Connection con;
	PreparedStatement pstmt;
	ResultSet rs;
	int result;

	public GalleryDAO() {
		try {
			Context init = new InitialContext();
			ds = (DataSource) init.lookup("java:comp/env/jdbc/OracleDB");
		} catch (Exception ex) {
			System.out.println("DB 연결 실패 : " + ex);
		}
	}
	
	
	public int addgallery(int lesson_code,int pic_num,String file) {
		try {
			con=ds.getConnection();
			String sql="insert into gallery values( ? , ? , ? )";
			pstmt=con.prepareStatement(sql);
			pstmt.setInt(1, lesson_code);
			pstmt.setInt(2, pic_num);
			pstmt.setString(3, file);
			
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
	
	public String getgallerylist(int lesson_code){
		String result="";
		try {
			con=ds.getConnection();
			String sql="select pic_name from gallery where lesson_code = ? and pic_num = 1 ";
			pstmt=con.prepareStatement(sql);
			pstmt.setInt(1, lesson_code);
			rs=pstmt.executeQuery();
			
			if(rs.next()) {
				result=rs.getString(1);
			}
			
		}catch (Exception e) {
			e.printStackTrace();
			System.out.println("getgallerylist() 에러입니다..");
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
		
	
	//갤러리 정보 가져오는 메소드
		public List<Gallery> gallery_info(int lesson_code){
			
			List<Gallery> glist = new ArrayList<Gallery>();
			
			try {
				con = ds.getConnection();
				
				String sql = "select pic_name from gallery where lesson_code = ? order by pic_num";
				
				pstmt = con.prepareStatement(sql);
				pstmt.setInt(1, lesson_code);
				rs = pstmt.executeQuery();
				
				while (rs.next()) {
					Gallery g = new Gallery();
					g.setPic_name(rs.getString(1));
					glist.add(g);
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
			
			return glist;
		}
		
		
		//메인사진 가져오는 메소드 (pic_num = 1)
		public String main_pic (int lesson_code) {
			
			String g = null;
			
			try {
				con = ds.getConnection();
				String sql = "select pic_name from gallery where lesson_code = ? and pic_num = 1 ";
				pstmt = con.prepareStatement(sql);
				
				pstmt.setInt(1, lesson_code);
				
				rs = pstmt.executeQuery();
				
				if(rs.next()) {
					
					g = rs.getString("pic_name");
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
			
			return g;
		}

}
