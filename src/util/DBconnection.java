package util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

public class DBconnection {
	private static DBconnection dbconn = null;
	private DBconnection() {}
	
	public static DBconnection getInstance() {
		if(dbconn == null) {
			dbconn = new DBconnection();
		}
		return dbconn;
	}
	public Connection getConnection() {
		Connection conn = null;
		try {
			Context initContext = new InitialContext();
			Context envContext  = (Context)initContext.lookup("java:/comp/env");
			DataSource ds = (DataSource)envContext.lookup("jdbc/myoracle");
			conn = ds.getConnection();
		}catch(NamingException e) {
			e.printStackTrace();
		}catch(SQLException e) {
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
