package userInfo.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import userInfo.db.UserInfo;
import userInfo.db.UserInfoDAO;

public class UserPassUpdateAction implements Action{

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		ActionForward forward = new ActionForward();
		UserInfoDAO userDAO = new UserInfoDAO();
		UserInfo user = new UserInfo();
		
		HttpSession session = request.getSession();
		String session_id = (String) session.getAttribute("id");
		String origin_pass = request.getParameter("origin-pass");
		String alter_pass = request.getParameter("alter-pass");
		
		int updatecheck = 0;
		updatecheck = userDAO.updatePW(session_id,origin_pass,alter_pass);
		
		
		if(updatecheck == 1) {
			System.out.println("비밀번호 업데이트 성공!");
			
			forward .setRedirect(false);
			forward.setPath("User/passUpdateOK.jsp");
			return forward;
		}
		forward.setRedirect(false);
		request.setAttribute("message", "비밀번호 업데이트 실패!");
		forward.setPath("error/error.jsp");
		return null;
	}

}
