package controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cart.dao.CartDAO;
import cart.dto.CartDTO;
import order.dao.OrderDAO;

@WebServlet("/Cart2")
public class Cart2Controller extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		CartDAO cdao = CartDAO.getInstance();
		String uid = "js";
		OrderDAO odao = OrderDAO.getInstance();
		ArrayList<CartDTO> clist;
		CartDTO data = new CartDTO();
		clist = cdao.cartSelect(uid);

		RequestDispatcher rd;

		if (request.getParameter("value") != null && request.getParameter("value").equals("delete")) {
			cdao.cartDelete(uid);
			rd = request.getRequestDispatcher("WEB-INF/jsp/client/cart2.jsp");
			rd.forward(request, response);
		} else if (request.getParameter("value") != null && request.getParameter("value").equals("complete")) {
			cdao.cartDelete(uid);
			rd = request.getRequestDispatcher("WEB-INF/jsp/client/cart2.jsp");
			rd.forward(request, response);
		}

		else if (request.getParameter("value") != null && request.getParameter("value").equals("order")) {
			cdao.cartDelete(uid);
			rd = request.getRequestDispatcher("WEB-INF/jsp/client/cart2.jsp");
			rd.forward(request, response);
		}

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		doGet(request, response);
	}

}