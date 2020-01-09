package lesson.action;

import java.io.PrintWriter;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import lesson.db.Lesson;
import lesson.db.LessonDAO;
import schedule.db.Schedule;
import schedule.db.ScheduleDAO;
import teacher.db.TeacherDAO;

public class SelectRegisterInfo implements Action{

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		ActionForward forward = new ActionForward();
		
		request.setCharacterEncoding("UTF-8");
		
		LessonDAO dao = new LessonDAO();
		ScheduleDAO sdao = new ScheduleDAO();
		
		TeacherDAO tdao = new TeacherDAO();
		
		int lesson_code = Integer.parseInt(request.getParameter("lesson_code"));
		
		
		Lesson lesson = dao.lesson_info(lesson_code);
		List<Schedule> schedule = sdao.schedule_info(lesson_code);
				
		request.setAttribute("lesson", lesson);
		request.setAttribute("schedule", schedule);
		
		HttpSession session = request.getSession();
		String id = (String) session.getAttribute("id");
		int result = tdao.isTeacher(id);
		
		if(result == 0) {
			response.setContentType("text/html; charset=UTF-8");
			PrintWriter out = response.getWriter();
			out.println("<script>");
			out.println("alert('강사님은 수강신청 불가능 합니다.');");
			out.println("history.back()");
			out.println("</script>");
			out.close();
			return null;
		}
		
		
		forward.setRedirect(false);
		forward.setPath("lesson/lessonRegister.jsp");
		
		return forward;
	}

}
