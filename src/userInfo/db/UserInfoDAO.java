package userInfo.db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

public class UserInfoDAO {
   DataSource ds;
   Connection con;
   PreparedStatement pstmt;
   ResultSet rs;
   int result;
   
   public UserInfoDAO() {
      try {
         Context init = new InitialContext();
         ds = (DataSource) init.lookup("java:comp/env/jdbc/OracleDB");
      } catch (Exception ex) {
         System.out.println("DB 연결 실패 : " + ex);
      }
   }
   
   public void close() {
      if (pstmt != null) {
         try {
            pstmt.close();
         } catch (SQLException ex) {
            ex.printStackTrace();
         }
      }
      if (con != null) {
         try {
            con.close();
         } catch (SQLException ex) {
            ex.printStackTrace();
         }
      }
   }

   
   public int idcheck(String id) {
      try {
         String sql="select user_id from userInfo where user_id = ? ";
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
         System.out.println("UserInfo idcheck()에러 입니다.");
      
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
   
   public int adduser(UserInfo user) {
      try {
         String sql="insert into userInfo values(?,?,?,?,?,?)";
         con=ds.getConnection();
         pstmt=con.prepareStatement(sql);
         pstmt.setString(1, user.getUser_id());
         pstmt.setString(2, user.getUser_pass());
         pstmt.setString(3, user.getUser_name());
         pstmt.setString(4, user.getUser_phone());
         pstmt.setString(5, user.getUser_email());
         pstmt.setString(6, user.getUser_type());
         
         result=pstmt.executeUpdate();
      
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
         
         String sql="select * from userInfo where user_id = ? ";
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
   
   public String getUser_name(String user_id) {
      String user_name="";
      try {
         con=ds.getConnection();
         String sql="select user_name from userinfo where user_id = ? ";
         pstmt=con.prepareStatement(sql);
         pstmt.setString(1, user_id);
         
         rs=pstmt.executeQuery();
         
         if(rs.next()) {
            user_name=rs.getString("user_name");
            
         }
      }catch(Exception e) {
         e.printStackTrace();
         System.out.println("getUser_name()에러");
      
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
      return user_name;   
   }

   public UserInfo user_info(String user_id) {
      
      UserInfo ul = null;
      
      try {
         con = ds.getConnection();
         
         String sql = "select user_name, user_email, user_phone from userInfo where user_id = ?";
         pstmt = con.prepareStatement(sql);
         
         pstmt.setString(1, user_id);
         rs = pstmt.executeQuery();
         
         if(rs.next()) {
            ul = new UserInfo();
            ul.setUser_name(rs.getString("user_name"));
            ul.setUser_email(rs.getString("user_email"));
            ul.setUser_phone(rs.getString("user_phone"));
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
      
      return ul;
   }
   
   public UserInfo getuserInfo(String userid) {
      UserInfo user = null;
      String sql = "select * from userinfo where user_id = ?";
      try {
         con = ds.getConnection();
         System.out.println("getConnection");
         pstmt = con.prepareStatement(sql);
         pstmt.setString(1, userid);
         rs = pstmt.executeQuery();
         if (rs.next()) {
            user = new UserInfo();
            user.setUser_id(rs.getString("USER_ID"));
            user.setUser_pass(rs.getString("USER_PASS"));
            user.setUser_name(rs.getString("USER_NAME"));
            user.setUser_phone(rs.getString("USER_PHONE"));
            user.setUser_email(rs.getString("USER_EMAIL"));
         }
         return user;
      } catch (Exception e) {
         System.out.println("getuserInfo : " + e);
         e.printStackTrace();
      } finally {
         close();
      }
      return null;
   }

   public int updatePW(String session_id, String origin_pass, String alter_pass) {
      int result = 0;
      try {
         con = ds.getConnection();
         String sql = "update userInfo set USER_PASS = ?" + "where USER_ID = ? and USER_PASS = ?";
         pstmt = con.prepareStatement(sql);
         pstmt.setString(1, alter_pass);
         pstmt.setString(2, session_id);
         pstmt.setString(3, origin_pass);
         result = pstmt.executeUpdate();
      } catch (Exception e) {
         System.out.println("updatePW 에러 : " + e);
         e.printStackTrace();
      } finally {
         close();
      }
      return result;
   }

   public int update(UserInfo u) {
      int result = 0;
      String sql = "update userInfo set USER_NAME = ?, " + "USER_PHONE = ?, USER_EMAIL = ? " + " where USER_ID = ?";
      try {
         con = ds.getConnection();

         pstmt = con.prepareStatement(sql);
         pstmt.setString(1, u.getUser_name());
         pstmt.setString(2, u.getUser_phone());
         pstmt.setString(3, u.getUser_email());
         pstmt.setString(4, u.getUser_id());
         result = pstmt.executeUpdate();
      } catch (Exception e) {
         System.out.println("유저 업데이트 중 에러 발생" + e);
         e.printStackTrace();
      } finally {
         close();
      }
      return result;
   }
   
   public JsonArray getLessonList(String session_id) {
      String sql = "select distinct lesson.lesson_code,lesson_title,lesson_type,lesson_content,lesson_info,pic_num,pic_name,bookmark" + 
            " from lesson,gallery,user_lesson " + 
            "where user_lesson.user_id = ? and user_lesson.lesson_code = lesson.lesson_code and gallery.lesson_code = user_lesson.lesson_code and pic_num = 1 "
            + "order by lesson.lesson_code asc";
      JsonArray array = new JsonArray();
      
      try {
         con = ds.getConnection();
         pstmt = con.prepareStatement(sql);
         System.out.println("getLessonList 메소드 아이디 값 확인 : "+session_id);
         pstmt.setString(1, session_id);
         rs = pstmt.executeQuery();
         while(rs.next()) {
            JsonObject object = new JsonObject();
            object.addProperty("lesson_code", rs.getInt(1));
            object.addProperty("Lesson_title", rs.getString(2));
            object.addProperty("Lesson_type", rs.getString(3));
            object.addProperty("Lesson_content", rs.getString(4));
            object.addProperty("lesson_info", rs.getString(5));
            object.addProperty("pic_num", rs.getInt(6));
            object.addProperty("pic_name", rs.getString(7));
            object.addProperty("bookmark", rs.getInt(8));
            array.add(object);
         }
      }catch (Exception e) {
         e.printStackTrace();
         System.out.println("getLessonList 에러 : " + e);
      }finally {
         close();
      }
      return array;
   }
   
   public JsonArray getLikeLessonList(String user_id) {
         String sql = "select distinct lesson.lesson_code, lesson_type, lesson_title, lesson_location, lesson_info,pic_num,pic_name,is_bookmark "
               + "from lesson,bookmark,gallery "
               + "where bookmark.user_id = ? AND is_bookmark = 1 and pic_num = 1 and lesson.lesson_code = gallery.lesson_code and lesson.lesson_code=bookmark.lesson_code  "
               + "order by lesson.lesson_code asc";
         JsonArray array = new JsonArray();
      try {
         con = ds.getConnection();
         pstmt = con.prepareStatement(sql);
         pstmt.setString(1, user_id);
         System.out.println(user_id);
         rs = pstmt.executeQuery();
         while (rs.next()) {
            JsonObject object = new JsonObject();
            
            object.addProperty("lesson_code", rs.getInt(1));
            object.addProperty("lesson_type", rs.getString(2));
            object.addProperty("lesson_title", rs.getString(3));
            object.addProperty("lesson_location", rs.getString(4));
            object.addProperty("lesson_info", rs.getString(5));
            object.addProperty("pic_num", rs.getInt(6));
            object.addProperty("pic_name", rs.getString(7));
            array.add(object);
         }
      } catch (Exception e) {
         e.printStackTrace();
         System.out.println("getLikeLessonList() 에러 : " + e);
      } finally {
         close();
      }
      return array;
   }

   public int getListCount(String id) {// 총 리스트 수 받아오는 메서드
      int x = 0;
      String sql = "select count(*) from user_lesson where user_id = ?";
      try {
         con = ds.getConnection();
         System.out.println(id);
         pstmt = con.prepareStatement(sql);
         pstmt.setString(1, id);
         rs = pstmt.executeQuery();
         if(rs.next()) {
            x = rs.getInt(1);
         }
         
      }catch(Exception e) {
         System.out.println("getListCount() 에러 : " + e);
         e.printStackTrace();
      }finally {
            close();
      }
      return x;
   }
   
   public JsonArray getGoodLesson(String lesson_type) {
	      String sql = "select * from (select rownum rnum , b.* " + 
	            "from (select max(bookmark),lesson_title,lesson_info,lesson.lesson_code,pic_num,pic_name " + 
	            "from lesson,gallery " + 
	            "where lesson.lesson_code=gallery.lesson_code and lesson_type = ? and pic_num = 1 group by lesson_title,lesson_info,lesson.lesson_code,pic_num,pic_name " + 
	            "order by max(bookmark) desc) b ) where rnum=1 ";
	      JsonArray array = new JsonArray();
	      try {
	         con = ds.getConnection();
	         pstmt = con.prepareStatement(sql);
	         pstmt.setString(1, lesson_type);
	         System.out.println(lesson_type);
	         rs = pstmt.executeQuery();
	         while (rs.next()) {
	            JsonObject object = new JsonObject();
	            object.addProperty("bookmark", rs.getInt("max(bookmark)"));
	            object.addProperty("lesson_title", rs.getString("lesson_title"));
	            object.addProperty("lesson_info", rs.getString("lesson_info"));
	            object.addProperty("lesson_code", rs.getString("lesson_code"));
	            object.addProperty("pic_num", rs.getInt("pic_num"));
	            object.addProperty("pic_name", rs.getString("pic_name"));
	            array.add(object);
	         }
	      } catch (Exception e) {
	         e.printStackTrace();
	         System.out.println("getLikeLessonList() 에러 : " + e);
	      } finally {
	         close();
	      }
	      return array;
	   }

   public int reviewWrite(int lesson_code,String session_id, String review_content, int star_point) {
         String sql = "insert into review values(review_seq.nextval,?,?,sysdate,?,?)";
         try {
            con = ds.getConnection();
            pstmt = con.prepareStatement(sql);
            pstmt.setInt(1, lesson_code);
            pstmt.setString(2, session_id);
            pstmt.setString(3, review_content);
            pstmt.setInt(4, star_point);
            result = pstmt.executeUpdate();
         }catch (Exception e) {
            e.printStackTrace();
            System.out.println("reviewWrite() 에러 : " + e);
         }finally {
            close();
         }
         return result;
      }

   public boolean userReviewCheck(int lesson_code, String session_id) {
      String sql ="SELECT LESSON_CODE,USER_ID,REVIEW_CONTENT FROM REVIEW where lesson_code = ? and user_id = ?";
      boolean check = false;
      try {
         con = ds.getConnection();
         pstmt = con.prepareStatement(sql);
         pstmt.setInt(1, lesson_code);
         pstmt.setString(2, session_id);
         rs = pstmt.executeQuery();
         if(!rs.next()) {
            check = false;
            System.out.println(check);
         }else {
            check = true;
            System.out.println(check);
         }
         }catch (Exception e) {
            e.printStackTrace();
            System.out.println("userReviewCheck 에러 : "+e);
         }
      return check;
      
   }
}