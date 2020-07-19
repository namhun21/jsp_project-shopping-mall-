package controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import pagelist.vo.ProductPageList;
import product.dao.ProductDAO;

/**
 * Servlet implementation class ProductManageController
 */
@WebServlet("/productmanage")
public class ProductManageController extends HttpServlet {
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		// 현재페이지 번호
		int currentPage = 1; // 초기값 
		// 목록개수
		int pageSize = 3;
		// 블록크기
		int blockSize = 10;
		try{
			currentPage = Integer.parseInt(request.getParameter("currentPage"));
			System.out.println("currentpage:"+currentPage);
			pageSize = Integer.parseInt(request.getParameter("pageSize"));
			System.out.println("pageSize: "+pageSize);
			blockSize = Integer.parseInt(request.getParameter("blockSize"));
			System.out.println("blockSize: "+blockSize);
		}catch(Exception e){ 
			;
		}
		
//		request.setAttribute("currentPage", currentPage);
//		request.setAttribute("pageSize", pageSize);
//		request.setAttribute("blockSize", blockSize);
		ProductDAO productDAO = ProductDAO.getInstance();
		ProductPageList listAll = null;
		int count = 0;
		try {
			listAll = productDAO.listAll(currentPage, pageSize, blockSize);
			count = productDAO.getCount();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		request.setAttribute("page", listAll);
		request.setAttribute("count", count);
		RequestDispatcher rd = request.getRequestDispatcher("WEB-INF/jsp/product-manage.jsp");
		rd.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
