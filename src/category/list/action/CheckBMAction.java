package category.list.action;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import category.list.db.CategoryListDAO;

public class CheckBMAction implements Action{

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {

		request.setCharacterEncoding("UTF-8");
		// 북마크를 누른 수업의 수업코드 
		int lesson_code = Integer.parseInt(request.getParameter("lesson_code"));
		// 현재 로그인한 사용자 id
		HttpSession session = request.getSession();			
		String loginid = (String) session.getAttribute("id");
		
		System.out.println("수업코드 = "+lesson_code);
		System.out.println("유저아이디 = "+loginid);
		
		CategoryListDAO cat = new CategoryListDAO();
		boolean bm = cat.checkBookmark(lesson_code, loginid);
		
        response.setContentType("text/html;charset=UTF-8");
        response.setHeader("cache-control", "no-cache,no-store");
        PrintWriter out = response.getWriter();       
        out.print(bm);
        System.out.println("좋아요 했나? "+bm);
        return null;
	}

}
