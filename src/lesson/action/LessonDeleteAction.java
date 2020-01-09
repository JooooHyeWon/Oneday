package lesson.action;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import lesson.db.LessonDAO;



public class LessonDeleteAction implements Action{

   @Override
   public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
      // TODO Auto-generated method stub
   
      ActionForward forward = new ActionForward();
      request.setCharacterEncoding("UTF-8");
   
      int lesson_code=Integer.parseInt(request.getParameter("lesson_code"));
      LessonDAO ldao=new LessonDAO();
      int result=ldao.delete_lesson(lesson_code);
      
      if(result==1) {
         response.setContentType("text/html;charset=UTF-8");
         PrintWriter out = response.getWriter();
         out.println("<script>");
         out.println("alert('수업이 삭제되었습니다.');");
         out.println("location.href='lessondeletepage.lesson';");
         out.println("</script>");
         out.close();
      }

      return null;
   
   }
}