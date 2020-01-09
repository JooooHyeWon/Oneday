package category.list.action;

import java.io.PrintWriter;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import category.list.db.CategoryListDAO;
import lesson.db.Lesson;

public class BookmarkDeleteAction implements Action{

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		request.setCharacterEncoding("UTF-8");
		// 북마크를 누른 수업의 수업코드
		int lesson_code = Integer.parseInt(request.getParameter("lesson_code"));
		// 현재 로그인한 사용자 id
		HttpSession session = request.getSession();			
		String loginid = (String) session.getAttribute("id");
		
		CategoryListDAO cat = new CategoryListDAO();
		cat.deleteBookmark(lesson_code, loginid);
		System.out.println("그다음 삭제 액션도 잘 돌아옴");
		// 북마크 성공 / 취소 구별은 LoginProcessAction 참고
		String message = "좋아요 목록에서 삭제되었습니다.";	
		
		String category=request.getParameter("cate");
		List<Lesson> Lesson = cat.loadLessonList(category);
		request.setAttribute("Lesson", Lesson);
		
		
		response.setContentType("text/html;charset=utf-8");
		PrintWriter out = response.getWriter();
		out.println("<script>");
		out.println("alert('"+message+"');");
		out.println("location.href='"+category+".cate?cate="+category+"';");
		out.println("</script>");
		out.close();
		
		return null;			
	}

}
