package product.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import pagelist.vo.PageList;
import product.vo.ProductVO;
import util.DBconn;

public class ProductDAO {
	
	
	public static ProductDAO productDAO;
	private ProductDAO() {}
	public static ProductDAO getInstance() {
		if(productDAO == null) {
			productDAO = new ProductDAO();
		}
		return productDAO;
	}
	public int selectGetProductId() {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		int result = 0;
		
		conn = DBconn.getInstance().getConnection();
		String sql = "select pid from product where isdelete = 0 order by pid desc";
		System.out.println("select문 실행");
		try {
			System.out.println("sql등록");
			pstmt = conn.prepareStatement(sql);
			System.out.println("pstmt실행");
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				result = rs.getInt(1);
				System.out.println("select 이후 result"+result);
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
		System.out.println("insert");
		String sql = "insert into product values(?,?,?,?,?,?,?,sysdate,0,0,0)";
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, pId);
			pstmt.setString(2, pName);
			pstmt.setString(3, pContent);
			pstmt.setInt(4, categoryCode);
			pstmt.setInt(5, price);
			pstmt.setInt(6, stock);
			pstmt.setString(7, product_img);
			result = pstmt.executeUpdate();
			System.out.println("insert 완료");
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			DBconn.close(conn, pstmt, rs);
		}
		return result;
	}
	public ArrayList<ProductVO> selectProducts(){
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		ArrayList<ProductVO> plist = null;
		
		conn = DBconn.getInstance().getConnection();
		String sql = "select * from product where isdelete = 0";
		try {
			plist = new ArrayList<ProductVO>();
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			while(rs.next()) {
			ProductVO productVO = new ProductVO();
			productVO.setPid(rs.getInt("pID"));
			productVO.setPname(rs.getString("pName"));
			productVO.setPcontent(rs.getString("pContent"));
			productVO.setCategorycode(rs.getInt("categoryCode"));
			productVO.setPrice(rs.getInt("price"));
			productVO.setStock(rs.getInt("stock"));
			productVO.setProduct_img(rs.getString("product_img"));
			productVO.setProduct_regist(rs.getString("product_regist"));
			productVO.setProduct_hit(rs.getInt("product_hit"));
			productVO.setProduct_reply_cnt(rs.getInt("product_reply_cnt"));
			plist.add(productVO);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			DBconn.close(conn, pstmt, rs);
		}
		return plist;
	}
	public int deleteProduct(int pId) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		int result = 0;
		conn = DBconn.getInstance().getConnection();
		String sql = "update product set isdelete = 1 where pid = ?";
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, pId);
			result = pstmt.executeUpdate();
			System.out.println("Delete 완료");
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			DBconn.close(conn, pstmt, rs);
		}
		return result;
	}
	
	public PageList listAll(int currentPageNumber, int pageSize, int blockSize) throws Exception{
		PageList pageList = null;
		List<ProductVO> list = null;
		ProductDAO productDAO = ProductDAO.getInstance();
		int totalCount = productDAO.getCount();
		if(totalCount<=0) {
			return null;
		}
		System.out.println("totalCount: "+totalCount);
		
		System.out.println("currentPage :"+currentPageNumber);
		
		pageList = new PageList(currentPageNumber,pageSize,blockSize,totalCount);
		System.out.println(pageList.getStartNo());
		System.out.println(pageList.getEndNo());
		list = productDAO.listAll(pageList.getStartNo(),pageList.getEndNo());
		System.out.println("list.size(): "+list.size());
		pageList.setList(list);
		for(int i = 0; i< list.size(); i++) {
			System.out.println(list.get(i).getPname());
		}
		System.out.println("list 담음");
		return pageList;
	}
	// 상품 목록 전체 리스트
		public List<ProductVO> listAll(int startNo, int endNo) throws SQLException {
			Connection conn = null;
			PreparedStatement pstmt = null;
			ResultSet rs = null;
			
			System.out.println("listAll_ startNO:"+startNo);
			System.out.println("listAll_ endNO:"+endNo);
			
			List<ProductVO> list = null;
			String sql = "select * from product where pid between ? and ? and isdelete = 0 order by pid desc";
			try {
				conn = DBconn.getInstance().getConnection();
				pstmt = conn.prepareStatement(sql);
				pstmt.setInt(1, startNo);
				pstmt.setInt(2, endNo);
				rs = pstmt.executeQuery();
				list = new ArrayList<>();
				while (rs.next()) {
					
					ProductVO productVO = new ProductVO();
					productVO.setPid(rs.getInt("pID"));
					productVO.setPname(rs.getString("pName"));
					productVO.setPcontent(rs.getString("pContent"));
					productVO.setCategorycode(rs.getInt("categoryCode"));
					productVO.setPrice(rs.getInt("price"));
					productVO.setStock(rs.getInt("stock"));
					productVO.setProduct_img(rs.getString("product_img"));
					productVO.setProduct_regist(rs.getString("product_regist"));
					productVO.setProduct_hit(rs.getInt("product_hit"));
					productVO.setProduct_reply_cnt(rs.getInt("product_reply_cnt"));
					list.add(productVO);	
					
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
			finally {
				DBconn.close(conn, pstmt, rs);
			}
			return list;
		}
	
	public int getCount() throws SQLException {
		int cnt = 0;
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			conn = DBconn.getInstance().getConnection();
			pstmt = conn.prepareStatement("select count(*) from product where isdelete = 0");
			rs = pstmt.executeQuery();
			rs.next();
			cnt = rs.getInt(1);
			
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			DBconn.close(conn, pstmt, rs);
		}
		
		return cnt;
	}
}
