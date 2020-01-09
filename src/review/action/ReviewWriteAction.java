package review.action;
import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import userInfo.db.UserInfoDAO;

public class ReviewWriteAction implements Action {

   @Override
   public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
     int check = 0;
     int star_point= 0;
     
      HttpSession session = request.getSession();
      response.setContentType("text/html; charset=UTF-8");
      PrintWriter out = response.getWriter();
      
      int lesson_code = Integer.parseInt(request.getParameter("code"));
      String session_id = (String)session.getAttribute("id");
      String review_content = request.getParameter("reaview_content");
      review_content = review_content.replace("\r\n","<br>");
      
         if(request.getParameter("star") == null) {
            star_point = 0;
         }else {
            star_point = Integer.parseInt(request.getParameter("star"));
         }
      UserInfoDAO userDAO = new UserInfoDAO();
      boolean review_check = userDAO.userReviewCheck(lesson_code, session_id);
      System.out.println(review_check);
      if(review_check == false) {
         check = userDAO.reviewWrite(lesson_code,session_id,review_content,star_point);
         System.out.println(check);
         if(check == 1) {
                System.out.println("리뷰 작성 성공");
                out.println("리뷰를 작성했습니다.");
                out.close();
         }else {
                System.out.println("리뷰 작성 실패");
                out.println("리뷰 작성에 실패했습니다.");
                out.close();
         }
      }else {
         System.out.println("등록 된 리뷰 감지");
            out.println("등록 된 리뷰가 있습니다!");
           out.close();
      } 
      return null;
   }
}