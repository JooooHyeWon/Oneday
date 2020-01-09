package lesson.action;

import java.io.File;
import java.io.PrintWriter;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;
import gallery.db.GalleryDAO;
import lesson.db.Lesson;
import lesson.db.LessonDAO;



public class LessonAddAction implements Action {

   @Override
   public ActionForward execute(HttpServletRequest request, HttpServletResponse response)
         throws Exception {
      // TODO Auto-generated method stub
      
      ActionForward forward = new ActionForward();
      request.setCharacterEncoding("UTF-8");

      response.setContentType("text/html;charset=UTF-8");
      PrintWriter out = response.getWriter();
      
      /*
       * String path="/fileUpload"; ServletContext
       * context=request.getServletContext(); String
       * realFolder=context.getRealPath(path);
       */
      String realFolder="D:/test_workspace/oneday/WebContent/gallery";
      MultipartRequest multi=new MultipartRequest(request,realFolder,
                                 1024*1024*10,"utf-8",new DefaultFileRenamePolicy());

      
      HttpSession session = request.getSession();
      String id = (String) session.getAttribute("id");
      
      Lesson lesson = new Lesson();
      lesson.setTea_id(id);
      lesson.setLesson_type(multi.getParameter("lesson_type"));
      lesson.setLesson_title(multi.getParameter("lesson_title"));
      lesson.setLesson_price(multi.getParameter("lesson_price"));
      lesson.setLesson_location(multi.getParameter("lesson_location"));
      
      String content=multi.getParameter("lesson_content");
      content = content.replace("\r\n","<br>");
      lesson.setLesson_content(content);
      lesson.setLesson_chat(multi.getParameter("lesson_chat"));
      
      content=multi.getParameter("tea_info");
      content = content.replace("\r\n","<br>");
      lesson.setTea_info(content);
      lesson.setLesson_info(multi.getParameter("lesson_info"));

      LessonDAO ldao = new LessonDAO();
      int lesson_code = ldao.addlesson(lesson);

      if(lesson_code==1) {
         lesson_code=ldao.getlesson_code();
      }else {
         out.println("<script>");
         out.println("alert('수업 등록 실패');");
         out.println("</script>");
         return null;
      }
      /*
       * String[] dates = multi.getParameterValues("lesson_yoil"); for (int i = 0; i <
       * dates.length; i++) { Schedule schedule = new Schedule();
       * schedule.setMax_user(Integer.parseInt(multi.getParameter("max_user")));
       * String lesson_de_code=lesson_code+"-"+dates[i];
       * schedule.setLesson_date(dates[i]);
       * schedule.setLesson_start(multi.getParameter("lesson_start"));
       * schedule.setLesson_end(multi.getParameter("lesson_end"));
       * schedule.setLesson_de_code(lesson_de_code);
       * 
       * ScheduleDAO sdao = new ScheduleDAO(); int result =
       * sdao.addschedule(lesson_code, schedule);
       * 
       * }
       */
         String name[]=new String[100];
         File file[]=new File[100];
         String changeName[]=new String[100];
      for (int i = 1; i <= 10; i++) {
         name[i]=multi.getOriginalFileName("class_file"+i);
         
         if (name[i]!= null) {
            
            file[i]=multi.getFile("class_file"+i);
            changeName[i]=file[i].getName();
            GalleryDAO gdao = new GalleryDAO();
            int result = gdao.addgallery(lesson_code,i, "gallery/"+changeName[i]);
            
         }
      }

      out.println("<script>");
      out.println("alert('수업등록이 완료 되었습니다. 시간을 등록해 주세요 ^^');");
      out.println("location.href='teacherLessonList.te';");
      out.println("</script>");
      
      
      /*
       * int num=(int)session.getAttribute("lesson_num"); num++;
       * session.setAttribute("lesson_num", num);
       */
      
      return null;
   }

}