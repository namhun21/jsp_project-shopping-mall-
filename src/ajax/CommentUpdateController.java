package ajax;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

import product.dao.ProductDAO;
import product.dto.ProductCommentDTO;

@WebServlet("/CommentUpdate")
public class CommentUpdateController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		String commentValue = request.getParameter("comment");
		String pid = request.getParameter("pid");

		ProductDAO dao = ProductDAO.getInstance();
		int result = dao.insertComment(commentValue, pid);

		// result 0이상이면 된거다다다다다다
		if (result > 0) {
			Gson gson = new Gson();
			ProductCommentDTO newComment = dao.selectInsertedComment(String.valueOf(result));
			response.getWriter().write(gson.toJson(newComment));
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
