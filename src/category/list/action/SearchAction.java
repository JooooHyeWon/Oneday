package category.list.action;

import java.io.PrintWriter;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import category.list.db.CategoryListDAO;
import gallery.db.Gallery;
import lesson.db.Lesson;

public class SearchAction implements Action{

   @Override
   public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
      
      ActionForward forward = new ActionForward();
      request.setCharacterEncoding("UTF-8");
      // 입력한 키워드
      String search = request.getParameter("search").toString();            
      String[] keyword = search.split(" ");
      // 키워드 개수
      int num = keyword.length;
      
      System.out.println("키워드 = "+ search);
      System.out.println("키워드 개수 = "+ num);
      
      if(search.equals("") || num==0) {
         response.setContentType("text/html;charset=UTF-8");
         PrintWriter out = response.getWriter();
         out.println("<script>");
         out.println("alert('검색 할 내용을 입력하세요');");
         out.println("history.back();");
         out.println("</script>");
         out.close();
          return null;
      }else {
         CategoryListDAO cat = new CategoryListDAO();         
         List<Lesson> searchlesson = cat.searchLesson(keyword, num);
         if(searchlesson==null) {
            response.setContentType("text/html;charset=UTF-8");
            PrintWriter out = response.getWriter();
            out.println("<script>");
            out.println("alert('검색 결과가 없습니다.');");
            out.println("history.back();");
            out.println("</script>");
            out.close();
             return null;               
         }else {
            List<Gallery> searchmainPic = cat.loadLessonPic();
            request.setAttribute("Lesson", searchlesson);
            request.setAttribute("mainPic", searchmainPic);   
            forward.setRedirect(false);
             forward.setPath("categoryList/lessonList.jsp");
            return forward;      
      
         }
         
      }
   }

}