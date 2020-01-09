package main.net;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import teacher.db.TeacherDAO;
import userInfo.db.UserInfoDAO;

public class IdCheckAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		// TODO Auto-generated method stub
		String user_type=request.getParameter("user_type");
		int result=0;
		if(user_type.equals("0")) {
			UserInfoDAO udao=new UserInfoDAO();
			result=udao.idcheck(request.getParameter("id"));
		}else {
			TeacherDAO tdao=new TeacherDAO();
			result=tdao.idcheck(request.getParameter("id"));
		}
		
		response.getWriter().append(Integer.toString(result));
		return null;
		
	}
}

