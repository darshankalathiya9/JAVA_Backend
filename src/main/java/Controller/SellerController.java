package Controller;

import java.io.IOException;
import java.util.Random;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import Dao.SellerDao;
import Model.Seller;
import Service.Servicess;

@WebServlet("/SellerController")
public class SellerController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public SellerController() {
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
			Seller s = new Seller();
			s.setName(request.getParameter("Name"));
			s.setContact(Long.parseLong(request.getParameter("Contact")));
			s.setAddress(request.getParameter("Address"));
			s.setEmail(request.getParameter("Email"));
			s.setPassword(request.getParameter("Password"));
			SellerDao.InsertSeller(s);

			request.setAttribute("msg1", "Account Registered Succesfully.");
			request.getRequestDispatcher("Seller-Login.jsp").forward(request, response);
		}

		else if (action.equalsIgnoreCase("Login")) {
			Seller s = new Seller();
			s.setEmail(request.getParameter("Email"));
			s.setPassword(request.getParameter("Password"));
			Seller s1 = SellerDao.LoginSeller(s);

			if (s1 == null) {
				request.setAttribute("msg1", "Password is Incorrect.");
				request.getRequestDispatcher("Seller-Login.jsp").forward(request, response);
			} else {
				HttpSession session = request.getSession();
				session.setAttribute("data", s1);
				request.getRequestDispatcher("Seller-Home.jsp").forward(request, response);
			}
		}

		else if (action.equalsIgnoreCase("Update")) {
			Seller s = new Seller();
			s.setID(Integer.parseInt(request.getParameter("ID")));
			s.setName(request.getParameter("Name"));
			s.setContact(Long.parseLong(request.getParameter("Contact")));
			s.setAddress(request.getParameter("Address"));
			s.setEmail(request.getParameter("Email"));

			SellerDao.updateProfile(s);
			HttpSession session = request.getSession();
			session.setAttribute("data", s);
			request.getRequestDispatcher("Seller-Home.jsp").forward(request, response);
		}

		else if (action.equalsIgnoreCase("Change Password")) {
			Seller s = new Seller();
			int ID = Integer.parseInt(request.getParameter("ID"));
			String OP = request.getParameter("OP");
			String NP = request.getParameter("NP");
			String CNP = request.getParameter("CNP");

			boolean flag = SellerDao.checkOldPassword(ID, OP);

			if (flag == true) {
				if (NP.equals(CNP)) {
					SellerDao.changePassword(ID, NP);
					response.sendRedirect("Seller-Home.jsp");
				} else {
					request.setAttribute("msg1", "New Password and Conform Password Doesn't Matched.");
					request.getRequestDispatcher("Seller-Change-Password.jsp").forward(request, response);
				}
			} else {
				request.setAttribute("msg", "Old Password Incorrect");
				request.getRequestDispatcher("Seller-Change-Password.jsp").forward(request, response);
			}
		}

		else if (action.equalsIgnoreCase("GET OTP")) {
			String Email = request.getParameter("Email");
			System.out.println(Email);
			boolean flag = SellerDao.checkEmail(Email);
			System.out.println(flag);
			if (flag == true) {
				Servicess s = new Servicess();
				Random r = new Random();
				int num = r.nextInt(999999);
				System.out.println(num);
				s.sendMail(Email, num);
				request.setAttribute("Email", Email);
				request.setAttribute("OTP", num);
				request.getRequestDispatcher("Seller-Verify-OTP.jsp").forward(request, response);

			} else {

			}
		}

		else if (action.equalsIgnoreCase("Verify")) {
			String Email = request.getParameter("Email");
			int OTP1 = Integer.parseInt(request.getParameter("OTP1"));
			int OTP2 = Integer.parseInt(request.getParameter("OTP2"));

			if (OTP1 == OTP2) {
				request.setAttribute("Email", Email);
				request.getRequestDispatcher("Seller-New-Password.jsp").forward(request, response);
			} else {
				request.setAttribute("Email", Email);
				request.setAttribute("OTP", OTP1);
				request.setAttribute("msg", "OTP Not Matched.");
				request.getRequestDispatcher("Seller-Verify-OTP.jsp").forward(request, response);
			}
		}

		else if (action.equalsIgnoreCase("Update Password")) {
			String Email = request.getParameter("Email");
			String NP = request.getParameter("NP");
			String CNP = request.getParameter("CNP");

			if (NP.equals(CNP)) {
				SellerDao.changeNewPassword(Email, NP);
				response.sendRedirect("Seller-Login.jsp");
			} else {
				request.setAttribute("msg", "New Password and confirm New Password are not Matched.");
				request.setAttribute("Email", Email);
				request.getRequestDispatcher("Seller-New-Password.jsp").forward(request, response);
			}
		}
		
		else if (action.equalsIgnoreCase("Admin Update")) {
			Seller s = new Seller();
			s.setID(Integer.parseInt(request.getParameter("ID")));
			s.setName(request.getParameter("Name"));
			s.setContact(Long.parseLong(request.getParameter("Contact")));
			s.setAddress(request.getParameter("Address"));
			s.setEmail(request.getParameter("Email"));

			SellerDao.updateProfile(s);
			response.sendRedirect("Admin-Seller-List.jsp");
		}
	}
}