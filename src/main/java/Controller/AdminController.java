package Controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/AdminController")
public class AdminController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public AdminController() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		String Email = "darshankalathiya963@gmail.com";
		String Password = "AdminDK@999";
		
		String action = request.getParameter("action");
		
		if (action.equalsIgnoreCase("Login")) {
			String Email1 = request.getParameter("Email");
			String Password1 = request.getParameter("Password");
			
			if (Email1.equals(Email) && Password1.equals(Password)) {
				request.setAttribute("Email", Email);
				request.getRequestDispatcher("Admin-Home.jsp").forward(request, response);
			} else {
				request.setAttribute("msg", "Password is Incorrect.");
				request.getRequestDispatcher("Admin-Login.jsp").forward(request, response);
			}
		}
		
		
	
	}
}
