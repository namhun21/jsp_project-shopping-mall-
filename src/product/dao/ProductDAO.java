package product.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;



import util.DBconn;

public class ProductDAO {
	public int selectGetProductId() {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		int result = 0;
		
		conn = DBconn.getInstance().getConnection();
		String sql = "select pid from product where rownum<2 order by pid desc";
		try {
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			if(rs.next()) {
				result = rs.getInt(1);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			DBconn.close(conn, pstmt, rs);
		}
		return result;
	}
	public int insertProduct(int pId, String pName, String pContent, int categoryCode, int price, int stock, String product_img) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		int result = 0;
		conn = DBconn.getInstance().getConnection();
		String sql = "insert into product values(?,?,?,?,?,?,?,sysdate,0,0)";
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, pId);
			pstmt.setString(2, pName);
			pstmt.setString(3, pName);
			pstmt.setString(4, pContent);
			pstmt.setInt(5, price);
			pstmt.setInt(6, stock);
			pstmt.setString(7, product_img);
			result = pstmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			DBconn.close(conn, pstmt, rs);
		}
		return result;
	}
}
