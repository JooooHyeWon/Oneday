package teacher.action;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import teacher.db.TeacherDAO;

public class TeacherPassUpdateAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		// TODO Auto-generated method stub
		ActionForward forward=new ActionForward();
		request.setCharacterEncoding("UTF-8");
		
		response.setContentType("text/html;charset=UTF-8");
		PrintWriter out=response.getWriter();
		
		
		HttpSession session=request.getSession();
		String id=(String)session.getAttribute("id");
		
		String origin_pass=request.getParameter("origin-pass");
		
		TeacherDAO tdao=new TeacherDAO();
		int passcheck=tdao.passcheck(id, origin_pass);
		if(passcheck==1) {
			
			String alter_pass=request.getParameter("alter-pass");
			int passchange=tdao.passupdate(id,alter_pass);
			if(passchange==1) {
				
				out.println("<script>");
				out.println("alert('비밀번호가 변경 되었습니다.');");
				out.println("self.close();");
				out.println("window.opener.location.href='teacherInfo.te'");
				out.println("</script>");
				out.close();
			}else {
			
				out.println("<script>");
				out.println("alert('비밀번호 변경에 실패하였습니다.')");
				out.println("</script>");
				out.close();
			}
			
		}else {
			
			out.println("<script>");
			out.println("alert('현재 비밀번호가 틀립니다');");
			out.println("$('#origin-pass').val('').focus();");
			out.println("</script>");
			out.close();
		}
	
		
		return null;
	}

}
