package category.list.action;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("*.cate")
public class CateFrontController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public CateFrontController() {
        super();
        // TODO Auto-generated constructor stub
    }
    protected void doProcess(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		/*
		요청된 전체 URL중에서 포트 번호 다음부터 마지막 문자열까지 반환됩니다.
		예) http://localhose:8088/MVC_Project/login.net의 경우
			"/MVC_Project/login.net" 반환됩니다.		
		*/	
    	String RequestURI = request.getRequestURI();
    	System.out.println("RequestURI = "+RequestURI);
    	
    	// getContectPath() : 컨텍스트 경로가 반환됩니다.
    	// contextPath는 "/MVC_Project"가 반환됩니다.
    	String contextPath = request.getContextPath();
    	System.out.println("contextPath = "+contextPath);
    	
    	// RequestURI에서 컨텍스트 경로 길이 값의 인덱스 위치의 문자부터 마지막 위치 문자까지 추출합니다.
    	// command는 "/login.net"반환됩니다.
    	String command = RequestURI.substring(contextPath.length());
    	System.out.println("command = " + command);
    	
    	// 초기화
    	ActionForward forward = null;
    	Action action = null;
    	// 수업 목록 불러오기
    	if(command.equals("/popular.cate")||command.equals("/diy.cate")||command.equals("/cook.cate")
    						||command.equals("/art.cate")||command.equals("/music.cate")||command.equals("/lang.cate")
    						||command.equals("/pic.cate")) {
    		action = new LoadCategoryAction();
    		try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
    		forward.setRedirect(false);
    		forward.setPath("categoryList/lessonList.jsp");
    	}
    	
    	// 메인 시작. 정말 딱 이동만 하는듯
    	else if(command.equals("/main.cate")) {
    		forward = new ActionForward();
    		forward.setRedirect(false);	// 포워딩 방식으로 주소가 바뀌지 않아요
    		forward.setPath("main/main.jsp");
    	}
    	
    	// 인기순/신상순 정렬
    	else if(command.equals("/list.cate")) {
    		action = new SortCategoryAction();
    		try {
				forward = action.execute(request, response);
				// null이 들어오지만 이걸 어디에도 활용 안해서 상관 x
				// 페이지 이동 안하는 ajax에서만 이렇게 사용
			} catch (Exception e) {
				e.printStackTrace();
			}    
    		forward.setRedirect(false);
    		forward.setPath("categoryList/lessonList.jsp");
    	}
    	
    	// 북마크 추가
    	else if(command.equals("/bookmarkAdd.cate")) {
    		action = new BookmarkAddAction();
    		try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}    
    	}
    	
    	// 북마크 삭제 
    	else if(command.equals("/bookmarkDelete.cate")) {
    		action = new BookmarkDeleteAction();
    		try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}    
    	}
    	
    	// 북마크 했는지 확인
    	else if(command.equals("/checkBM.cate")) {
    		action = new CheckBMAction();
    		try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}    
    	}
    	
    	// 검색
    	else if(command.equals("/search.cate")) {
    		action = new SearchAction();
    		try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}    
    		forward.setRedirect(false);
    		forward.setPath("categoryList/lessonList.jsp");
    	}
    	
    	
    	
    	
    	
    	
    	
    	// forward 주소 정해지면 그쪽으로 보내버리는 실행역할
    	if(forward != null) {
    		if(forward.isRedirect()) { // 리다이렉트 됩니다.
    			response.sendRedirect(forward.getPath());    			
    		}else {	// 포워딩 됩니다.
    			RequestDispatcher dispatcher = request.getRequestDispatcher(forward.getPath());
    			dispatcher.forward(request, response);    			
    		}
    	}    	
    }

    // doProcess(request,response)메소드를 구현하여 요청이 GET방식이든 POST방식으로 전송되어오든
    // 같은 메소드에서 요청을 처리할수 있도록 하였다
 
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doProcess(request,response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
