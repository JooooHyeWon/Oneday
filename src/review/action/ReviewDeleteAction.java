package review.action;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import review.db.ReviewDAO;


public class ReviewDeleteAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html; charset=utf-8");
		
		boolean result = false;
		boolean usercheck = false;
		
		int num = Integer.parseInt(request.getParameter("num"));
		
		ReviewDAO rdao = new ReviewDAO();
		
		usercheck = rdao.isReviewWriter(num, request.getParameter("user_pass"));
		
		System.out.println("리뷰코드 = " + num);
		
		//비밀번호 일치하지 않는 경우
		if (usercheck == false) {
			PrintWriter out = response.getWriter();
			out.println("<script>");
			out.println("alert('비밀번호가 다릅니다.');");
			out.println("history.back();");
			out.println("</script>");
			out.close();
					
			return null;
		}
		
		result = rdao.reviewDelete(num);
		
		ActionForward forward = new ActionForward();
		
		if(result == false) {
			System.out.println("리뷰 삭제 실패");
			forward.setRedirect(false);
			request.setAttribute("message", "리뷰 삭제 실패입니다.");
			forward.setPath("error/error.jsp");
			return forward;
		}
		
		//삭제 처리 성공한 경우 - 글 목록 보기 요청을 전송하는 부분입니다.
		System.out.println("리뷰 삭제 성공");
		response.setContentType("text/html;charset=utf-8");
		PrintWriter out = response.getWriter();
		out.println("<script>");
		out.println("alert('리뷰가 삭제 되었습니다.');");
		out.println("location.href='main.net';");
		out.println("</script>");
		out.close();
		return null;
	}
}