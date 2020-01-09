package teacher.action;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("*.te")
public class TeacherFrontController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public TeacherFrontController() {
		super();
	}

	private void doProcess(HttpServletRequest request, 
							HttpServletResponse response)
			throws ServletException, IOException {
		
		/*
			요청된 전체 URL중에서 포트 번호 다음부터 마지막 문자열까지 반환됩니다.
			예) http://localhost:8088/JspProject/login.net인 경우,
			"/JspProject/login.net" 반환됩니다. 
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
		
		if (command.equals("/teacherInfoMain.te")) {
			forward = new ActionForward();
			forward.setRedirect(false); //주소 변경 없이 jsp 페이지의 내용을 보여줍니다. (dispatcher)
			forward.setPath("teacher/myProfile.jsp");
			
		}else if(command.equals("/teacherInfo.te")) {
			action = new TeacherInfoAction();
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
			
		} else if (command.equals("/teacherInfoUpdate.te")){
			action = new TeacherInfoUpdateAction();
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
			
		}else if (command.equals("/teacherPassCheck.te")) {
			forward = new ActionForward();
			forward.setRedirect(false); //주소 변경 없이 jsp 페이지의 내용을 보여줍니다. (dispatcher)
			forward.setPath("teacher/passchange.jsp");
			
		}else if (command.equals("/teacherPassUpdate.te")) {
			action = new TeacherPassUpdateAction();
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
			
		}else if (command.equals("/teacheraddlesson.te")) {
			forward = new ActionForward();
			forward.setRedirect(false); //주소 변경 없이 jsp 페이지의 내용을 보여줍니다. (dispatcher)
			forward.setPath("teacher/lesson_add_btn.jsp");
			
		}else if (command.equals("/teacherLessonList.te")) {
			action = new TeacherLessonListAction();
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
			
		}else if (command.equals("/teacherLessonListView.te")) {
			forward = new ActionForward();
			forward.setRedirect(false); //주소 변경 없이 jsp 페이지의 내용을 보여줍니다. (dispatcher)
			forward.setPath("teacher/lessonlist.jsp");
			
		}
		
		/*
		else if (command.equals("/logout.net")) {
			action = new LogoutAction();
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
		} else if (command.equals("/member_update.net")) {
			action = new Member_updateAction();
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				 e.printStackTrace(); 
			}
		}  
		*/
		
		if (forward != null) {
			
			if (forward.isRedirect()) { //리다이렉트 됩니다.
				response.sendRedirect(forward.getPath());
			} else { //포워딩 됩니다.
				RequestDispatcher dispatcher = request.getRequestDispatcher(forward.getPath());
				dispatcher.forward(request, response);
			}
		}
	}

	
	
	// doProcess(request, response) 메서드를 구현하여
	// 요청이 GET방식이든 POST방식으로 전송이 되어 오든 같은 메서드에서 요청을 처리할 수 있도록 함
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		doProcess(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		doProcess(request, response);
	}

}
