package userInfo.action;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import userInfo.db.UserInfo;
import userInfo.db.UserInfoDAO;

public class UserInfoUpdateAction implements Action{

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		request.setCharacterEncoding("utf-8");
		
		String id = request.getParameter("USER_ID");
		String name = request.getParameter("USER_NAME");
		String phone = request.getParameter("USER_PHONE");
		String email = request.getParameter("USER_EMAIL");
		UserInfo u = new UserInfo();
		
		u.setUser_id(id);
		u.setUser_name(name);
		u.setUser_phone(phone);
		u.setUser_email(email);
		
		System.out.println(u.getUser_name());
		System.out.println(u.getUser_pass());
		System.out.println(u.getUser_phone());
		System.out.println(u.getUser_email());
		response.setContentType("text/html;charset=utf-8");
		PrintWriter out = response.getWriter();
		
		UserInfoDAO userDAO = new UserInfoDAO();
		
		int result = userDAO.update(u);
		
		out.println("<script>");
		if(result == 1) {
			out.println("alert('정보 변경 완료');");
			out.println("location.href='userInfo.ue'");
		}else {
			out.println("alert('정보 변경 실패');");
			out.println("location.href='userInfo.ue'");
		}
		out.println("</script>");
		out.close();
		return null;
	}

}
