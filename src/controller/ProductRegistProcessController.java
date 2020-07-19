package controller;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Enumeration;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

import product.dao.ProductDAO;

/**
 * Servlet implementation class ProductRegistProcessController
 */
@WebServlet("/productregistprocess")
public class ProductRegistProcessController extends HttpServlet {
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
	
		ProductDAO productdao = ProductDAO.getInstance();
		int pId =  productdao.selectGetProductId() + 1;
		System.out.println("이것은 pid"+pId);
		String pName = request.getParameter("pName");
		String pContent = request.getParameter("pContent");
		int categoryCode = Integer.parseInt(request.getParameter("categoryCode"));
		int price = Integer.parseInt(request.getParameter("price"));
		int stock = Integer.parseInt(request.getParameter("stock"));
		String product_img = request.getParameter("img_name");
		System.out.println(product_img);
		int result = 0;
		//int result = productdao.insertProduct(pId, pName, pContent, categoryCode, price, stock, product_img);
		
		if(result!= 0) {
			response.sendRedirect("productmanage");
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
