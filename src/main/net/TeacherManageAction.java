package main.net;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import main.db.MainDAO;
import teacher.db.Teacher;

public class TeacherManageAction implements Action{

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		ActionForward forward = new ActionForward();
		request.setCharacterEncoding("UTF-8");
		MainDAO mdao = new MainDAO();
		
		int page = 1;
		int limit=5;
		
		if(request.getParameter("page")!=null) {
			page = Integer.parseInt(request.getParameter("page"));
		}
		System.out.println("넘어온 페이지 = "+page);
		List<Teacher> list = null;
		int listcount = 0;
		String type = "teacher";
		listcount = mdao.getListCount(type);
		list = mdao.getTeacherList(page,limit);
		
		int maxpage = (listcount + limit -1)/limit;
		System.out.println("총 페이지 수 = "+ maxpage);
		
		int startpage = ((page-1)/5) * 5 +1;
		System.out.println("현재 페이지에 보여줄 시작 페이지 수 = " + startpage);
		
		// endpage : 현재 페이지 그룹에서 보여줄 마지막 페이지 수 ([10],[20],[30] 등..)
		int endpage = startpage + 5 -1;
		System.out.println("현재 페이지에 보여줄 마지막 페이지 수 = "+endpage);
		
		if(endpage>maxpage) endpage = maxpage;
		
		request.setAttribute("page", page);	// 현재 페이지 수
		request.setAttribute("maxpage", maxpage); // 최대 페이지 수		
		// 현재 페이지에 표시할 첫 페이지 수 
		request.setAttribute("startpage", startpage);		
		// 현재 페이지에 표시할 끝 페이지 수 
		request.setAttribute("endpage", endpage);
		
		request.setAttribute("listcount", listcount); // 총 글의 수
		// 해당 페이지의 글 목록을 갖고 있는 리스트
		request.setAttribute("teacherlist", list);
		
		forward.setRedirect(false);		
		forward.setPath("admin/teacherInfo.jsp");
		return forward;
	}

}
