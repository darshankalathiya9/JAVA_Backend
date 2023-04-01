package Controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Dao.CartDao;
import Dao.ProductDao;
import Model.Cart;
import Model.Product;

@WebServlet("/CartController")
public class CartController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public CartController() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		String action = request.getParameter("action");

		if (action.equalsIgnoreCase("Add to Cart")) {
			Cart c = new Cart();
			c.setCustomerID(Integer.parseInt(request.getParameter("CustomerID")));
			c.setPID(Integer.parseInt(request.getParameter("PID")));

			Product p = ProductDao.getProductByPID(Integer.parseInt(request.getParameter("PID")));
			c.setPName(p.getPName());
			c.setPPrice(p.getPPrice());
			c.setPQuantity(1);
			c.setTotalPrice(p.getPPrice());
			c.setPayment_Status("Pending");

			CartDao.InsertCart(c);
			response.sendRedirect("Customer-Home.jsp");
		}
	}                                                                  

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		int CID = Integer.parseInt(request.getParameter("CID"));
		int PQuantity = Integer.parseInt(request.getParameter("PQuantity"));
		System.out.println(CID +""+PQuantity);
		
		Cart c= CartDao.getCartByCID(CID);
		Product p = ProductDao.getProductByPID(c.getPID());
		
		int Total = PQuantity*c.getPPrice();
		Cart c1 = new Cart();
		c1.setCID(CID);
		c1.setPQuantity(PQuantity);
		c1.setTotalPrice(Total);
		
		CartDao.updateCart(c1);
		response.sendRedirect("Customer-Cart.jsp");
	}
}
