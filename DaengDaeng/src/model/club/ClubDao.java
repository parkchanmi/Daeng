package model.club;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;

import model.admin.AdminDao;
import model.dto.ClubBoardDataBean;
import model.dto.ClubInfoDataBean;
import model.dto.ClubJoinDataBean;
import model.dto.MemberDataBean;
import model.dto.MessageDataBean;
import model.member.MemberDao;

public class ClubDao {
	private static ClubDao instance = new ClubDao();
	//MemberDao mdo = MemberDao.getInstance();
	public static ClubDao getInstance() {
		return instance;
	}

	private ClubDao() {
	}

	private Connection getConnection() throws Exception {
		String jdbcDriver = "jdbc:apache:commons:dbcp:pool";
		return DriverManager.getConnection(jdbcDriver);
	}
	public int joinOutClub(int memcode,int cicode){
		Connection conn = null;
		PreparedStatement pstmt = null;
		int result=0;
		try {
			conn = getConnection();
			//String query = "select * from member where mem_type='관리자'";
			
			String query = "delete from club_join where cj_memcode=? and cj_cicode=?";
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, memcode);
			pstmt.setInt(2, cicode);
			result=pstmt.executeUpdate();//쿼리실행
			
			
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			
			if (pstmt != null)
				try {
					pstmt.close();
				} catch (SQLException ex) {
				}
			if (conn != null)
				try {
					conn.close();
				} catch (SQLException ex) {
				}
		}
		return result;
	}
	
	public int joinOutClub(int cjcode){
		Connection conn = null;
		PreparedStatement pstmt = null;
		int result=0;
		try {
			conn = getConnection();
			//String query = "select * from member where mem_type='관리자'";
			String query = "delete from club_join where cj_code=?";
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, cjcode);
			result=pstmt.executeUpdate();//쿼리실행
			
			
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			
			if (pstmt != null)
				try {
					pstmt.close();
				} catch (SQLException ex) {
				}
			if (conn != null)
				try {
					conn.close();
				} catch (SQLException ex) {
				}
		}
		return result;
	}
	
	public int joinClub(int cicode, int memcode){
		Connection conn = null;
		PreparedStatement pstmt = null;
		int result=0;
		try {
			conn = getConnection();
			//String query = "select * from member where mem_type='관리자'";
			String query = "insert into club_join values(join_club_sequence.nextval,?,?,?)";
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, memcode);
			pstmt.setInt(2, cicode);
			pstmt.setTimestamp(3, new Timestamp(System.currentTimeMillis()));
			result=pstmt.executeUpdate();//쿼리실행
			
			
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			
			if (pstmt != null)
				try {
					pstmt.close();
				} catch (SQLException ex) {
				}
			if (conn != null)
				try {
					conn.close();
				} catch (SQLException ex) {
				}
		}
		return result;
	}
	//소모임 개설
	public int createClub(ClubInfoDataBean clubData){
		Connection conn = null;
		PreparedStatement pstmt = null;
		int result=0;
		try {
			conn = getConnection();
			//String query = "select * from member where mem_type='관리자'";
			String query = "insert into club_info values(cre_club_sequence.nextval,?,?,?,?,?,?)";
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, clubData.getCi_leader());
			pstmt.setInt(2, clubData.getCi_file_code());
			pstmt.setString(3, clubData.getCi_type());
			pstmt.setString(4, clubData.getCi_name());
			pstmt.setString(5, clubData.getCi_intro());
			pstmt.setTimestamp(6, clubData.getCi_date());
			result=pstmt.executeUpdate();//쿼리실행
			if(result==1){//개설이 성공적으로 되면 첫번째 가입자가됨
				String query2 = "insert into club_join values(join_club_sequence.nextval,?,cre_club_sequence.currval,?)";
				pstmt = conn.prepareStatement(query2);
				pstmt.setInt(1, clubData.getCi_leader());//개설자가 멤버가됨
				pstmt.setTimestamp(2, new Timestamp(System.currentTimeMillis()));
				
				result=pstmt.executeUpdate();//쿼리실행
			}
			
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			
			if (pstmt != null)
				try {
					pstmt.close();
				} catch (SQLException ex) {
				}
			if (conn != null)
				try {
					conn.close();
				} catch (SQLException ex) {
				}
		}
		return result;
	}
	//소모임 전체
	public ArrayList<ClubInfoDataBean> getAll_Club(){
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		ArrayList<ClubInfoDataBean> cList = new ArrayList<ClubInfoDataBean>();
		int result=0;
		try {
			MemberDao mdpro = MemberDao.getInstance();
			conn = getConnection();
			String query = "select * from club_info";
			pstmt = conn.prepareStatement(query);
			rs=pstmt.executeQuery();
			while(rs.next()){
				ClubInfoDataBean clubData = new ClubInfoDataBean();
				clubData.setCi_code(rs.getInt(1));
				clubData.setCi_leader(rs.getInt(2));
				clubData.setCi_file_code(rs.getInt(3));
				clubData.setCi_type(rs.getString(4));
				clubData.setCi_name(rs.getString(5));
				clubData.setCi_intro(rs.getString(6));
				clubData.setCi_date(rs.getTimestamp(7));
				clubData.setLeader_name(mdpro.getName(rs.getInt(2)));
				
				cList.add(clubData);
			}
		
			System.out.println(cList.size());
			
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			if (rs != null)
				try {
					rs.close();
				} catch (SQLException ex) {
				}
			if (pstmt != null)
				try {
					pstmt.close();
				} catch (SQLException ex) {
				}
			if (conn != null)
				try {
					conn.close();
				} catch (SQLException ex) {
				}
		}
		return cList;
	}
	//소모임 검색결과
	
	public boolean IsLeader(int ciCode,int memCode){
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		boolean result=false;
		ClubJoinDataBean joinData = null;
		try {
			conn = getConnection();
			String query = "select * from club_info where ci_code=? and ci_leader=?";
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, ciCode);
			pstmt.setInt(2, memCode);
			rs=pstmt.executeQuery();
			if(rs.next()){
				result=true;
			}
		
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			if (rs != null)
				try {
					rs.close();
				} catch (SQLException ex) {
				}
			if (pstmt != null)
				try {
					pstmt.close();
				} catch (SQLException ex) {
				}
			if (conn != null)
				try {
					conn.close();
				} catch (SQLException ex) {
				}
		}
		return result;
	}
	public ClubJoinDataBean IsMember(int memCode,int ciCode){
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		ClubJoinDataBean joinData = null;
		try {
			conn = getConnection();
			String query = "select * from club_join where cj_memcode=? and cj_cicode=?";
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, memCode);
			pstmt.setInt(2, ciCode);
			rs=pstmt.executeQuery();
			if(rs.next()){
				joinData = new ClubJoinDataBean();
				joinData.setCj_code(rs.getInt(1));
				joinData.setCj_memcode(rs.getInt(2));
				joinData.setCj_cicode(rs.getInt(3));
				joinData.setCj_date(rs.getTimestamp(4));
			}
		
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			if (rs != null)
				try {
					rs.close();
				} catch (SQLException ex) {
				}
			if (pstmt != null)
				try {
					pstmt.close();
				} catch (SQLException ex) {
				}
			if (conn != null)
				try {
					conn.close();
				} catch (SQLException ex) {
				}
		}
		return joinData;
	}
	//소모임 데이터
	public ClubInfoDataBean getClub_Info(int ciCode){
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		ClubInfoDataBean clubData = null;
		int result=0;
		try {
			MemberDao mdpro=MemberDao.getInstance();
			conn = getConnection();
			String query = "select * from club_info where ci_code=?";
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, ciCode);
			rs=pstmt.executeQuery();
			if(rs.next()){
				clubData = new ClubInfoDataBean();
				clubData.setCi_code(rs.getInt(1));
				clubData.setCi_leader(rs.getInt(2));
				clubData.setCi_file_code(rs.getInt(3));
				clubData.setCi_type(rs.getString(4));
				clubData.setCi_name(rs.getString(5));
				clubData.setCi_intro(rs.getString(6));
				clubData.setCi_date(rs.getTimestamp(7));
				clubData.setLeader_name(mdpro.getName(rs.getInt(2)));
				String sql="select file_path from file_info where file_code=?";
				pstmt = conn.prepareStatement(sql);
				pstmt.setInt(1, rs.getInt(3));
				rs=pstmt.executeQuery();
				if(rs.next()){
					clubData.setCi_filepath(rs.getString(1));
				}else{
					clubData.setCi_filepath("http://localhost:8081/DaengDaeng/upload/noimage.gif");
				}
			}
		
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			if (rs != null)
				try {
					rs.close();
				} catch (SQLException ex) {
				}
			if (pstmt != null)
				try {
					pstmt.close();
				} catch (SQLException ex) {
				}
			if (conn != null)
				try {
					conn.close();
				} catch (SQLException ex) {
				}
		}
		return clubData;
	}
	//내가 가입한 소모임
	public ArrayList<ClubInfoDataBean> getMy_Club(int memCode){
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		ArrayList<ClubInfoDataBean> cList = new ArrayList<ClubInfoDataBean>();
		int result=0;
		try {
			conn = getConnection();
			String query = "select * from club_join where cj_memcode=?";
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, memCode);
			rs=pstmt.executeQuery();
			while(rs.next()){
				int cicode=rs.getInt("cj_cicode");
				ClubInfoDataBean clubData = getClub_Info(cicode);
				cList.add(clubData);
			}
		
			System.out.println(cList.size());
			
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			if (rs != null)
				try {
					rs.close();
				} catch (SQLException ex) {
				}
			if (pstmt != null)
				try {
					pstmt.close();
				} catch (SQLException ex) {
				}
			if (conn != null)
				try {
					conn.close();
				} catch (SQLException ex) {
				}
		}
		return cList;
	}
	public ArrayList<MemberDataBean> getClub_member(int ciCode){
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		ArrayList<MemberDataBean> mList = new ArrayList<MemberDataBean>();
		int result=0;
		try {
			conn = getConnection();
			String query = "select * from club_join where cj_cicode=?";
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, ciCode);
			rs=pstmt.executeQuery();
			MemberDao mdo = MemberDao.getInstance();
			while(rs.next()){
				int memcode=rs.getInt("cj_memcode");
				MemberDataBean memData = mdo.getMember(memcode);
				mList.add(memData);
			}
		
			System.out.println(mList.size());
			
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			if (rs != null)
				try {
					rs.close();
				} catch (SQLException ex) {
				}
			if (pstmt != null)
				try {
					pstmt.close();
				} catch (SQLException ex) {
				}
			if (conn != null)
				try {
					conn.close();
				} catch (SQLException ex) {
				}
		}
		return mList;
	}
	public int writeClubBoard(ClubBoardDataBean cbdata){
		Connection conn = null;
		PreparedStatement pstmt = null;
		int result=0;
		try {
			conn = getConnection();
			String query = "insert into club_board values(cboard_sequence.nextval,?,?,?,?)";
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, cbdata.getCb_cjcode());
			pstmt.setString(2, cbdata.getCb_contents());
			pstmt.setTimestamp(3, cbdata.getCb_date());
			pstmt.setInt(4, cbdata.getCb_filecode());
			result=pstmt.executeUpdate();//쿼리실행
			
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			
			if (pstmt != null)
				try {
					pstmt.close();
				} catch (SQLException ex) {
				}
			if (conn != null)
				try {
					conn.close();
				} catch (SQLException ex) {
				}
		}
		return result;
	}
	public ArrayList<ClubBoardDataBean> getClubArticles(int cicode){
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		ArrayList<ClubBoardDataBean> cList = new ArrayList<ClubBoardDataBean>();
		int result=0;
		try {
			conn = getConnection();
			String query = "select a.cb_num,a.cb_cjcode,a.cb_contents,a.cb_date,a.cb_filecode,b.cj_memcode "
					+ "from club_board a,club_join b where a.CB_CJCODE=b.CJ_CODE and b.CJ_CICODE=?";
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, cicode);
			rs=pstmt.executeQuery();
			MemberDao mdb = MemberDao.getInstance();
			while(rs.next()){
				
				ClubBoardDataBean cbData = new ClubBoardDataBean();
				cbData.setCb_num(rs.getInt(1));
				cbData.setCb_cjcode(rs.getInt(2));
				cbData.setCb_contents(rs.getString(3));
				cbData.setCb_date(rs.getTimestamp(4));
				cbData.setCb_filecode(rs.getInt(5));
				cbData.setCb_writer(rs.getInt(6));
				cbData.setCb_writer_name(mdb.getName(rs.getInt(6)));
				cList.add(cbData);
			}
		
			System.out.println(cList.size());
			
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			if (rs != null)
				try {
					rs.close();
				} catch (SQLException ex) {
				}
			if (pstmt != null)
				try {
					pstmt.close();
				} catch (SQLException ex) {
				}
			if (conn != null)
				try {
					conn.close();
				} catch (SQLException ex) {
				}
		}
		return cList;
		
	}
	
	
}
