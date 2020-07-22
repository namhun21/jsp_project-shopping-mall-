package user.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import pagelist.vo.UserPageList;
import user.dto.UserDTO;
import util.DBconnection;

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
		List<UserDTO> list = null;
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
	// 상품 사용자 전체 리스트
		public List<UserDTO> listAll(int startNo, int endNo) throws SQLException {
			
			Connection conn = null;
			PreparedStatement pstmt = null;
			ResultSet rs = null;
			
			System.out.println("listAll_ startNO:"+startNo);
			System.out.println("listAll_ endNO:"+endNo);
			
			List<UserDTO> list = null;
			//String sql = "select * from users where rownum<=3 and usersequence between ? and ? and isdelete = 0";
			String sql = "select * from (select rownum as rnum, A.* from (select * from users where isdelete = 0)A  )B where B.rnum between ? and ?";
			try {
				conn = DBconnection.getInstance().getConnection();
				pstmt = conn.prepareStatement(sql);
				pstmt.setInt(1, startNo);
				pstmt.setInt(2, endNo);
				rs = pstmt.executeQuery();
				list = new ArrayList<>();
				while (rs.next()) {
					System.out.println("user 정보 담는중");
					UserDTO UserVO = new UserDTO();
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
				DBconnection.close(conn, pstmt, rs);
			}
			return list;
		}
		
		public int getCount() throws SQLException {
			int cnt = 0;
			Connection conn = null;
			PreparedStatement pstmt = null;
			ResultSet rs = null;
			String sql = "select count(*) from users where isdelete = 0";
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
		
		public int deleteUsers(String userId) {
			Connection conn = null;
			PreparedStatement pstmt = null;
			ResultSet rs = null;
			int result = 0;
			conn = DBconnection.getInstance().getConnection();
			String sql = "update users set isdelete = 1 where userId = ?";
			try {
				pstmt = conn.prepareStatement(sql);
				pstmt.setString(1, userId);
				result = pstmt.executeUpdate();
				System.out.println("Delete 완료");
			} catch (Exception e) {
				e.printStackTrace();
			}finally {
				DBconnection.close(conn, pstmt, rs);
			}
			return result;
		}
		
		public int selectIsAdmin(String userId) {
			Connection conn = null;
			PreparedStatement pstmt = null;
			ResultSet rs = null;
			int result = 0;
			conn = DBconnection.getInstance().getConnection();
			String sql = "select isadmin from users where userId = ?";
			try {
				pstmt = conn.prepareStatement(sql);
				pstmt.setString(1, userId);
				rs = pstmt.executeQuery();
				System.out.println("isadmin값 반환 완료");
				if(rs.next()) {
					result = rs.getInt(1);
				}
			} catch (Exception e) {
				e.printStackTrace();
			}finally {
				DBconnection.close(conn, pstmt, rs);
			}
			return result;
		}
		
		//userid 체크(중복확인용)
		public int selectUserIdCheck(String userid) {
			Connection conn = null;
			PreparedStatement pstmt = null;
			ResultSet rs = null;
			int result = 0;

			conn = DBconnection.getInstance().getConnection();
			String sql = "select count(userid) from users where userid = ?";
			try {
				pstmt = conn.prepareStatement(sql);
				pstmt.setString(1, userid);
				rs = pstmt.executeQuery();
				if (rs.next()) {
					result = rs.getInt(1);
				}
			} catch (SQLException e) {
				e.printStackTrace();
			} finally {
				DBconnection.close(conn, pstmt, rs);
			}
			return result;
		}
		
		//아이디 비밀번호 체크용 메소드
		public int selectCheckUserInfo(String userid, String userpw) {
			Connection conn = null;
			PreparedStatement pstmt = null;
			ResultSet rs = null;
			int result = 0;
			
			
			String sql = "select count(*) from users where userid = ? and userpw = ?";

			try {
				conn = DBconnection.getInstance().getConnection();
				pstmt = conn.prepareStatement(sql);
				pstmt.setString(1, userid);
				pstmt.setString(2, userpw);
				rs = pstmt.executeQuery();
				if(rs.next()) {
					result = rs.getInt(1);
				}
				
			} catch (SQLException e) {
				e.printStackTrace();
			} finally {
				DBconnection.close(conn, pstmt,rs);
			}
			
			return result;			
		}
		
		//회원가입 시 회원정보 db에 저장
		public int insertJoin(UserDTO userDTO) {
			Connection conn = null;
			PreparedStatement pstmt = null;
			ResultSet rs = null;
			int result = 0;
			
			conn = DBconnection.getInstance().getConnection();
			String sql = "insert into users values (? , ? , ? , ? , ? , ? , user_seq.nextval ,0,0)";
			
			try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, userDTO.getUserId());
			pstmt.setString(2, userDTO.getUserPw());
			pstmt.setString(3, userDTO.getUserName());
			pstmt.setString(4, userDTO.getUserAddress());
			pstmt.setString(5, userDTO.getUserEmail());
			pstmt.setString(6, userDTO.getUserPhNumber());
			result = pstmt.executeUpdate();
			System.out.println("insert user 완료");
			} catch (SQLException e) {
				e.printStackTrace();
			} finally {
				DBconnection.close(conn,pstmt, rs);
			}
			return result;
		}
		
		//전체 회원 수 반환
		public ArrayList<UserDTO> selectAllUsers() {
			Connection conn = null;
			PreparedStatement pstmt = null;
			ResultSet rs = null;;
			ArrayList<UserDTO> ulist = null;
			
			conn = DBconnection.getInstance().getConnection();
			String sql = "select * from users where isdelete = 0";
			
			try {
				pstmt = conn.prepareStatement(sql);
				rs = pstmt.executeQuery();
				ulist = new ArrayList<UserDTO>();
				while(rs.next()) {
					UserDTO userDTO = new UserDTO();
					
					userDTO.setUserId(rs.getString("userId"));
					userDTO.setUserPw(rs.getString("userpw"));
					userDTO.setUserName(rs.getString("username"));
					userDTO.setUserAddress(rs.getString("useraddress"));
					userDTO.setUserEmail(rs.getString("useremail"));
					userDTO.setUserPhNumber(rs.getString("userphnumber"));
					ulist.add(userDTO);
				}				
				
			} catch (SQLException e) {
				e.printStackTrace();
			} finally {
				DBconnection.close(conn,pstmt, rs);
			}
			return ulist;
		}
}
