package lesson.action;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import schedule.db.Schedule;
import schedule.db.ScheduleDAO;

public class ScheduleAddAction implements Action{

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		ActionForward forward = new ActionForward();
		request.setCharacterEncoding("UTF-8");

		response.setContentType("text/html;charset=UTF-8");
		PrintWriter out = response.getWriter();
		
		int lesson_code=Integer.parseInt(request.getParameter("lesson_code"));
		String lesson_date=request.getParameter("lesson_date");
		System.out.println("lesson_date="+lesson_date);
		String lesson_start=request.getParameter("lesson_start");
		String lesson_end=request.getParameter("lesson_end");
		int max_user=Integer.parseInt(request.getParameter("max_user"));
		String lesson_de_code=lesson_code+"-"+request.getParameter("lesson_date");
		
		Schedule schedule=new Schedule();
		schedule.setLesson_code(lesson_code);
		schedule.setLesson_date(lesson_date);
		schedule.setLesson_start(lesson_start);
		schedule.setLesson_end(lesson_end);
		schedule.setMax_user(max_user);
		schedule.setLesson_de_code(lesson_de_code);
		
		ScheduleDAO sdao=new ScheduleDAO();
		int result=sdao.addschedule(schedule);
		
		if(result==1) {
			out.println("<script>");
			out.println("self.close();");
			out.println("alert('시간이 등록되었습니다.');");
			out.println("</script>");
			out.close();
		}
		return null;
	}

}
