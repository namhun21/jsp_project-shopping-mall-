package controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import pagelist.vo.OrderPageList;
import order.dao.OrderDAO;

/**
 * Servlet implementation class OrderManageController
 */
@WebServlet("/ordermanage")
public class OrderManageController extends HttpServlet {
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		// 현재페이지 번호
		int orderCurrentPage = 1; // 초기값 
		// 목록개수
		int orderPageSize = 3;
		// 블록크기
		int orderBlockSize = 10;
		try{
			orderCurrentPage = Integer.parseInt(request.getParameter("orderCurrentPage"));
			System.out.println("orderCurrentPage:"+orderCurrentPage);
			orderPageSize = Integer.parseInt(request.getParameter("orderPageSize"));
			System.out.println("orderPageSize: "+orderPageSize);
			orderBlockSize = Integer.parseInt(request.getParameter("orderBlockSize"));
			System.out.println("orderBlockSize: "+orderBlockSize);
		}catch(Exception e){ 
			;
		}
		
//		request.setAttribute("orderCurrentPage", orderCurrentPage);
//		request.setAttribute("orderPageSize", orderPageSize);
//		request.setAttribute("orderBlockSize", orderBlockSize);
		OrderDAO orderDAO = OrderDAO.getInstance();
		OrderPageList listAll = null;
		int count = 0;
		try {
			listAll = orderDAO.listAll(orderCurrentPage, orderPageSize, orderBlockSize);
			count = orderDAO.getCount();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		request.setAttribute("page", listAll);
		request.setAttribute("count", count);
		RequestDispatcher rd = request.getRequestDispatcher("WEB-INF/jsp/admin/order-manage.jsp");
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
