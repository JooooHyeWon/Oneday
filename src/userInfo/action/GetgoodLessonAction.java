package userInfo.action;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.JsonArray;

import userInfo.db.UserInfoDAO;

public class GetgoodLessonAction implements Action{

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		// TODO Auto-generated method stub
				UserInfoDAO userDAO = new UserInfoDAO();
				String lesson_type = (String)request.getParameter("lesson_type");
				System.out.println(lesson_type);
				JsonArray json = userDAO.getGoodLesson(lesson_type);
				response.setContentType("text/html;charset=UTF-8");
				response.setHeader("cache-control", "no-cache,no-store");
				PrintWriter out = response.getWriter();
				out.print(json);
				System.out.println(json);
				return null;
	} 

}
