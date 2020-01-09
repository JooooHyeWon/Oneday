package teacher.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import teacher.db.Teacher;
import teacher.db.TeacherDAO;

public class TeacherInfoAction implements Action{
	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		// TODO Auto-generated method stub
		ActionForward forward = new ActionForward();
		request.setCharacterEncoding("UTF-8");
		
		HttpSession session=request.getSession();
		String id=(String)session.getAttribute("id");
		
		System.out.println("id="+id);
		
		TeacherDAO tdao=new TeacherDAO();
		Teacher teacher=tdao.info(id);
		request.setAttribute("teacher", teacher);
		forward.setRedirect(false);
		forward.setPath("teacherInfoMain.te");
		return forward;
	}

}
