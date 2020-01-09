package lesson.action;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import gallery.db.GalleryDAO;
import lesson.db.Lesson;
import lesson.db.LessonDAO;


public class LessonDeletePageAction implements Action{

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		// TODO Auto-generated method stub
		ActionForward forward = new ActionForward();
		request.setCharacterEncoding("UTF-8");

		
		HttpSession session=request.getSession();
		String id=(String)session.getAttribute("id");
		
	
		Map<Integer,String> gmap=new HashMap<Integer,String>();
		
		LessonDAO ldao=new LessonDAO();
		List<Lesson> lessonlist=ldao.getlessonlist(id);
		for(Lesson lesson : lessonlist) {
			
			
			GalleryDAO gdao=new GalleryDAO();
			String pic_name=gdao.getgallerylist(lesson.getLesson_code());
			
			gmap.put(lesson.getLesson_code(),pic_name);

		}
		
		request.setAttribute("list",lessonlist );
		request.setAttribute("gmap", gmap);
		
		
		forward.setRedirect(false);
		forward.setPath("lesson/delete_lesson.jsp");
		return forward;
	}

}
