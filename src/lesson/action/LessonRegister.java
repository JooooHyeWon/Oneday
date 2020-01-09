package lesson.action;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import schedule.db.Schedule;
import schedule.db.ScheduleDAO;
import user_lesson.db.UserLesson;
import user_lesson.db.UserLessonDAO;

public class LessonRegister implements Action{

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		ActionForward forward = new ActionForward();
		request.setCharacterEncoding("utf-8");
		
		HttpSession session = request.getSession();
		String id = (String) session.getAttribute("id");
		
		
		int lesson_code = Integer.parseInt(request.getParameter("lesson_code"));		
		String lesson_de_code = request.getParameter("lesson_de_code");  //lesson_code-lesson_date 2-2019/12/22
		
		System.out.println("lesson_code = " +lesson_code);
		System.out.println("lesson_de_code = " +lesson_de_code);
		
		//user_lesson 테이블에 값 insert 하려고 set 하는 부분
		UserLesson ul = new UserLesson();
		ul.setUser_id(id);
		ul.setLesson_code(lesson_code);
		ul.setLesson_de_code(lesson_de_code);
		
		
		UserLessonDAO uldao = new UserLessonDAO();
		ScheduleDAO sdao = new ScheduleDAO();
		
		Schedule s = sdao.thislesson(lesson_de_code);
		int max = s.getMax_user();	//현재 수업의 최대 인원
		int now = s.getUser_count();//현재 수업 신청 인원
		
		System.out.println("최대" + max);
		
		if (max > now) {
			
			int result = uldao.register(ul);
			int update = sdao.cnt_update(lesson_de_code);
			
			if(result == 1 && update == 1) {
				response.setContentType("text/html; charset=utf-8");
				PrintWriter out = response.getWriter();
				out.println("<script>");
				out.println("alert('수강 목록에 추가 되었습니다.');");
				out.println("location.href='userMyLessonList.ue';");
				out.println("</script>");
				out.close();
				return null;
				
			} else if (result == -1) {
				response.setContentType("text/html; charset=utf-8");
				PrintWriter out = response.getWriter();
				out.println("<script>");
				out.println("alert('이미 수강신청한 수업입니다.');");
				out.println("location.href='lessonInfo.lesson';");
				out.println("</script>");
				out.close();
				return null;
			}
		} else {
			
			response.setContentType("text/html; charset=utf-8");
			PrintWriter out = response.getWriter();
			out.println("<script>");
			out.println("alert('해당 수업은 인원이 마감 되었습니다. 다른 날짜를 선택 해주세요');");
			out.println("location.href='register.lesson?lesson_code=" + lesson_code + "';");
			out.println("</script>");
			out.close();
			return null;
			
		}

		return forward;
	}

}
