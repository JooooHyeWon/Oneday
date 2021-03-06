package main.net;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class LogoutAction implements Action{

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		// TODO Auto-generated method stub
		request.setCharacterEncoding("UTF-8");
		ActionForward forward=new ActionForward();
		
		HttpSession session=request.getSession();
		session.invalidate();
		
		forward.setRedirect(true);
		forward.setPath("main.net");
		return forward;
	}

}
