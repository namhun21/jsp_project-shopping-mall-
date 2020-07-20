package product.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import pagelist.vo.ProductPageList;
import product.dto.ProductCommentDTO;
import product.dto.ProductDTO;
import util.DBconnection;


public class ProductDAO {
	
	
	public static ProductDAO productDAO;
	private ProductDAO() {}
	public static ProductDAO getInstance() {
		if(productDAO == null) {
			productDAO = new ProductDAO();
		}
		return productDAO;
	}
	
	//product id 받아오는 method
	public int selectGetProductId() {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		int result = 0;
		
		conn = DBconnection.getInstance().getConnection();
		String sql = "select pid from product where isdelete = 0 order by pid desc";
		System.out.println("select문 실행");
		try {
			System.out.println("sql등록");
			pstmt = conn.prepareStatement(sql);
			System.out.println("pstmt실행");
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				result = Integer.parseInt(rs.getString(1));
				System.out.println("select 이후 result"+result);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			DBconnection.close(conn, pstmt, rs);
		}
		return result;
	}
	
	//product 추가하는 method
	public int insertProduct(String pId, String pName, String pContent, String categoryCode, int price, int stock, String product_img) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		int result = 0;
		conn = DBconnection.getInstance().getConnection();
		System.out.println("insert");
		String sql = "insert into product values(?,?,?,?,?,?,?,sysdate,0,0,0)";
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, pId);
			pstmt.setString(2, pName);
			pstmt.setString(3, pContent);
			pstmt.setString(4, categoryCode);
			pstmt.setInt(5, price);
			pstmt.setInt(6, stock);
			pstmt.setString(7, product_img);
			result = pstmt.executeUpdate();
			System.out.println("insert 완료");
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			DBconnection.close(conn, pstmt, rs);
		}
		return result;
	}
	
	//product 목록 가져오는 method
	public ArrayList<ProductDTO> selectProductAll(){
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		conn = DBconnection.getInstance().getConnection();
		String sql = "select * from product";
		ArrayList<ProductDTO> plist = new ArrayList<ProductDTO>();

		try {
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				ProductDTO dto = new ProductDTO();
				dto.setCategorycode(rs.getString("categorycode"));
				dto.setPid(rs.getString("pid"));
				dto.setPname(rs.getString("pname"));
				dto.setPrice(rs.getInt("price"));
				dto.setProduct_hit(rs.getInt("product_hit"));
				dto.setProduct_img(rs.getString("product_img"));
				dto.setProduct_regist(rs.getString("product_regist"));
				dto.setProduct_reply_cnt(rs.getInt("product_reply_cnt"));
				dto.setStock(rs.getInt("stock"));
				plist.add(dto);
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		} finally {
			DBconnection.close(conn, pstmt, rs);
		}

		return plist;
	}
	
	//product 삭제시 메소드
	public int deleteProduct(int pId) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		int result = 0;
		conn = DBconnection.getInstance().getConnection();
		String sql = "update product set isdelete = 1 where pid = ?";
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, pId);
			result = pstmt.executeUpdate();
			System.out.println("Delete 완료");
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			DBconnection.close(conn, pstmt, rs);
		}
		return result;
	}
	
	//페이징을 위한 메소드
	public ProductPageList listAll(int currentPageNumber, int pageSize, int blockSize) throws Exception{
		ProductPageList pageList = null;
		List<ProductDTO> list = null;
		ProductDAO productDAO = ProductDAO.getInstance();
		int totalCount = productDAO.getCount();
		if(totalCount<=0) {
			return null;
		}
		System.out.println("totalCount: "+totalCount);
		
		System.out.println("currentPage :"+currentPageNumber);
		
		pageList = new ProductPageList(currentPageNumber,pageSize,blockSize,totalCount);
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
		public List<ProductDTO> listAll(int startNo, int endNo) throws SQLException {
			Connection conn = null;
			PreparedStatement pstmt = null;
			ResultSet rs = null;
			
			System.out.println("listAll_ startNO:"+startNo);
			System.out.println("listAll_ endNO:"+endNo);
			
			List<ProductDTO> list = null;
			//String sql = "select * from product where pid between ? and ? and isdelete = 0 order by pid desc";
			String sql = "select * from (select rownum as rnum, A.* from (select * from product where isdelete = 0)A  )B where B.rnum between ? and ?";
			try {
				conn = DBconnection.getInstance().getConnection();
				pstmt = conn.prepareStatement(sql);
				pstmt.setInt(1, startNo);
				pstmt.setInt(2, endNo);
				rs = pstmt.executeQuery();
				list = new ArrayList<>();
				while (rs.next()) {
					
					ProductDTO productVO = new ProductDTO();
					productVO.setPid(rs.getString("pID"));
					productVO.setPname(rs.getString("pName"));
					productVO.setPcontent(rs.getString("pContent"));
					productVO.setCategorycode(rs.getString("categoryCode"));
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
				DBconnection.close(conn, pstmt, rs);
			}
			return list;
		}
	
	// 지워지지않은 product의 수 반환하는 메소드 
	public int getCount() throws SQLException {
		int cnt = 0;
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			conn = DBconnection.getInstance().getConnection();
			pstmt = conn.prepareStatement("select count(*) from product where isdelete = 0");
			rs = pstmt.executeQuery();
			rs.next();
			cnt = rs.getInt(1);
			System.out.println(cnt);
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			DBconnection.close(conn, pstmt, rs);
		}
		
		return cnt;
	}
	
	// 해당상품 가져오기
		public ProductDTO selectProduct(String productID) {
			Connection conn = null;
			PreparedStatement pstmt = null;
			ResultSet rs = null;

			conn = DBconnection.getInstance().getConnection();
			String sql = "select * from product where pid = ?";
			ProductDTO dto = new ProductDTO();

			try {
				pstmt = conn.prepareStatement(sql);
				pstmt.setString(1, productID);
				rs = pstmt.executeQuery();
				while (rs.next()) {
					dto.setCategorycode(rs.getString("categorycode"));
					dto.setPid(rs.getString("pid"));
					dto.setPname(rs.getString("pname"));
					dto.setPrice(rs.getInt("price"));
					dto.setProduct_hit(rs.getInt("product_hit"));
					dto.setProduct_img(rs.getString("product_img"));
					dto.setProduct_regist(rs.getString("product_regist"));
					dto.setProduct_reply_cnt(rs.getInt("product_reply_cnt"));
					dto.setStock(rs.getInt("stock"));
				}
			} catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
			} finally {
				DBconnection.close(conn,pstmt, rs);
			}

			return dto;
		}
		// sort한 상품 가져오기
		public ArrayList<ProductDTO> selectSortedProduct(String sortby) {
			Connection conn = null;
			PreparedStatement pstmt = null;
			ResultSet rs = null;
			ArrayList<ProductDTO> plist = new ArrayList<ProductDTO>();

			String param = "";
			if (sortby.equals("orders")) {
				param = "PRODUCT_HIT";
			} else if (sortby.equals("price")) {
				param = "price";
			}

			conn = DBconnection.getInstance().getConnection();
			String sql = "select * from product order by " + param;

			try {
				pstmt = conn.prepareStatement(sql);
				rs = pstmt.executeQuery();
				while (rs.next()) {
					ProductDTO dto = new ProductDTO();
					dto.setCategorycode(rs.getString("categorycode"));
					dto.setPid(rs.getString("pid"));
					dto.setPname(rs.getString("pname"));
					dto.setPrice(rs.getInt("price"));
					dto.setProduct_hit(rs.getInt("product_hit"));
					dto.setProduct_img(rs.getString("product_img"));
					dto.setProduct_regist(rs.getString("product_regist"));
					dto.setProduct_reply_cnt(rs.getInt("product_reply_cnt"));
					dto.setStock(rs.getInt("stock"));
					plist.add(dto);
				}
			} catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
			} finally {
				DBconnection.close(conn,pstmt, rs);
			}

			return plist;
		}
		// 상품 댓글가져오기
		public ArrayList<ProductCommentDTO> selectCommentAll() {
			Connection conn = null;
			PreparedStatement pstmt = null;
			ResultSet rs = null;

			conn = DBconnection.getInstance().getConnection();
			String sql = "select * from product_reply order by repregist desc";
			ArrayList<ProductCommentDTO> clist = new ArrayList<ProductCommentDTO>();

			try {
				pstmt = conn.prepareStatement(sql);
				rs = pstmt.executeQuery();

				while (rs.next()) {
					ProductCommentDTO dto = new ProductCommentDTO();
					dto.setPid(rs.getString("pid"));
					dto.setRepid(rs.getString("repid"));
					dto.setRepregist(rs.getString("repregist"));
					dto.setUserid(rs.getString("userid"));
					dto.setComments(rs.getString("comments"));
					dto.setIsdelete(rs.getInt("isdelete"));
					clist.add(dto);
				}
			} catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
			} finally {
				DBconnection.close(conn,pstmt, rs);
			}

			return clist;
		}
		// comment 삽입
		public int insertComment(String commentValue, String pid) {
			Connection conn = null;
			PreparedStatement pstmt = null;
			ResultSet rs = null;
			int result = 0;
			// TODO userid : receive data from session or cookie
			String userid = "juseok";
			ArrayList<ProductCommentDTO> clist = selectCommentAll();
			String replyID = String.valueOf(clist.size() + 1);

			conn = DBconnection.getInstance().getConnection();
			String sql = "insert into product_reply values(?,?,?,sysdate,?,0)";

			try {
				pstmt = conn.prepareStatement(sql);
				pstmt.setString(1, replyID);
				pstmt.setString(2, pid);
				pstmt.setString(3, userid);
				pstmt.setString(4, commentValue);
				result = pstmt.executeUpdate();
				if (result > 0) {
					result = Integer.parseInt(replyID);
				}
			} catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
			} finally {
				DBconnection.close(conn,pstmt, rs);
			}

			return result;
		}
		// 특정 comment 불러오기
		public ProductCommentDTO selectInsertedComment(String replyID) {
			Connection conn = null;
			PreparedStatement pstmt = null;
			ResultSet rs = null;
			ProductCommentDTO dto = new ProductCommentDTO();
			ArrayList<ProductCommentDTO> clist = selectCommentAll();

			conn = DBconnection.getInstance().getConnection();
			String sql = "select * from product_reply where repid = ?";

			try {
				pstmt = conn.prepareStatement(sql);
				pstmt.setString(1, replyID);
				rs = pstmt.executeQuery();
				while (rs.next()) {
					dto.setPid(rs.getString("pid"));
					dto.setRepid(rs.getString("repid"));
					dto.setRepregist(rs.getString("repregist"));
					dto.setUserid(rs.getString("userid"));
					dto.setComments(rs.getString("comments"));
					dto.setIsdelete(rs.getInt("isdelete"));
				}
			} catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
			} finally {
				DBconnection.close(conn,pstmt, rs);
			}

			return dto;
		}
}
