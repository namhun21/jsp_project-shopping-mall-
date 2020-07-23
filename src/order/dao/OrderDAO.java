package order.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import cart.dao.CartDAO;
import cart.dto.CartDTO;
import pagelist.vo.OrderPageList;
import order.dao.OrderDAO;
import order.dto.OrderDTO;
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
	 public int selectOrder() {
	      Connection conn = null;
	      PreparedStatement pstmt = null;
	      ResultSet rs = null;
	      int result = 0;
	      conn = DBconnection.getInstance().getConnection();
	      String sql = "select count(*) from orders";
	      try {
	         pstmt = conn.prepareStatement(sql);
	         rs = pstmt.executeQuery();
	      while(rs.next())
	      {
	         result = rs.getInt(1);
	      }
	      } catch (Exception e) {
	         // TODO: handle exception
	         e.printStackTrace();
	      } finally {
	         DBconnection.close(conn, pstmt, rs);
	      }

	      return result;
	   }

	  public int orderInsert(int orderid, String uid, ArrayList<String> pid) {
	      // TODO Auto-generated method stub
	      int ordernum = 1;
	      Connection conn = null;
	      PreparedStatement pstmt = null;
	      ResultSet rs = null;
	      int result = 0;
	      Statement stat = null;
	      conn = DBconnection.getInstance().getConnection();
	      String sql = "INSERT INTO orders (ordernum, orderid, userid, pid, orderdate, useraddress,orderphon, amount) VALUES (?, ?, ?, ?, sysdate,(select useraddress from users where userid = ?),(select userphnumber from users where userid = ?),(select pcount from cart where pid = ? and isdelete = 0))";
	      
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
	         for(int i =0; i<pid.size();i++)
	         {
	            pstmt.setInt(1, ordernum++);
	            pstmt.setInt(2, orderid);
	            pstmt.setString(3, uid);
	            pstmt.setString(4, pid.get(i));
	            pstmt.setString(5, uid);
	            pstmt.setString(6, uid);
	            pstmt.setString(7, pid.get(i));
	            pstmt.executeUpdate();
	         }
	         
	      } catch (SQLException e) {
	         // TODO Auto-generated catch block
	         e.printStackTrace();
	      } finally {
	         DBconnection.close(conn, pstmt, rs);
	      }
	      return result;
	   }

	
	public OrderPageList listAll(int currentPageNumber, int pageSize, int blockSize) throws Exception{
		
		OrderPageList pageList = null;
		List<OrderDTO> list = null;
		OrderDAO orderDAO = OrderDAO.getInstance();
		int totalCount = orderDAO.getCount();
		if(totalCount<=0) {
			System.out.println("total count = 0");
			return null;
		}
		System.out.println("totalCount: "+totalCount);
		
		System.out.println("currentPage :"+currentPageNumber);
		
		pageList = new OrderPageList(currentPageNumber,pageSize,blockSize,totalCount);
		System.out.println(pageList.getStartNo());
		System.out.println(pageList.getEndNo());
		list = orderDAO.listAll(pageList.getStartNo(),pageList.getEndNo());
		System.out.println("list.size(): "+list.size());
		pageList.setList(list);
		for(int i = 0; i< list.size(); i++) {
			System.out.println(list.get(i).getOrderid());
		}
		System.out.println("list 담음");
		return pageList;
	}
	// 상품 목록 전체 리스트
		public List<OrderDTO> listAll(int startNo, int endNo) throws SQLException {
			
			Connection conn = null;
			PreparedStatement pstmt = null;
			ResultSet rs = null;
			
			System.out.println("listAll_ startNO:"+startNo);
			System.out.println("listAll_ endNO:"+endNo);
			
			List<OrderDTO> list = null;
			//String sql = "select * from users where rownum<=3 and usersequence between ? and ? and isdelete = 0";
			String sql = "select * from (select rownum as rnum, A.* from (select * from orders)A  )B where B.rnum between ? and ?";
			try {
				conn = DBconnection.getInstance().getConnection();
				pstmt = conn.prepareStatement(sql);
				pstmt.setInt(1, startNo);
				pstmt.setInt(2, endNo);
				rs = pstmt.executeQuery();
				list = new ArrayList<>();
				while (rs.next()) {
					System.out.println("order 정보 담는중");
					OrderDTO orderDTO = new OrderDTO();
					orderDTO.setOrderid(rs.getString("orderid"));
					orderDTO.setUserid(rs.getString("userid"));
					orderDTO.setPid(rs.getString("pid"));
					orderDTO.setOrderdate(rs.getString("orderdate"));
					//orderDTO.setUserName(rs.getString("username"));
					orderDTO.setAddress(rs.getString("useraddress"));
					orderDTO.setOrderphon(rs.getString("orderphon"));
					orderDTO.setAmount(rs.getString("amount"));
					list.add(orderDTO);	
					
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
			finally {
				DBconnection.close(conn, pstmt, rs);
			}
			return list;
		}
		
		
		public int getCount() throws SQLException {
			int cnt = 0;
			Connection conn = null;
			PreparedStatement pstmt = null;
			ResultSet rs = null;
			String sql = "select count(*) from orders";
			try {
				conn = DBconnection.getInstance().getConnection();
				pstmt = conn.prepareStatement(sql);
				rs = pstmt.executeQuery();
				if(rs.next()) {
					cnt = rs.getInt(1);
					
				}
				
				
			}catch(Exception e) {
				e.printStackTrace();
			}finally {
				DBconnection.close(conn, pstmt, rs);
			}
			
			return cnt;
		}
		
}
