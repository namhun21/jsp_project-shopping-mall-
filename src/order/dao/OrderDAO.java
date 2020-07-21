package order.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import cart.dao.CartDAO;
import cart.dto.CartDTO;
import util.DBconnection;

public class OrderDAO {
private static OrderDAO orderDAO;
	
	private OrderDAO() {}
	public static OrderDAO getInstance() {
		if(orderDAO == null) {
			orderDAO = new OrderDAO();
		}
		return orderDAO;
	}
	
	public int orderInsert(int orderid, String uid, int pid) {
		// TODO Auto-generated method stub
		int ordernum = 1;
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		int result = 0;
		Statement stat = null;
		conn = DBconnection.getInstance().getConnection();
		String sql = "INSERT INTO orders (ordernum, orderid, userid, pid, orderdate, useraddress,orderphon, amount) VALUES (?, ?, ?, ?, sysdate,(select useraddress from users where userid = ?),(select userphnumber from users where userid = ?),(select pcount from cart where pid = ?))";
		
		try {

			stat = conn.createStatement();
			rs = stat.executeQuery("select ordernum from orders where ROWNUM = 1 order by ordernum desc");
	         while (rs.next()) {
	            ordernum = rs.getInt("ordernum") + 1;
	         }
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (stat != null)
				try {
					stat.close();
				} catch (SQLException ex) {
				}
		}
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, ordernum++);
			pstmt.setInt(2, orderid);
			pstmt.setString(3, uid);
			pstmt.setInt(4, pid);
			pstmt.setString(5, uid);
			pstmt.setString(6, uid);
			pstmt.setInt(7, pid);
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			DBconnection.close(conn, pstmt, rs);
		}
		return result;
	}
}
