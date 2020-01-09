package main.net;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import lesson.action.LessonAddAction;


@WebServlet("*.net")
public class MainFrontController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public MainFrontController() {
		super();
		// TODO Auto-generated constructor stub
	}

	private void doProcess(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		/*
		 * 요청된 전체 URL중에서 포트 번호 다음부터 마지막 문자열까지 반환됩니다. 예)
		 * http://localhost:8088/JspProject/login.net인 경우, "/JspProject/login.net"
		 * 반환됩니다.
		 */
		String RequestURI = request.getRequestURI();
		System.out.println("RequestURI = " + RequestURI);

		//getContextPath() : 컨텍스트 경로가 반환됩니다.
		//contextPath는 "/JspProject"가 반환됩니다.
		String contextPath = request.getContextPath();
		System.out.println("contextPath = " + contextPath);

		//RequestURI에서 컨텍스트 경로 길이 값의 인덱스 위치의 문자부터 마지막 위치 문자까지 추출
		//command는 "/login.net" 반환됩니다.
		String command = RequestURI.substring(contextPath.length());
		System.out.println("command = " + command);

		//초기화
		ActionForward forward = null;
		Action action = null;

		if (command.equals("/main.net")) {
			forward = new ActionForward();
			forward.setRedirect(false); // 주소 변경 없이 jsp 페이지의 내용을 보여줍니다. (dispatcher)
			forward.setPath("main/main.jsp");

		}else if (command.equals("/idcheck.net")) {
			action = new IdCheckAction();
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}else if (command.equals("/logout.net")) {
			action = new LogoutAction();
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}else if (command.equals("/login.net")) {
			action = new LoginAction();
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}else if (command.equals("/join.net")) {
			action = new JoinAction();
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}else if(command.equals("/lesson.net")) {
    		action = new LessonManageAction();
    		try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
    	}
    	// 강사 관리
    	else if(command.equals("/teacher.net")) {
    		action = new TeacherManageAction();
    		try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
    	}
    	// 회원 관리
    	else if(command.equals("/userInfo.net")) {
    		action = new UserManageAction();
    		try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
    	}
    	// 수업 삭제
    	else if(command.equals("/delete.net")) {
    		System.out.println("컨트롤러 옴");
    		action = new Manage_deleteAction();
    		
    		try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
    	}

		/*
		 * else if (command.equals("/logout.net")) { action = new LogoutAction(); try {
		 * forward = action.execute(request, response); } catch (Exception e) {
		 * e.printStackTrace(); } } else if (command.equals("/member_update.net")) {
		 * action = new Member_updateAction(); try { forward = action.execute(request,
		 * response); } catch (Exception e) { e.printStackTrace(); } }
		 */

		if (forward != null) {

			if (forward.isRedirect()) { // 리다이렉트 됩니다.
				response.sendRedirect(forward.getPath());
			} else { // 포워딩 됩니다.
				RequestDispatcher dispatcher = request.getRequestDispatcher(forward.getPath());
				dispatcher.forward(request, response);
			}
		}
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		doProcess(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		doProcess(request, response);
	}

}
