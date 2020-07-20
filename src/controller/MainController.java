package controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import product.dao.ProductDAO;
import product.dto.ProductDTO;
import util.Pagination;

/**
 * Servlet implementation class MainController
 */
@WebServlet("/main")
public class MainController extends HttpServlet {

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String sortby = request.getParameter("sort_by");
		ArrayList<ProductDTO> plist = new ArrayList<ProductDTO>();

		if (sortby == null || sortby.equals("sortby")) {

			ProductDAO dao = ProductDAO.getInstance();
			plist = dao.selectProductAll();

			// page navigation
			String curPage = request.getParameter("");
			if (curPage == null) {
				curPage = "1";
			}
			int contentCnt = 9;
			int totalContentCnt = plist.size();
//			int start = Integer.parseInt(curPage) * contentCnt - (contentCnt - 1);
//			int end = Integer.parseInt(curPage) * contentCnt;

			Pagination pagination = Pagination.getInstance();
			pagination.pageInfo(Integer.parseInt(curPage), contentCnt, totalContentCnt);

			request.setAttribute("plist", plist);
			request.setAttribute("pagination", pagination);
			RequestDispatcher rd = request.getRequestDispatcher("WEB-INF/jsp/client/index.jsp");
			rd.forward(request, response);
		} else {
			ProductDAO dao = ProductDAO.getInstance();
			plist = dao.selectSortedProduct(sortby);

			// page navigation
			int contentCnt = 9;
			int totalContentCnt = plist.size();

			Pagination pagination = Pagination.getInstance();
			pagination.pageInfo(1, contentCnt, totalContentCnt);

			if (plist.isEmpty()) {
				response.sendRedirect("main");
			} else {
				request.setAttribute("plist", plist);
				request.setAttribute("pagination", pagination);
				request.setAttribute("sortby", sortby);
				RequestDispatcher rd = request.getRequestDispatcher("WEB-INF/jsp/client/category.jsp");
				rd.forward(request, response);
			}
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
