package Controller;

import java.io.IOException;
import java.util.Random;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.tomcat.util.scan.AbstractInputStreamJar;
import Dao.CustomerDao;
import Model.Customer;
import Service.Servicess;

@WebServlet("/CustomerController")
public class CustomerController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public CustomerController() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String action = request.getParameter("action");

		if (action.equalsIgnoreCase("Register")) {
			Customer c = new Customer();
			c.setName(request.getParameter("Name"));
			c.setContact(Long.parseLong(request.getParameter("Contact")));
			c.setAddress(request.getParameter("Address"));
			c.setEmail(request.getParameter("Email"));
			c.setPassword(request.getParameter("Password"));
			
			CustomerDao.insertCustomer(c);
			
			request.setAttribute("msg", "Account Registered Succesfully.");
			request.getRequestDispatcher("Customer-Login.jsp").forward(request, response);
		}

		else if (action.equalsIgnoreCase("Login")) {
			Customer c = new Customer();
			c.setEmail(request.getParameter("Email"));
			c.setPassword(request.getParameter("Password"));
			Customer c1 = CustomerDao.loginCustomer(c);
			if (c1 == null) {
				request.setAttribute("msg", "Password is Incorrect.");
				request.getRequestDispatcher("Customer-Login.jsp").forward(request, response);
			} else {
				HttpSession session = request.getSession();
				session.setAttribute("data", c1);
				request.getRequestDispatcher("Customer-Home.jsp").forward(request, response);
			}
		}

		else if (action.equalsIgnoreCase("Update")) {
			Customer c = new Customer();
			c.setID(Integer.parseInt(request.getParameter("ID")));
			c.setName(request.getParameter("Name"));
			c.setContact(Long.parseLong(request.getParameter("Contact")));
			c.setAddress(request.getParameter("Address"));
			c.setEmail(request.getParameter("Email"));

			CustomerDao.updateProfile(c);
			HttpSession session = request.getSession();
			session.setAttribute("data", c);
			request.getRequestDispatcher("Customer-Home.jsp").forward(request, response);
		}

		else if (action.equalsIgnoreCase("Change Password")) {
			Customer c = new Customer();
			int ID = Integer.parseInt(request.getParameter("ID"));
			String OP = request.getParameter("OP");
			String NP = request.getParameter("NP");
			String CNP = request.getParameter("CNP");

			boolean flag = CustomerDao.checkOldPassword(ID, OP);

			if (flag == true) {
				if (NP.equals(CNP)) {
					CustomerDao.changePassword(ID, NP);
					response.sendRedirect("Customer-Home.jsp");
				} else {
					request.setAttribute("msg1", "New Password and Confirm New Password Doesn't Matched.");
					request.getRequestDispatcher("Customer-Change-Password.jsp").forward(request, response);
				}
			} else {
				request.setAttribute("msg", "Old Password Incorrect.");
				request.getRequestDispatcher("Customer-Change-Password.jsp").forward(request, response);
			}
		}

		else if (action.equalsIgnoreCase("GET OTP")) {
			String Email = request.getParameter("Email");
			boolean flag = CustomerDao.checkEmail(Email);

			if (flag == true) {
				Servicess s = new Servicess();
				Random r = new Random();
				int num = r.nextInt(999999);
				System.out.println(num);
				s.sendMail(Email, num);

				request.setAttribute("Email", Email);
				request.setAttribute("OTP", num);
				request.getRequestDispatcher("Customer-Verify-OTP.jsp").forward(request, response);
			} else {

			}
		}

		else if (action.equalsIgnoreCase("Verify")) {
			String Email = request.getParameter("Email");
			int OTP1 = Integer.parseInt(request.getParameter("OTP1"));
			int OTP2 = Integer.parseInt(request.getParameter("OTP2"));

			if (OTP1 == OTP2) {
				request.setAttribute("Email", Email);
				request.getRequestDispatcher("Customer-New-Password.jsp").forward(request, response);
			} else {
				request.setAttribute("Email", Email);
				request.setAttribute("OTP", OTP1);
				request.setAttribute("msg", "OTP Not Matched.");
				request.getRequestDispatcher("Customer-Verify-OTP.jsp").forward(request, response);
			}
		}

		else if (action.equalsIgnoreCase("Update Password")) {
			String Email = request.getParameter("Email");
			String NP = request.getParameter("NP");
			String CNP = request.getParameter("CNP");

			if (NP.equals(CNP)) {
				CustomerDao.changeNewPassword(Email, NP);
				response.sendRedirect("Customer-Login.jsp");
			} else {
				request.setAttribute("msg", "New Password and Conform New Password are Not Matched.");
				request.setAttribute("Email", Email);
				request.getRequestDispatcher("Customer-New-Password.jsp").forward(request, response);
			}
		}
		
		else if (action.equalsIgnoreCase("Admin Update")) {
			Customer c = new Customer();
			c.setID(Integer.parseInt(request.getParameter("ID")));
			c.setName(request.getParameter("Name"));
			c.setContact(Long.parseLong(request.getParameter("Contact")));
			c.setAddress(request.getParameter("Address"));
			c.setEmail(request.getParameter("Email"));

			CustomerDao.updateProfile(c);
			response.sendRedirect("Admin-Customer-List.jsp");
		}
	}
}