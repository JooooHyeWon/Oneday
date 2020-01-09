package main.net;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import main.db.MainDAO;

public class Manage_deleteAction implements Action{

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String code = request.getParameter("code");
		String type = request.getParameter("type");
		System.out.println("DAO code = "+code);
		System.out.println("DAO type = "+type);
		
		response.setContentType("text/html;charset=euc-kr");
		PrintWriter out = response.getWriter();
		
		MainDAO mdao = new MainDAO();
		int result = mdao.delete(code,type);
		String message = "삭제되었습니다.";
		if(result!=1) {
			message = "삭제가 되지 않았습니다.";
		}
		
		out.println("<script>");
		out.println("alert('"+message+"');");
		out.println("location.href='"+type+".net';");
		out.println("</script>");
		out.close();
		return null;
	}

}
