package userInfo.action;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.google.gson.JsonArray;

import userInfo.db.UserInfo;
import userInfo.db.UserInfoDAO;

public class UserLikeLessonAction implements Action{

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		UserInfoDAO userDAO = new UserInfoDAO();
		HttpSession session = request.getSession(true);
		String session_id = (String)session.getAttribute("id");
		userDAO.getuserInfo(session_id);
		JsonArray json = userDAO.getLikeLessonList(session_id);
		response.setContentType("text/html;charset=UTF-8");
		response.setHeader("cache-control", "no-cache,no-store");
		PrintWriter out = response.getWriter();
		out.print(json);
		System.out.println(json);
		return null;
	}

}
