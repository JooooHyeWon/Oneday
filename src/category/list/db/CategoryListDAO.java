package category.list.db;

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

import gallery.db.Gallery;
import lesson.db.Lesson;

public class CategoryListDAO {
   private DataSource ds; 
   private Connection con;
   private PreparedStatement pstmt;
   private ResultSet rs;   
   
   public CategoryListDAO() {
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
   
   
   // 수업 목록 불러오기
   public List<Lesson> loadLessonList(String category) throws SQLException {      
      List<Lesson> lesson = new ArrayList<Lesson>();   
      String sql;
      try {
         con = ds.getConnection();
         if(category.equals("popular")) {
            sql = "select * from lesson order by bookmark desc";            
            pstmt = con.prepareStatement(sql);            
            rs = pstmt.executeQuery();
         }else {
            sql = "select * from lesson where lesson_type=? order by bookmark desc";
            pstmt = con.prepareStatement(sql);
            pstmt.setString(1, category);            
            rs = pstmt.executeQuery();
         }         
         
         while(rs.next()) {                  
            int lesson_code = rs.getInt("lesson_code");
            String lesson_type = rs.getString("lesson_type");
            String lesson_title = rs.getString("lesson_title");
            int bookmark = rs.getInt("bookmark");
            String lesson_info = rs.getString("lesson_info");
                        
            Lesson Lesson = new Lesson();
            Lesson.setLesson_code(lesson_code);
            Lesson.setLesson_type(lesson_type);
            Lesson.setLesson_title(lesson_title);
            Lesson.setLesson_info(lesson_info);
            Lesson.setBookmark(bookmark);
            
            lesson.add(Lesson);
         }                  
      } finally {
         close();
      }      
      return lesson;
   }
   
   // 수업 목록 불러오기 - 정렬용 
   public List<Lesson> loadLessonList(String category, String sort)  throws SQLException {      
      List<Lesson> lesson = new ArrayList<Lesson>();   
      System.out.println("DAO에서 카테고리 = "+category);
      String sql;
      try {
         con = ds.getConnection();
         if(category.equals("popular")) {
            if(sort.equals("bm")) {
               sql = "select * from lesson order by bookmark desc";   
            }else {
               sql = "select * from lesson order by lesson_code desc";   
            }         
            pstmt = con.prepareStatement(sql);            
            rs = pstmt.executeQuery();
         }else {
            if(sort.equals("bm")) {
               sql = "select * from lesson where lesson_type=? order by bookmark desc";
            }else {
               sql = "select * from lesson where lesson_type=? order by lesson_code desc";
            }            
            pstmt = con.prepareStatement(sql);
            pstmt.setString(1, category);            
            rs = pstmt.executeQuery();
         }   
         System.out.println(sql);
         
         while(rs.next()) {                  
            int lesson_code = rs.getInt("lesson_code");
            String lesson_type = rs.getString("lesson_type");
            String lesson_title = rs.getString("lesson_title");
            int bookmark = rs.getInt("bookmark");
            String lesson_info = rs.getString("lesson_info");
                        
            Lesson Lesson = new Lesson();
            Lesson.setLesson_code(lesson_code);
            Lesson.setLesson_type(lesson_type);
            Lesson.setLesson_title(lesson_title);
            Lesson.setLesson_info(lesson_info);
            Lesson.setBookmark(bookmark);
            
            lesson.add(Lesson);
         }         
         
      } finally {
         close();
      }      
      return lesson;
   }

   // 수업 사진 불러오기
   public List<Gallery> loadLessonPic()  throws SQLException {
      List<Gallery> pic = new ArrayList<Gallery>();      
      try {
         con = ds.getConnection();
         
         String sql = " select lesson_code, pic_num, pic_name"  + 
                    " from gallery where pic_num=1";
         
         pstmt = con.prepareStatement(sql);         
         rs = pstmt.executeQuery();
         
         while(rs.next()) {            
            int lesson_code = rs.getInt("lesson_code");
            int pic_num = rs.getInt("pic_num");
            String pic_name = rs.getString("pic_name");
                        
            Gallery Gallery = new Gallery();
            Gallery.setLesson_code(lesson_code);
            Gallery.setPic_num(pic_num);
            Gallery.setPic_name(pic_name);
            
            pic.add(Gallery);
         }         
         
      } finally {
         close();
      }
      return pic;
   }

   // 북마크 +1 (테이블 두개 동시)
   public void addBookmark(int code, String id) {
      try {
         String checksql = "select is_bookmark from bookmark "
               + "where user_id = ? and lesson_code = ?";
         con = ds.getConnection();
         pstmt = con.prepareStatement(checksql);
         pstmt.setString(1, id);
         pstmt.setInt(2, code);
         rs = pstmt.executeQuery();
         
         String sql2="";
         if(rs.next()) {
            sql2 = "update bookmark "
                  + "set is_bookmark = 1 "
                  + "where user_id = ? and lesson_code = ?";
         }else {
            sql2 = "insert into bookmark "
                  + "values(?, ?, 1)";
         }
         
         String sql = "update lesson "
               + "set bookmark = bookmark +1 "
               + "where lesson_code = ?";
         con = ds.getConnection();
         con.setAutoCommit(false);
         pstmt = con.prepareStatement(sql);
         pstmt.setInt(1, code);
         int result1 = pstmt.executeUpdate();
         
         System.out.println(sql2);
         pstmt = con.prepareStatement(sql2);
         pstmt.setString(1, id);
         pstmt.setInt(2, code);
         int result2 = pstmt.executeUpdate();
         
         if(result1 >=0 && result2 == 1) {
            con.commit(); // commit 합니다.
            con.setAutoCommit(true); // 다시 true로 설정합니다.
            System.out.println("북마크 DAO성공");
         }else {
            con.rollback();
            System.out.println("rollback()");
         }         
      } catch (SQLException e) {
         System.out.println("addBookmark() 에러 : "+ e);
         e.printStackTrace();
      }finally {
         close();
      }
   }
   
   // 북마크 삭제
   public void deleteBookmark(int code, String id) {
      try {
         String sql = "update lesson "
               + "set bookmark = bookmark -1 "
               + "where lesson_code = ?";
         con = ds.getConnection();
         con.setAutoCommit(false);
         pstmt = con.prepareStatement(sql);
         pstmt.setInt(1, code);
         int result1 = pstmt.executeUpdate();
         
         sql = "update bookmark "
               + "set is_bookmark = 0 "
               + "where user_id = ? and lesson_code = ?";
         pstmt = con.prepareStatement(sql);
         pstmt.setString(1, id);
         pstmt.setInt(2, code);
         int result2 = pstmt.executeUpdate();
         
         if(result1 >=0 && result2 == 1) {
            con.commit(); // commit 합니다.
            con.setAutoCommit(true); // 다시 true로 설정합니다.
            System.out.println("북마크삭제 DAO성공");
         }else {
            con.rollback();
            System.out.println("rollback()");
         }         
      } catch (SQLException e) {
         System.out.println("addBookmark() 에러 : "+ e);
         e.printStackTrace();
      }finally {
         close();
      }
      
   }

   // hover시 북마크 여부 확인
   public boolean checkBookmark(int lesson_code, String loginid) {
      boolean BM = false;
      String sql = "select is_bookmark from bookmark "
            + "where lesson_code = ? and user_id = ?";
      try {
         con = ds.getConnection();
         pstmt = con.prepareStatement(sql);
         pstmt.setInt(1, lesson_code);
         pstmt.setString(2, loginid);
         rs = pstmt.executeQuery();
         
         if(rs.next()) {
            int result = rs.getInt("is_bookmark");
            if(result==1) {
               BM = true;
            }else {
               BM = false;
            }               
         }         
      } catch (SQLException e) {
         System.out.println("checkBookmark() 에러 : "+ e);
         e.printStackTrace();
      }finally {
         close();
      }
      return BM;
   }

   // 검색하기
   public List<Lesson> searchLesson(String[] keyword, int num) throws SQLException {
      List<Lesson> searchlesson = new ArrayList<Lesson>();   
      String sql;
      try {
         con = ds.getConnection();
         sql = "select lesson_code, lesson_type, lesson_title, bookmark " + 
                 " from lesson where lesson_title like ? ";
         for(int i=0; i<num-1; i++) {
            sql += " or lesson_title like ? ";
         }
         System.out.println(sql);
         for(int i=0;i<num;i++) {
            System.out.println("키워드"+ i+" = "+ keyword[i]);
         }
         
         
         pstmt = con.prepareStatement(sql);
         for(int i=0;i<num;i++) {
            pstmt.setString(i+1, "%"+keyword[i]+"%" );
         }
         rs = pstmt.executeQuery();
                  
         if(rs.isBeforeFirst()==false){
            return null;
         }
         while(rs.next()) {                  
            int lesson_code = rs.getInt("lesson_code");
            String lesson_type = rs.getString("lesson_type");
            String lesson_title = rs.getString("lesson_title");
            int bookmark = rs.getInt("bookmark");
                        
            Lesson Lesson = new Lesson();
            Lesson.setLesson_code(lesson_code);
            Lesson.setLesson_type(lesson_type);
            Lesson.setLesson_title(lesson_title);
            Lesson.setBookmark(bookmark);
            
            searchlesson.add(Lesson);
         }                  
      } finally {
         close();
      }      
      return searchlesson;
   }
   


   
}