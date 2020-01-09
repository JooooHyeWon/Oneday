package category.list.action;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import category.list.db.CategoryListDAO;
import gallery.db.Gallery;
import lesson.db.Lesson;

public class LoadCategoryAction implements Action{

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {

		ActionForward forward = new ActionForward();
		request.setCharacterEncoding("UTF-8");
		
		String category = request.getParameter("cate");
		System.out.println("현재 카테고리 = "+category);
		request.setAttribute("cate", category);			
		
		HttpSession session = request.getSession();
		String id = (String) session.getAttribute("id");
		System.out.println("현재 아이디 = "+id);
		
		CategoryListDAO cat = new CategoryListDAO();
		List<Lesson> Lesson = cat.loadLessonList(category);
		List<Gallery> mainPic = cat.loadLessonPic();
		request.setAttribute("Lesson", Lesson);
		request.setAttribute("mainPic", mainPic);
					
		
		return forward;				
	}

}
