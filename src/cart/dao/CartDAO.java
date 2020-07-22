package cart.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import cart.dto.CartDTO;
import util.DBconnection;

public class CartDAO {
	private static CartDAO cartDAO;

	private CartDAO() {
	}

	public static CartDAO getInstance() {
		if (cartDAO == null) {
			cartDAO = new CartDAO();
		}
		return cartDAO;
	}

	public int getCartCount() {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		int result = 0;

		conn = DBconnection.getInstance().getConnection();
		String sql = "select count(*) from cart";
		try {
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				result = rs.getInt(1);
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			DBconnection.close(conn, pstmt, rs);
		}
		return result;
	}

	public int cartInsert(String uid,String cartid, String pid, int pcount) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		int result = 0;
		String userid = uid;

		conn = DBconnection.getInstance().getConnection();
		String sql = "insert into cart VALUES (?,?,?,?,0)";
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, cartid);
			pstmt.setString(2, userid);
			pstmt.setString(3, pid);
			pstmt.setInt(4, pcount);
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			DBconnection.close(conn, pstmt, rs);
		}
		return result;
	}

	public ArrayList<CartDTO> cartSelect(String uid) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		ArrayList<CartDTO> clist = null;
		conn = DBconnection.getInstance().getConnection();
		String sql = "SELECT cart.pid, userid, cartid,price,pcount,product_img, pname FROM product join cart on product.pid = cart.pid where cart.userid = ? and cart.isdelete = 0";
		try {
			pstmt = conn.prepareStatement(sql);
			clist = new ArrayList<CartDTO>();
			pstmt.setString(1, uid);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				CartDTO cvo = new CartDTO();
				cvo.setpId(rs.getString("pid"));
				cvo.setuId(rs.getString("userid"));
				cvo.setcartId(rs.getInt("cartid"));
				cvo.setPrice(rs.getString("price"));
				cvo.setpName(rs.getString("pname"));
				cvo.setpCount(rs.getInt("pcount"));
				cvo.setProduct_img(rs.getString("product_img"));
				clist.add(cvo);
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			DBconnection.close(conn, pstmt, rs);
		}
		return clist;
	}

	public void cartDelete(String uid) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		int result = 0;

		conn = DBconnection.getInstance().getConnection();
		String sql = "UPDATE cart SET isdelete = 1 WHERE userid = ?";
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, uid);
			result = pstmt.executeUpdate();

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			DBconnection.close(conn, pstmt, rs);
		}
//		return result;
	}

	public void cartpartDelete(int cartid) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		int result = 0;

		conn = DBconnection.getInstance().getConnection();
		String sql = "UPDATE cart SET isdelete = 1 WHERE cartid = ?";
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, cartid);
			result = pstmt.executeUpdate();

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			DBconnection.close(conn, pstmt, rs);
		}
//		return result;
	}

	public int cartUpdate(int pcount, int cartid) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		int result = 0;

		conn = DBconnection.getInstance().getConnection();
		String sql = "UPDATE cart SET pcount = ? WHERE cartid = ?";
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, pcount);
			pstmt.setInt(2, cartid);

			result = pstmt.executeUpdate();

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			DBconnection.close(conn, pstmt, rs);
		}
		return result;
	}

	public int cartdelete(int cartid) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		int result = 0;

		conn = DBconnection.getInstance().getConnection();
		String sql = "DELETE FROM cart WHERE cartid = ?";
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, cartid);

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
