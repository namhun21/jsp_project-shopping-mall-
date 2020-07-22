package controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import product.dao.ProductDAO;
import product.dto.ProductCommentDTO;
import product.dto.ProductDTO;

@WebServlet("/ProductDetail")
public class ProductDetailController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		String productID = request.getParameter("pid");

		ProductDAO dao = ProductDAO.getInstance();
		ProductDTO pdto = dao.selectProduct(productID);
		ArrayList<ProductCommentDTO> cdto = dao.selectCommentAll();
		
		HttpSession session = request.getSession();
	    String uid = (String)session.getAttribute("userid");

		request.setAttribute("product", pdto);
		request.setAttribute("comment", cdto);
		request.setAttribute("userid", uid);

		RequestDispatcher rd = request.getRequestDispatcher("WEB-INF/jsp/client/product-detail.jsp");
		rd.forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
