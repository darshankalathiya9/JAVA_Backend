package Controller;

import java.io.File;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import Dao.ProductDao;
import Model.Product;

@WebServlet("/ProductController")
@MultipartConfig(fileSizeThreshold = 1024 * 1024 * 512, maxFileSize = 1024 * 1024 * 512, maxRequestSize = 1024 * 1024
		* 512)
public class ProductController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public ProductController() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	private String extractfilename(Part file) {
		String cd = file.getHeader("content-disposition");
		System.out.println(cd);
		String[] items = cd.split(";");
		for (String string : items) {
			if (string.trim().startsWith("filename")) {
				return string.substring(string.indexOf("=") + 2, string.length() - 1);
			}
		}
		return "";
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String action = request.getParameter("action");
		System.out.println(action);

		if (action.equalsIgnoreCase("Upload")) {
			String savePath = "G:\\JAVA_BackEnd\\Bootie\\src\\main\\webapp\\Image";
			File fileSaveDir = new File(savePath);
			if (!fileSaveDir.exists()) {
				fileSaveDir.mkdir();
			}

			Part file1 = request.getPart("Image");
			String fileName = extractfilename(file1);
			file1.write(savePath + File.separator + fileName);
			String filePath = savePath + File.separator + fileName;

			String savePath2 = "G:\\JAVA_BackEnd\\Bootie\\src\\main\\webapp\\Image";
			File imgSaveDir = new File(savePath2);

			if (!imgSaveDir.exists()) {
				imgSaveDir.mkdir();
			}

			Product p = new Product();
			p.setSID(Integer.parseInt(request.getParameter("SID")));
			p.setImage(fileName);
			p.setPName(request.getParameter("PName"));
			p.setPPrice(Integer.parseInt(request.getParameter("PPrice")));
			p.setPCategory(request.getParameter("PCategory"));
			p.setPDesc(request.getParameter("PDesc"));
			System.out.println(p);
			
			ProductDao.uploadProduct(p);
			System.out.println("Product Uploaded succesfully.");
			response.sendRedirect("Seller-Home.jsp");
		}
	}
}