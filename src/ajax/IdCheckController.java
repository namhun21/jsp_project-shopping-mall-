package ajax;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import user.dao.UserDAO;

/**
 * Servlet implementation class IdCheckController
 */
@WebServlet("/idcheck")
public class IdCheckController extends HttpServlet {

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("idcheck Servlet 도착");
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=UTF-8");
		UserDAO userDAO = UserDAO.getInstance();
		String userid = request.getParameter("userid");
		System.out.println(userid);
        PrintWriter out = response.getWriter();
        int result = 0;
        try {
			result = userDAO.selectUserIdCheck(userid);
		} catch (Exception e) {
			e.printStackTrace();
		}
        
        if(result ==1) {
            out.print(1);
        }else {
            out.print(0);
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
