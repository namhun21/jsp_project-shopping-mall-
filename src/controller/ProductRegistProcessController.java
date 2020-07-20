package controller;

import java.io.IOException;
import java.nio.file.Paths;
import java.sql.SQLException;
import java.util.Enumeration;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

import product.dao.ProductDAO;

/**
 * Servlet implementation class ProductRegistProcessController
 */
@WebServlet("/productregistprocess")
@MultipartConfig 
public class ProductRegistProcessController extends HttpServlet {
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
	
		ProductDAO productdao = ProductDAO.getInstance();
		String pId =  String.valueOf(productdao.selectGetProductId() + 1);
		System.out.println("이것은 pid"+pId);
		String pName = request.getParameter("pName");
		String pContent = request.getParameter("pContent");
		String categoryCode = request.getParameter("categoryCode");
		int price = Integer.parseInt(request.getParameter("price"));
		int stock = Integer.parseInt(request.getParameter("stock"));
		
		Part filePart = request.getPart("upload"); // Retrieves <input type="file" name="upload">
	    String product_img =  getSubmittedFileName(filePart);
	    
		
		int result = productdao.insertProduct(pId, pName, pContent, categoryCode, price, stock, product_img);
		
		if(result!= 0) {
			response.sendRedirect("productmanage");
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}
	private static String getSubmittedFileName(Part part) {
	    for (String cd : part.getHeader("content-disposition").split(";")) {
	        if (cd.trim().startsWith("filename")) {
	            String fileName = cd.substring(cd.indexOf('=') + 1).trim().replace("\"", "");
	            return fileName.substring(fileName.lastIndexOf('/') + 1).substring(fileName.lastIndexOf('\\') + 1); // MSIE fix.
	        }
	    }
	    return null;
	}
}
