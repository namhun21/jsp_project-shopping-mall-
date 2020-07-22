package ajax;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.google.gson.Gson;
import com.google.gson.JsonObject;

import cart.dao.CartDAO;

@WebServlet("/AddToCart")
public class AddToCartController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		String productCnt = request.getParameter("productCnt");
		String pid = request.getParameter("pid");
		CartDAO dao = CartDAO.getInstance();
		int cartCount = dao.getCartCount();
		HttpSession session = request.getSession();
	    String uid = (String)session.getAttribute("userid");

		int result = dao.cartInsert(uid,String.valueOf(cartCount + 1), pid, Integer.parseInt(productCnt));
		Gson gson = new Gson();
		JsonObject json = new JsonObject();
		json.addProperty("result", result);

		response.getWriter().write(gson.toJson(json));
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
