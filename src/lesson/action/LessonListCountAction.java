package lesson.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import lesson.db.LessonDAO;

public class LessonListCountAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		request.setCharacterEncoding("UTF-8");

		String id=request.getParameter("id");
		LessonDAO ldao=new LessonDAO();
		int result=ldao.getlessoncount(id);
		
		response.getWriter().append(Integer.toString(result));
		return null;
		
		
	}

}
