package controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import user.dao.UserDAO;
import user.dto.UserDTO;

@WebServlet("/joinok")
public class JoinOkController extends HttpServlet {

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		String userid = request.getParameter("userid");
		String userpw = request.getParameter("userpw");
		String username = request.getParameter("username");
		String useraddress = request.getParameter("useraddress");
		String useremail = request.getParameter("useremail");
		String userphnumber = request.getParameter("userphnumber");
		
		UserDAO userDAO = UserDAO.getInstance();
		UserDTO userDTO = new UserDTO();
		userDTO.setUserId(userid);
		userDTO.setUserPw(userpw);
		userDTO.setUserName(username);
		userDTO.setUserAddress(useraddress);
		userDTO.setUserEmail(useremail);
		userDTO.setUserPhNumber(userphnumber);
		
		int result = userDAO.insertJoin(userDTO);
		
		
		if(result == 1) {
			System.out.println("회원가입 완료");
			response.sendRedirect("login");
		}else {
			System.out.println("회원가입 실패");
			response.sendRedirect("join");
		}
		
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
