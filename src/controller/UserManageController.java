package controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import pagelist.vo.UserPageList;
import user.dao.UserDAO;



/**
 * Servlet implementation class UserManageController
 */
@WebServlet("/usermanage")
public class UserManageController extends HttpServlet {
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		// 현재페이지 번호
		int userCurrentPage = 1; // 초기값 
		// 목록개수
		int userPageSize = 3;
		// 블록크기
		int userBlockSize = 10;
		
		try{
			userCurrentPage = Integer.parseInt(request.getParameter("userCurrentPage"));
			System.out.println("userCurrentPage:"+userCurrentPage);
			userPageSize = Integer.parseInt(request.getParameter("userPageSize"));
			System.out.println("userPageSize: "+userPageSize);
			userBlockSize = Integer.parseInt(request.getParameter("userBlockSize"));
			System.out.println("userBlockSize: "+userBlockSize);
		}catch(Exception e){ 
			System.out.println("userPage 받기 실패");
		}
		
		UserDAO userDAO = UserDAO.getInstance();
		UserPageList listAll = null;
		int count = 0;
		try {
			listAll = userDAO.listAll(userCurrentPage, userPageSize, userBlockSize);
			count = userDAO.getCount();
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		request.setAttribute("page", listAll);
		request.setAttribute("count", count);
		
		RequestDispatcher rd = request.getRequestDispatcher("WEB-INF/jsp/admin/user-manage.jsp");
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
