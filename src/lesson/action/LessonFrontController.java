package lesson.action;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("*.lesson")
public class LessonFrontController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public LessonFrontController() {
		super();
	}

	private void doProcess(HttpServletRequest request, 
							HttpServletResponse response)
			throws ServletException, IOException {
		
		
		String RequestURI = request.getRequestURI();
		System.out.println("RequestURI = " + RequestURI);
		
		String contextPath = request.getContextPath();
		System.out.println("contextPath = " + contextPath);
		
		String command = RequestURI.substring(contextPath.length());
		System.out.println("command = " + command);
		

		ActionForward forward = null;
		Action action = null;
		if(command.equals("/lessonAddForm.lesson")) {
			forward = new ActionForward();
			forward.setRedirect(false); 
			forward.setPath("lesson/add_lesson.jsp");
			
		}else if (command.equals("/LessonAdd.lesson")) {
			action = new LessonAddAction();
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
		
		} else if (command.equals("/scheduleaddpage.lesson")) {
			forward = new ActionForward();
			forward.setRedirect(false); 
			forward.setPath("lesson/add_schedule.jsp");
			
		}else if (command.equals("/scheduleadd.lesson")) {
			action = new ScheduleAddAction();
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
		
		}else if (command.equals("/lessondelete.lesson")) {
			action = new LessonDeleteAction();
			try {
				forward = action.execute(request, response);
			}catch(Exception e) {
				e.printStackTrace();
			}
		}else if (command.equals("/lessondeletepage.lesson")) {
			action = new LessonDeletePageAction();
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
		
		}else if (command.equals("/userlist.lesson")) {
			action = new UserListAction();
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
		
		}if(command.equals("/lessonInfo.lesson")) {
			action = new SelectLessonInfo();
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
			
		} else if (command.equals("/lessonInfo2.lesson")) {
			forward = new ActionForward();
			forward.setRedirect(false);
			forward.setPath("lesson/lessonInfo.jsp");
			
		} else if (command.equals("/register.lesson")) {
			action = new SelectRegisterInfo();
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}

		} else if (command.equals("/paymentPage.lesson")) {
			action = new SelectPaymentInfo();
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}

		} else if (command.equals("/payment.lesson")) {
			action = new LessonRegister();
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}

		} else if (command.equals("/gopay.lesson")) {
			action = new GoPay();
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}

		}else if (command.equals("/lessonlistcount.lesson")) {
			action = new LessonListCountAction();
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}

		}
		
		if (forward != null) {
			
			if (forward.isRedirect()) { 
				response.sendRedirect(forward.getPath());
			} else { 
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
