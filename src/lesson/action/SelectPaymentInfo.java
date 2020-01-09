package lesson.action;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import lesson.db.Lesson;
import lesson.db.LessonDAO;
import user_lesson.db.UserLessonDAO;

public class SelectPaymentInfo implements Action{

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		ActionForward forward = new ActionForward();
		
		request.setCharacterEncoding("UTF-8");
		
		LessonDAO dao = new LessonDAO();
		UserLessonDAO uldao = new UserLessonDAO();
		
		HttpSession session = request.getSession();
		String id = (String) session.getAttribute("id");
		
		int lesson_code = Integer.parseInt(request.getParameter("lesson_code"));
		
		Lesson lesson = dao.lesson_info(lesson_code);
		
		request.setAttribute("lesson", lesson);
		
		String lesson_de_code = lesson.getLesson_code() + "-";
		lesson_de_code += request.getParameter("schedule");
		System.out.println(lesson_de_code);		
		request.setAttribute("lesson_de_code", lesson_de_code);
		
		int isid = uldao.isId(id, lesson_de_code);	//session
		
		
		
		if(isid == -1) { //아이디가 존재하지 않으면 (신청 가능)
			forward.setRedirect(false);
			forward.setPath("lesson/lessonPayment.jsp");
			
		} else if (isid == 1) {
			response.setContentType("text/html; charset=UTF-8");
			PrintWriter out = response.getWriter();
			out.println("<script>");
			out.println("alert('이미 수강신청한 수업입니다.');");
			out.println("history.back()");
			out.println("</script>");
			out.close();
			return null;
		}		
		
		System.out.println("lesson_code = " + lesson.getLesson_code());
		System.out.println("lesson_de_code = " + lesson_de_code);
		return forward;
	}

}
