package user.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import pagelist.vo.UserPageList;
import user.vo.UserVO;
import util.DBconn;

public class UserDAO {
	public static UserDAO userDAO;
	private UserDAO() {}
	public static UserDAO getInstance() {
		if(userDAO == null) {
			userDAO = new UserDAO();
		}
		return userDAO;
	}
	public UserPageList listAll(int currentPageNumber, int pageSize, int blockSize) throws Exception{
		
		UserPageList pageList = null;
		List<UserVO> list = null;
		UserDAO userDAO = UserDAO.getInstance();
		int totalCount = userDAO.getCount();
		if(totalCount<=0) {
			System.out.println("total count = 0");
			return null;
		}
		System.out.println("totalCount: "+totalCount);
		
		System.out.println("currentPage :"+currentPageNumber);
		
		pageList = new UserPageList(currentPageNumber,pageSize,blockSize,totalCount);
		System.out.println(pageList.getStartNo());
		System.out.println(pageList.getEndNo());
		list = userDAO.listAll(pageList.getStartNo(),pageList.getEndNo());
		System.out.println("list.size(): "+list.size());
		pageList.setList(list);
		for(int i = 0; i< list.size(); i++) {
			System.out.println(list.get(i).getUserName());
		}
		System.out.println("list 담음");
		return pageList;
	}
	// 상품 목록 전체 리스트
		public List<UserVO> listAll(int startNo, int endNo) throws SQLException {
			
			Connection conn = null;
			PreparedStatement pstmt = null;
			ResultSet rs = null;
			
			System.out.println("listAll_ startNO:"+startNo);
			System.out.println("listAll_ endNO:"+endNo);
			
			List<UserVO> list = null;
			//String sql = "select * from users where rownum<=3 and usersequence between ? and ? and isdelete = 0";
			String sql = "select * from (select rownum as rnum, A.* from (select * from users where isdelete = 0)A  )B where B.rnum between ? and ?";
			try {
				conn = DBconn.getInstance().getConnection();
				pstmt = conn.prepareStatement(sql);
				pstmt.setInt(1, startNo);
				pstmt.setInt(2, endNo);
				rs = pstmt.executeQuery();
				list = new ArrayList<>();
				while (rs.next()) {
					System.out.println("user 정보 담는중");
					UserVO UserVO = new UserVO();
					UserVO.setUserId(rs.getString("userid"));
					UserVO.setUserPw(rs.getString("userpw"));
					UserVO.setUserName(rs.getString("username"));
					UserVO.setUserAddress(rs.getString("useraddress"));
					UserVO.setUserEmail(rs.getString("useremail"));
					UserVO.setUserPhNumber(rs.getString("userphnumber"));
					UserVO.setIsAdmin(rs.getInt("isadmin"));
					list.add(UserVO);	
					
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
			finally {
				DBconn.close(conn, pstmt, rs);
			}
			return list;
		}
		
		public int getTotalCount()throws SQLException {
			int cnt = 0;
			Connection conn = null;
			PreparedStatement pstmt = null;
			ResultSet rs = null;
			String sql = "select count(*) from users";
			try {
				conn = DBconn.getInstance().getConnection();
				pstmt = conn.prepareStatement(sql);
				rs = pstmt.executeQuery();
				if(rs.next()) {
					cnt = rs.getInt(1);
					
				}
				
				
			}catch(Exception e) {
				e.printStackTrace();
			}finally {
				DBconn.close(conn, pstmt, rs);
			}
			
			return cnt;
		}
		
		public int getCount() throws SQLException {
			int cnt = 0;
			Connection conn = null;
			PreparedStatement pstmt = null;
			ResultSet rs = null;
			String sql = "select count(*) from users where isdelete = 0";
			try {
				conn = DBconn.getInstance().getConnection();
				pstmt = conn.prepareStatement(sql);
				rs = pstmt.executeQuery();
				if(rs.next()) {
					cnt = rs.getInt(1);
					
				}
				
				
			}catch(Exception e) {
				e.printStackTrace();
			}finally {
				DBconn.close(conn, pstmt, rs);
			}
			
			return cnt;
		}
		
		public int deleteUsers(String userId) {
			Connection conn = null;
			PreparedStatement pstmt = null;
			ResultSet rs = null;
			int result = 0;
			conn = DBconn.getInstance().getConnection();
			String sql = "update users set isdelete = 1 where userId = ?";
			try {
				pstmt = conn.prepareStatement(sql);
				pstmt.setString(1, userId);
				result = pstmt.executeUpdate();
				System.out.println("Delete 완료");
			} catch (Exception e) {
				e.printStackTrace();
			}finally {
				DBconn.close(conn, pstmt, rs);
			}
			return result;
		}
}
