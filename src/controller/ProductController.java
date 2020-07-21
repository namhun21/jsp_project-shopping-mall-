package controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class ProductController
 */
@WebServlet("/Product")
public class ProductController extends HttpServlet {
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		RequestDispatcher rd;
		String a = "All";
		if (request.getParameter("value").equals("Top")) {
			System.out.println("1");
			a = request.getParameter("value");
			request.setAttribute("a", "Top");
		} else if (request.getParameter("value").equals("Bottom")) {
			System.out.println("2");
			request.setAttribute("a", "Bottom");
		} else if (request.getParameter("value").equals("Shoes")) {
			System.out.println("3");
			request.setAttribute("a", "Shoes");
		} else {
			System.out.println("4");
			request.setAttribute("a", "All");
		}

		rd = request.getRequestDispatcher("WEB-INF/jsp/client/product.jsp");
		rd.forward(request, response);
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
