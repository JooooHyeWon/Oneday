package userInfo.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import userInfo.db.UserInfo;
import userInfo.db.UserInfoDAO;

public class UserInfoAction implements Action{

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		// TODO Auto-generated method stub
				ActionForward forward = new ActionForward();
				UserInfo user = new UserInfo();
				UserInfoDAO userDAO = new UserInfoDAO();
				
				HttpSession session = request.getSession(true);
				String userid=(String)session.getAttribute("id");
				
				user = userDAO.getuserInfo(userid);
				int x = userDAO.getListCount(userid);
				System.out.println("내 수업 갯수"+x);
				request.setAttribute("ListCount",x);
				
				if(user == null) {
					System.out.println("유저 데이터 불러오기 실패");
					
					forward.setRedirect(false);
					request.setAttribute("message", "유저 데이터를 불러오지 못했습니다.");
					forward.setPath("error/error.jsp");
					return null;
				}
				System.out.println("유저 데이터 불러오기 성공");
				
				request.setAttribute("user", user);
				forward.setRedirect(false);
				forward.setPath("User/userInfo.jsp");
				return forward;
	}

}
