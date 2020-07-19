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
		int currentPage = 1; // 초기값 
		// 목록개수
		int pageSize = 3;
		// 블록크기
		int blockSize = 10;
		try{
			currentPage = Integer.parseInt(request.getParameter("currentPage"));
			System.out.println("currentpage:"+currentPage);
			pageSize = Integer.parseInt(request.getParameter("pageSize"));
			System.out.println("pageSize: "+pageSize);
			blockSize = Integer.parseInt(request.getParameter("blockSize"));
			System.out.println("blockSize: "+blockSize);
		}catch(Exception e){ 
			;
		}
		
		UserDAO userDAO = UserDAO.getInstance();
		UserPageList listAll = null;
		int count = 0;
		try {
			listAll = userDAO.listAll(currentPage, pageSize, blockSize);
			count = userDAO.getCount();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		request.setAttribute("page", listAll);
		request.setAttribute("count", count);
		
		RequestDispatcher rd = request.getRequestDispatcher("WEB-INF/jsp/user-manage.jsp");
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
