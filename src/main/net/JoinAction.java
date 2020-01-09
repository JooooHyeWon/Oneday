package main.net;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import teacher.db.Teacher;
import teacher.db.TeacherDAO;
import userInfo.db.UserInfo;
import userInfo.db.UserInfoDAO;

public class JoinAction implements Action{

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		// TODO Auto-generated method stub
		ActionForward forward=new ActionForward();
		
		request.setCharacterEncoding("UTF-8");
		String user_type=request.getParameter("user_type");
		String id=request.getParameter("id");
		String password=request.getParameter("password");
		String name=request.getParameter("name");
		String phone=request.getParameter("phone");
		String email=request.getParameter("email");
		
		int result=0;
		
		if(user_type.equals("0")) {
			UserInfo user=new UserInfo();
			user.setUser_type(user_type);
			user.setUser_id(id);
			user.setUser_pass(password);
			user.setUser_name(name);
			user.setUser_phone(phone);
			user.setUser_email(email);
			
			UserInfoDAO udao=new UserInfoDAO();
			result=udao.adduser(user);
			
		}else {
			
				Teacher teacher=new Teacher();
				teacher.setUser_type(user_type);
				teacher.setTea_id(id);
				teacher.setTea_pass(password);
				teacher.setTea_name(name);
				teacher.setTea_phone(phone);
				teacher.setTea_email(email);
				
				TeacherDAO tdao=new TeacherDAO();
				result=tdao.addteacher(teacher);
	
		}
		
		if(result==1) {
			forward.setPath("main.net");
			forward.setRedirect(true);
		}else if(result==-1){
			response.setContentType("text/html;charset=UTF-8");
			PrintWriter out=response.getWriter();
			out.println("<script>");
			out.println("alert('아이디가 중복되었습니다. 다시입력하세요');");
			//out.println("location.href='./join.net';");//새로고침되어 이전정보 전부 삭제
			out.println("history.back()");
			out.println("</script>"); //비밀번호를 제외한 다른 데이터는 유지되어있습니다.
			out.close();
			
		}
		return forward;
		
	}

}
