package lesson.action;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import schedule.db.ScheduleDAO;
import userInfo.db.UserInfoDAO;
import user_lesson.db.UserLesson;
import user_lesson.db.UserLessonDAO;

public class UserListAction implements Action{

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		// TODO Auto-generated method stub
		ActionForward forward = new ActionForward();
		request.setCharacterEncoding("UTF-8");
		
		int lesson_code=Integer.parseInt(request.getParameter("lesson_code"));
		UserLessonDAO udao=new UserLessonDAO();
		List<UserLesson> user_lessonlist=udao.getuser_lessonlist(lesson_code);
		
		
		UserInfoDAO uidao=new UserInfoDAO();
		ScheduleDAO sdao=new ScheduleDAO();
		Map<String,String> map=new HashMap<String,String>();
		for( UserLesson userlessons : user_lessonlist ) {
			String user_id=userlessons.getUser_id();
			String lesson_de_code=userlessons.getLesson_de_code();
			String lesson_date=sdao.getLesson_date(lesson_de_code);
			String user_name=uidao.getUser_name(user_id);
			map.put(user_id, user_name);
			map.put(lesson_de_code,lesson_date);
		}
		
		
		
		request.setAttribute("list", user_lessonlist);
		request.setAttribute("map", map);
		forward.setRedirect(false);
		forward.setPath("lesson/user_list.jsp");
	
		return forward;
	}

}
