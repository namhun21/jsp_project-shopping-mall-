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

@WebServlet("/Cart")
public class CartController extends HttpServlet {
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		RequestDispatcher rd;
		CartDAO cdao = CartDAO.getInstance();
		String uid = "js";
		OrderDAO odao = OrderDAO.getInstance();
		ArrayList<CartDTO> clist;
		CartDTO data = new CartDTO();
		clist = cdao.cartSelect(uid);
		request.setAttribute("clist", clist);
		System.out.println(clist);
		if (clist.isEmpty()) {
			rd = request.getRequestDispatcher("WEB-INF/jsp/client/cart2.jsp");
			rd.forward(request, response);
		} else {
			request.setAttribute("clist", clist);
			rd = request.getRequestDispatcher("WEB-INF/jsp/client/cart.jsp");
			rd.forward(request, response);
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}