package Controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Dao.WishlistDao;
import Model.Wishlist;

@WebServlet("/WishlistController")
public class WishlistController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public WishlistController() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String action = request.getParameter("action");

		if (action.equalsIgnoreCase("Add to Wishlist")) {
			Wishlist w = new Wishlist();
			w.setPID(Integer.parseInt(request.getParameter("PID")));
			w.setCustomerID(Integer.parseInt(request.getParameter("CustomerID")));
			WishlistDao.InsertWishList(w);
			response.sendRedirect("Customer-Home.jsp");
		}
	}

}
