package userInfo.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import userInfo.db.UserInfo;
import userInfo.db.UserInfoDAO;

public class UserLessonListAction implements Action{

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		ActionForward forward = new ActionForward();
		UserInfoDAO userDAO = new UserInfoDAO();
		UserInfo user = new UserInfo();
		
		HttpSession session = request.getSession();
		String id = (String)session.getAttribute("id");
		user.setUser_id(id);
		String session_id = user.getUser_id();
		
		System.out.println("현재 세션 아이디 : "+session_id);
		int x = userDAO.getListCount(session_id);
		System.out.println("내 수업 갯수"+x);
		request.setAttribute("ListCount",x);
		
		forward.setRedirect(false);
		forward.setPath("User/userMyLessonList.jsp");
		return forward;
	}

}
