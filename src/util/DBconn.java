package util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DBconn {
	private static DBconn dbconn = null;
	private DBconn() {}
	
	public static DBconn getInstance() {
		if(dbconn == null) {
			dbconn = new DBconn();
		}
		return dbconn;
	}
	public static Connection getConnection() {
		Connection conn = null;
		try {
			if(conn == null || conn.isClosed()) {
				String driver = "oracle.jdbc.driver.OracleDriver";
				String url = "jdbc:oracle:thin:@localhost:1521:xe";
				String id = "mvcweb";
				String pw = "1234";
				Class.forName(driver);
				conn = DriverManager.getConnection(url,id,pw);
			}
		}catch (Exception e) {
			e.printStackTrace();
		}
		return conn;
	}
	public static void close(Connection conn,PreparedStatement pstmt, ResultSet rs ) {
		try {
			if(rs != null) {
				rs.close();
			}
			if(pstmt!=null) {
				pstmt.close();
		}
			if(conn != null ) {
				conn.close();
			}
				
		}catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
