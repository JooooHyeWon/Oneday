package review.action;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import userInfo.db.UserInfoDAO;


@WebServlet("*.rv")
public class ReviewFrontController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	public ReviewFrontController()
	{
        super();
    }
	
	private void doProcess(HttpServletRequest request, HttpServletResponse response) 
					throws ServletException, IOException{
		String RequestURI = request.getRequestURI();
		System.out.println("RequestURI = " + RequestURI);

		String contextPath = request.getContextPath();
		System.out.println("contextPath = " + contextPath);
		
		String command = RequestURI.substring(contextPath.length());
		System.out.println("command = " + command);
		ActionForward forward = null;
		Action action = null;
		
		
		if(command.equals("/reviewWrite.rv")) {
			action = new ReviewWriteAction();
			try {
				forward = action.execute(request, response);
			}catch(Exception e) {
				e.printStackTrace();
			}
		} else if(command.equals("/reviewDelete.rv")) {
			forward = new ActionForward();
			forward.setRedirect(false);
			forward.setPath("lesson/review_delete.jsp");
			
		} else if (command.equals("/reviewDeleteAction.rv")) {
			action = new ReviewDeleteAction();
			try {
				forward = action.execute(request, response);
			}catch(Exception e) {
				e.printStackTrace();
			}
		}
		
		
		
		/*else if(command.equals("/passchange.ue")) {
			forward = new ActionForward();
			forward.setRedirect(false);
			forward.setPath("User/passwordchange.jsp");
		}*/
		
		if(forward != null) {
			if(forward.isRedirect()) {
				response.sendRedirect(forward.getPath());
			}else {
				RequestDispatcher dispatcher = 
						request.getRequestDispatcher(forward.getPath());
				dispatcher.forward(request, response);
			}
		}
	}
	

	protected void doGet(HttpServletRequest request,
						 HttpServletResponse response) throws ServletException, IOException
	{
		doProcess(request,response);
	}

	protected void doPost(HttpServletRequest request,
						  HttpServletResponse response) throws ServletException, IOException
	{
		doProcess(request,response);
		
	}
}

