package lesson.action;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import lesson.db.Lesson;
import lesson.db.LessonDAO;
import schedule.db.Schedule;
import schedule.db.ScheduleDAO;
import userInfo.db.UserInfo;
import userInfo.db.UserInfoDAO;

public class GoPay implements Action{

   @Override
   public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
      ActionForward forward = new ActionForward();
      request.setCharacterEncoding("UTF-8");
         
      
      LessonDAO dao = new LessonDAO();
      UserInfoDAO uldao = new UserInfoDAO();
      
      int lesson_code = Integer.parseInt(request.getParameter("lesson_code"));      
      String lesson_de_code = request.getParameter("lesson_de_code");
      
      HttpSession session = request.getSession();
      String id = (String) session.getAttribute("id");
      
      Lesson lesson = dao.lesson_info(lesson_code); 
      UserInfo userinfo = uldao.user_info(id);
   
         
      request.setAttribute("lesson_code", lesson_code);
      request.setAttribute("lesson_de_code", lesson_de_code);
      
      
      String price = lesson.getLesson_price();
      request.setAttribute("price", price);
      
      String title = lesson.getLesson_title();
      request.setAttribute("title", title);
      
      String email = userinfo.getUser_email();
      request.setAttribute("email", email);
      
      String name = userinfo.getUser_name();
      request.setAttribute("name", name);
      
      String phone = userinfo.getUser_phone();
      request.setAttribute("phone", phone);
      
      
      ScheduleDAO sdao = new ScheduleDAO();
      
      Schedule s = sdao.thislesson(lesson_de_code);
      int max = s.getMax_user();   //현재 수업의 최대 인원
      int now = s.getUser_count();//현재 수업 신청 인원
      
      
      //최대 인원보다 현재 신청한 수가 많거나(불가능) 같은 경우
      if (max <= now) {
         response.setContentType("text/html; charset=utf-8");
         PrintWriter out = response.getWriter();
         out.println("<script>");
         out.println("alert('해당 수업은 인원이 마감 되었습니다. 다른 날짜를 선택 해주세요');");
         out.println("location.href='register.lesson?lesson_code=" + lesson_code + "';");   //날짜 고르는 페이지로 이동
         out.println("</script>");
         out.close();
         return null;
      }
            
      
      //아닐 경우 결제 시작
      forward.setRedirect(false);
      forward.setPath("lesson/Pay2.jsp");
      
      
      return forward;
   }

}