package category.list.action;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import category.list.db.CategoryListDAO;
import gallery.db.Gallery;
import lesson.db.Lesson;

public class SortCategoryAction implements Action{

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		ActionForward forward = new ActionForward();
		request.setCharacterEncoding("UTF-8");
		String category = request.getParameter("cate");
		String sort = request.getParameter("sort");
		request.setAttribute("cate", category);		
		
		System.out.println("정렬하러 간다");
		System.out.println("정렬 할 카테고리"+category);
		
		CategoryListDAO cat = new CategoryListDAO();
		List<Lesson> Lesson = cat.loadLessonList(category, sort);
		List<Gallery> mainPic = cat.loadLessonPic();
		request.setAttribute("Lesson", Lesson);
		request.setAttribute("mainPic", mainPic);
					
		return forward;			
	}

}
