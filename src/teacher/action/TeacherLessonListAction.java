package teacher.action;

import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import gallery.db.Gallery;
import gallery.db.GalleryDAO;
import lesson.db.Lesson;
import lesson.db.LessonDAO;
import schedule.db.Schedule;
import schedule.db.ScheduleDAO;

public class TeacherLessonListAction implements Action{

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		// TODO Auto-generated method stub
		
		
		request.setCharacterEncoding("UTF-8");

		response.setContentType("text/html;charset=UTF-8");
		PrintWriter out = response.getWriter();
		
		HttpSession session=request.getSession();
		String id=(String)session.getAttribute("id");
		
		LessonDAO ldao=new LessonDAO();
		
		int page=1;
		int limit=9;
		
		if(request.getParameter("page")!=null) {
			page=Integer.parseInt(request.getParameter("page"));
		}
		System.out.println("넘어온 페이지 = "+page);
		
		if(request.getParameter("limit")!=null) {
			limit=Integer.parseInt(request.getParameter("limit"));
		}
		System.out.println("넘어온 limit = " + limit);
		
		int listcount=ldao.getListCount(id);
		List<Lesson> lessonlist=ldao.getlessonlist(id,page,limit);
		
		int maxpage=(listcount+limit-1)/limit;
		System.out.println("총 페이지 수 = "+maxpage);
		
		int startpage=((page-1)/9)*9+1;
		System.out.println("현재 페이지에  보여줄 시작 페이지 수 ="+startpage);
		
		int endpage=startpage + 9-1;
		System.out.println("현재 페이지에 보여줄 마지막 페이지 수 = "+endpage);
		
		if(endpage>maxpage) endpage=maxpage;
		
		String state=request.getParameter("state");
		if(state==null) {
			System.out.println("state==null");
			
			request.setAttribute("page", page);
			request.setAttribute("maxpage", maxpage);
			request.setAttribute("startpage", startpage);
			request.setAttribute("endpage", endpage);
			
			request.setAttribute("listcount", listcount);
			
			Map<Integer,String> gmap=new HashMap<Integer,String>();
			
			
			
			for(Lesson lesson : lessonlist) {
				
				
				GalleryDAO gdao=new GalleryDAO();
				String pic_name=gdao.getgallerylist(lesson.getLesson_code());
				
				gmap.put(lesson.getLesson_code(),pic_name);

			}
			request.setAttribute("gmap", gmap);
			request.setAttribute("list", lessonlist);
			request.setAttribute("limit", limit);
			ActionForward forward = new ActionForward();
			forward.setRedirect(false);
			forward.setPath("teacher/lessonlist.jsp");
			return forward;
			
		}
	
		
		return null;
	}

}
