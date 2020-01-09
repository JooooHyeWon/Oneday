package lesson.action;

import java.io.PrintWriter;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import gallery.db.Gallery;
import gallery.db.GalleryDAO;
import lesson.db.Lesson;
import lesson.db.LessonDAO;
import review.db.Review;
import review.db.ReviewDAO;
import schedule.db.Schedule;
import schedule.db.ScheduleDAO;


public class SelectLessonInfo implements Action{

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		ActionForward forward = new ActionForward();
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html; charset=utf-8");
		LessonDAO dao = new LessonDAO();
		ScheduleDAO sdao = new ScheduleDAO();
		ReviewDAO rdao = new ReviewDAO();
		GalleryDAO gdao = new GalleryDAO();
		
		
		int lesson_code = Integer.parseInt(request.getParameter("lesson_code"));
		String sort = request.getParameter("sort");
		request.setAttribute("sort", sort);

		System.out.println("정렬은 " + sort);
		
		Lesson lesson = dao.lesson_info(lesson_code); 
		List<Schedule> schedule = sdao.schedule_info(lesson_code);
		List<Review> review = rdao.review_info(lesson_code);
		int review_cnt = rdao.review_cnt(lesson_code);
		List<Gallery> gallery = gdao.gallery_info(lesson_code);
		String main = gdao.main_pic(lesson_code);
		
		
		HttpSession session = request.getSession();
		String id = (String) session.getAttribute("id");
		System.out.println("아이디는 " + id);
		
				
		
		request.setAttribute("lesson", lesson);
		request.setAttribute("schedule", schedule);	
		request.setAttribute("gallery", gallery);
		request.setAttribute("main", main); //첫번째 사진
		
		System.out.println(main);
		
		//요약 주소 만들기
		String location = lesson.getLesson_location();
		int cut_loc = location.indexOf("로");
		String min_loc = location.substring(0, 2);	//앞 두글자
		String sum_loc = location.substring(0, cut_loc+1);	// ~~로 까지
		request.setAttribute("min_loc", min_loc);
		request.setAttribute("sum_loc", sum_loc);

		
		//요약 시간 부분 (진행 시간 계산)
		int process_h = 0;
		int process_m = 0;
		String starttime = schedule.get(0).getLesson_start();
		
		String endtime = schedule.get(0).getLesson_end();
		int s_h = Integer.parseInt(starttime.substring(0,2));	//시작시간의 시
		int s_m = Integer.parseInt(starttime.substring(3,5));	//시작시간의 분
		int e_h = Integer.parseInt(endtime.substring(0,2));		//끝시간의 시
		int e_m = Integer.parseInt(endtime.substring(3,5));		//끝시간의 분
		
		if (s_m == 0 && e_m == 0) {
			process_h = e_h - s_h;
			process_m = 0;
		} else if (s_m == 0 && e_m == 30) {
			process_h = e_h - s_h;
			process_m = 30;
		} else if (s_m == 30 && e_m == 0) {
			process_h = e_h - s_h - 1 ;
			process_m = 30;
		}
		
		request.setAttribute("process_h", process_h);
		request.setAttribute("process_m", process_m);

		
		//리뷰 별점 계산
		float review_sum = 0;	//리뷰 별점 합
		float review_avg = 0;	//리뷰 별점 합 / 리뷰 개수
		
		for(int i=0; i<review.size(); i++) {
			review_sum += review.get(i).getReview_star();
		}
		
		review_avg = review_sum / review.size();
		review_avg = Float.parseFloat(String.format("%.1f", review_sum / review.size()));
	
		request.setAttribute("review_avg", review_avg);
	
		
		//페이지
		int page = 1;
		int limit = 3;
		
		if(request.getParameter("page") != null) {
			page = Integer.parseInt(request.getParameter("page"));
		}
		
		if(request.getParameter("limit") != null) {
			limit = Integer.parseInt(request.getParameter("limit"));
		}
		
		//총 리스트 수를 받아옵니다.
		//review_cnt
		
		//리스트를 받아옵니다.
		//List<Review> reviewlist = new ArrayList<Review>();	
		
		
		
		
		review = rdao.getReviewList(page, limit, lesson_code, sort);
		int maxpage = (review_cnt + limit -1) / limit;		//총페이지수
		int startpage = ((page - 1) / 5) * 5 + 1;
		int endpage = startpage + 5 -1 ;
		
		
			
		if (endpage > maxpage) endpage = maxpage;
		
		request.setAttribute("page", page);
		request.setAttribute("maxpage", maxpage);		
		request.setAttribute("startpage", startpage);
		request.setAttribute("endpage", endpage);
		request.setAttribute("review", review);
		
		
		request.setAttribute("review_cnt", review_cnt);
		
		
		forward.setRedirect(false);
		forward.setPath("lessonInfo2.lesson");
		
		return forward;
	}

}
