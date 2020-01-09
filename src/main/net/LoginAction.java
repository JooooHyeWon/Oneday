package main.net;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import teacher.db.TeacherDAO;
import userInfo.db.UserInfoDAO;

public class LoginAction implements Action{

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		// TODO Auto-generated method stub
		ActionForward forward = new ActionForward();
		request.setCharacterEncoding("UTF-8");
		String user_type=request.getParameter("user_type");
		
		int result=0;
		int lesson_num=0;
		String id=request.getParameter("id");
		String password=request.getParameter("password");
		if(user_type.equals("0")) {
			UserInfoDAO udao=new UserInfoDAO();
			result = udao.login(id,password);
		}else {
			TeacherDAO tdao=new TeacherDAO();
			result=tdao.login(id,password);
			lesson_num=tdao.getlesson_num(id);
			
		}
		
		System.out.println("결과는 "+result);
		
		if(result==1) {
			HttpSession session=request.getSession();
			session.setAttribute("id", id);
			session.setAttribute("user_type", user_type);
			System.out.println("아이디는 = "+id);
			if(user_type.equals("1")) {
				session.setAttribute("lesson_num", lesson_num);
				System.out.println(id+"의 수업 갯수 = "+lesson_num);
			}
			if(id.equals("admin")) {
				forward.setRedirect(true);
				forward.setPath("userInfo.net");
				//forward.setPath("BoardWrite.bo");
				return forward;
			}
			forward.setRedirect(true);
			forward.setPath("main.net");
			//forward.setPath("BoardWrite.bo");
			return forward;
			
		}else {
			String message="비밀번호가 일치하지 않습니다.";
			if(result==-1)
				message="아이디가 존재하지 않습니다.";
			
			response.setContentType("text/html;charset=UTF-8");
			PrintWriter out=response.getWriter();
			out.println("<script>");
			out.println("alert('"+message+"');");
			out.println("history.back();");
			out.println("</script>");
			out.close();
			return null;
		}
	}

}
