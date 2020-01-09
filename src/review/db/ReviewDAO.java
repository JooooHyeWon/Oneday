package review.db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

public class ReviewDAO {
	DataSource ds;
	Connection con;
	PreparedStatement pstmt;
	ResultSet rs;
	int result;

	public ReviewDAO() {
		try {
			Context init = new InitialContext();
			ds = (DataSource) init.lookup("java:comp/env/jdbc/OracleDB");
		} catch (Exception ex) {
			System.out.println("DB 연결 실패 : " + ex);
		}
	}
	
	// 리뷰 가져오는 메소드
		public List<Review> review_info(int lesson_code) {

			List<Review> rlist = new ArrayList<Review>();

			try {
				con = ds.getConnection();

				String sql = "select user_id, review_date, review_content, review_star, review_code "
						+ "	  from review where lesson_code = ?";

				pstmt = con.prepareStatement(sql);

				pstmt.setInt(1, lesson_code);
				rs = pstmt.executeQuery();

				while (rs.next()) {
					Review r = new Review();
					r.setUser_id(rs.getString(1));
					r.setReview_date(rs.getDate(2));
					r.setReview_content(rs.getString(3));
					r.setReview_star(rs.getInt(4));
					r.setReview_code(rs.getInt(5));
					rlist.add(r);
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
			return rlist;
		}

		
		//리뷰 개수 구하는 메소드
		public int review_cnt(int lesson_code) {

			try {
				con = ds.getConnection();

				String sql = "select count(*) from review where lesson_code = ?";
				pstmt = con.prepareStatement(sql);

				pstmt.setInt(1, lesson_code);
				rs = pstmt.executeQuery();

				while (rs.next()) {
					result = rs.getInt(1);
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
		
		
		//리뷰 작성자 정보
		public List<Review> review_writer(int lesson_code){
			List<Review> rlist = new ArrayList<Review>();

			try {
				con = ds.getConnection();

				String sql = "select user_id "
						+ "	  from review where lesson_code = ?";

				pstmt = con.prepareStatement(sql);

				pstmt.setInt(1, lesson_code);
				rs = pstmt.executeQuery();

				while (rs.next()) {
					Review r = new Review();
					r.setUser_id(rs.getString(1));
					rlist.add(r);
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
			return rlist;
		}
		
	
		
		
		//리뷰 페이지 메소드
		public List<Review> getReviewList(int page, int limit, int lesson_code, String sort){
			
			String review_sort = "";
			if(sort.equals("rv_recent")) {
				review_sort = "select * "
						+ "from (select rownum rnum, b.* "
						+		" from (select * from review where lesson_code = ? "
						+				"order by review_date desc) b"
						+		") "
						+ "where rnum >= ? and rnum <= ?";
			} else {
				review_sort = "select * "
						+ "from (select rownum rnum, b.* "
						+		" from (select * from review where lesson_code = ? "
						+				"order by review_star desc) b"
						+		") "
						+ "where rnum >= ? and rnum <= ?";
			}
			
			List<Review> list = new ArrayList<Review>();
			
			int startrow = (page-1) * limit + 1;
			int endrow = startrow + limit - 1;
			
			try {
				con = ds.getConnection();
				pstmt = con.prepareStatement(review_sort);
				
				pstmt.setInt(1, lesson_code);
				pstmt.setInt(2, startrow);
				pstmt.setInt(3, endrow);
				
				rs = pstmt.executeQuery();
				
				while(rs.next()) {
					
					Review review = new Review();
					review.setReview_code(rs.getInt("review_code"));
					review.setReview_content(rs.getString("review_content"));
					review.setReview_date(rs.getDate("review_date"));
					review.setReview_star(rs.getInt("review_star"));
					review.setUser_id(rs.getString("user_id"));
					list.add(review);
				}						
			} catch (Exception e) {
				e.printStackTrace();
				System.out.println("getReviewList() 에러 : " + e);
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
		
		
		
		public boolean isReviewWriter(int num, String pass) {
			
			String board_sql = "select review_code, user_pass, review.user_id "
					+ "			from review, userInfo "
					+ "			where review.user_id = userInfo.user_id "
					+ "			and review_code = ?";
			
			try {
				con = ds.getConnection();
				pstmt = con.prepareStatement(board_sql);
				pstmt.setInt(1, num);
				rs = pstmt.executeQuery();
				
				if(rs.next())
					if(pass.equals(rs.getString(2))) {
						return true;
					}
			} catch (Exception e) {
				e.printStackTrace();
				System.out.println("isReviewWriter() 에러 : " + e);
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
			
			return false;
		}
		
		
		
	public boolean reviewDelete(int review_code) {
		int re_del = 0;

		try {

			String sql = "delete from review where review_code = ?";

			con = ds.getConnection();
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, review_code);

			re_del = pstmt.executeUpdate();

			if (re_del != 0) {
				return true;
			}

		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("reviewDelete 에러 : " + e);
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

		return false;

	}
	
	
	//리뷰 페이지 메소드
			public List<Review> getReviewList(int page, int limit, int lesson_code){
				
				String review_list = "select * "
						+ "from (select rownum rnum, b.* "
						+		" from (select * from review where lesson_code = ? "
						+				"order by review_date desc) b"
						+		") "
						+ "where rnum >= ? and rnum <= ?";
				
				List<Review> list = new ArrayList<Review>();
				
				int startrow = (page-1) * limit + 1;
				int endrow = startrow + limit - 1;
				
				try {
					con = ds.getConnection();
					pstmt = con.prepareStatement(review_list);
					
					pstmt.setInt(1, lesson_code);
					pstmt.setInt(2, startrow);
					pstmt.setInt(3, endrow);
					
					rs = pstmt.executeQuery();
					
					while(rs.next()) {
						
						Review review = new Review();
						review.setReview_code(rs.getInt("review_code"));
						review.setReview_content(rs.getString("review_content"));
						review.setReview_date(rs.getDate("review_date"));
						review.setReview_star(rs.getInt("review_star"));
						review.setUser_id(rs.getString("user_id"));
						list.add(review);
					}						
				} catch (Exception e) {
					e.printStackTrace();
					System.out.println("getReviewList() 에러 : " + e);
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
	
}
