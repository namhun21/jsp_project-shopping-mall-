package controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import user.dao.UserDAO;

/**
 * Servlet implementation class LoginOkController
 */
@WebServlet("/loginok")
public class LoginOkController extends HttpServlet {
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String userid = request.getParameter("userid");
		String userpw = request.getParameter("userpw");
		
		System.out.println(userid);
		System.out.println(userpw);
		UserDAO userDAO = UserDAO.getInstance();
		int result = userDAO.selectCheckUserInfo(userid, userpw);
		System.out.println(result);
		//클라이언트로 부터 세션 id를 받아와서 세션객체 생성
		HttpSession session = request.getSession();
		if(result == 1) {
			System.out.println("로그인 완료");
			session.setAttribute("userid", userid);
			int isadmin = userDAO.selectIsAdmin(userid);
			if(isadmin == 1) {
				System.out.println("관리자화면으로 이동");
				response.sendRedirect("productmanage");
			}
			else {
				System.out.println("고객 화면으로 이동");
				response.sendRedirect("main");
			}	
		}
		else {
			System.out.println("로그인 실패");
			response.sendRedirect("login");
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
