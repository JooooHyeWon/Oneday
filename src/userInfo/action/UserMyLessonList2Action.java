package userInfo.action;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.google.gson.JsonArray;

import userInfo.db.UserInfo;
import userInfo.db.UserInfoDAO;

public class UserMyLessonList2Action implements Action{

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		// TODO Auto-generated method stub
				UserInfoDAO userDAO = new UserInfoDAO();
				UserInfo user = new UserInfo();
				HttpSession session = request.getSession(true);
				String session_id = (String)session.getAttribute("id");
				System.out.println(session_id);
				
				JsonArray json = userDAO.getLessonList(session_id);
				response.setContentType("text/html;charset=UTF-8");
				response.setHeader("cache-control", "no-cache,no-store");
				PrintWriter out = response.getWriter();
				out.print(json);
				System.out.println(json);
				return null;
	}

}
