package teacher.action;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import teacher.db.Teacher;
import teacher.db.TeacherDAO;

public class TeacherInfoUpdateAction implements Action{

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		// TODO Auto-generated method stub
		ActionForward forward=new ActionForward();
		request.setCharacterEncoding("UTF-8");
		
		HttpSession session=request.getSession();
		String id=(String)session.getAttribute("id");
		
		String tea_name=request.getParameter("tea_name");
		String tea_phone=request.getParameter("tea_phone");
		String tea_email=request.getParameter("tea_email");
		
		System.out.println(tea_name+tea_phone+tea_email);
		TeacherDAO tdao=new TeacherDAO();
		int result=tdao.infoUpdate(id,tea_name,tea_phone,tea_email);
		
		if(result==1) {
			response.setContentType("text/html;charset=UTF-8");
			PrintWriter out=response.getWriter();
			out.println("<script>");
			out.println("alert('정보 변경이 완료 되었습니다')");
			out.println("location.href='teacherInfo.te'");
			out.println("</script>");
			out.close();
			
		}else {
			response.setContentType("text/html;charset=UTF-8");
			PrintWriter out=response.getWriter();
			out.println("<script>");
			out.println("alert('오류 발생')");
			out.println("</script>");
			out.close();
		}
		
		return null;
	}

}
