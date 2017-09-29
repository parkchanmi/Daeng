package model.lost;

import java.sql.*;
import java.util.*;

import model.dto.LostBoardDataBean;
import model.member.MemberDao;

public class OwnerDao {
	private static OwnerDao instance = new OwnerDao();

	public static OwnerDao getInstance() {
		return instance;
	}

	private OwnerDao() {
	}

	private Connection getConnection() throws Exception {
		String jdbcDriver = "jdbc:apache:commons:dbcp:pool";
		return DriverManager.getConnection(jdbcDriver);
	}

	// owner_list.jsp

	public int getArticleCount(String lost_type) throws Exception {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		int i = 0;

		try {
			conn = getConnection();

			pstmt = conn.prepareStatement("select count(*) from Lost_Board where lost_type=?");
			pstmt.setString(1, lost_type);
			rs = pstmt.executeQuery();
			System.out.println(lost_type);
			if (rs.next()) {
				i = rs.getInt(1);
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
		return i;
	}

	// 검색했을때
	public int getArticleCount(String lost_type, int searchn, String search) throws Exception {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		String sql = "";
		String[] column_name = { "lost_writer", "lost_contents" };

		int i = 0;

		try {
			conn = getConnection();

			if (searchn == 0) {
				sql = "select count (*) from lost_board,member where lost_board.lost_writer = member.mem_code and mem_name like'%"
						+ search + "%' and lost_type=?";
			} else {
				sql = "select count(*) from lost_board where " + column_name[searchn] + " like '%" + search
						+ "%' and lost_type=?";
			}

			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, lost_type);
			rs = pstmt.executeQuery();
			System.out.println(lost_type);
			if (rs.next()) {
				i = rs.getInt(1);
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
		return i;
	}

	// 리스트페이징
	public List getArticles(int startRow, int endRow, String lost_type) throws Exception {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		List articleList = null;
		MemberDao mdb = MemberDao.getInstance();

		try {
			conn = getConnection();
			pstmt = conn.prepareStatement("select * from Lost_Board where lost_type=? order by lost_code desc");
			pstmt.setString(1, lost_type);

			/*
			 * "select board_num,board_writer,board_title,board_date,board_contents,r  "
			 * +
			 * "from (select board_num,board_writer,board_title,board_date,board_contents,rownum r"
			 * +
			 * "from (select num,board_writer,board_title,board_date,board_contents,"
			 * + "where r >= ? and r <= ? "
			 */
			// pstmt.setInt(1, start);
			// pstmt.setInt(2, end);
			rs = pstmt.executeQuery();

			if (rs.next()) {
				articleList = new ArrayList(endRow);
				do {
					LostBoardDataBean article = new LostBoardDataBean();

					article.setLost_code(rs.getInt("lost_code"));
					article.setLost_writer(rs.getInt("lost_writer"));
					article.setLost_filecode(0);
					article.setLost_type(rs.getString("lost_type"));
					article.setLost_loc1(rs.getString("lost_loc1"));
					article.setLost_loc2(rs.getString("lost_loc2"));
					article.setLost_date(rs.getTimestamp("lost_date"));
					article.setLost_contents(rs.getString("lost_contents"));
					article.setLost_writer_name(mdb.getName(rs.getInt("lost_writer")));

					articleList.add(article);
				} while (rs.next());
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
		return articleList;

	}
	//검색 페이징
	public List getArticles(int startRow, int endRow, String lost_type, int searchn, String search) throws Exception {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		List articleList = null;
		MemberDao mdb = MemberDao.getInstance();
		String sql = "";
		String[] column_name = {"board_writer_name", "lost_contents"};
		
		try {
			conn = getConnection();
			if (searchn==0)	{
				sql="select lost_code,lost_writer,mem_name,lost_filecode,lost_type,lost_loc1,lost_loc2,lost_date,lost_contents "
						+ "FROM  lost_board,member "
						+ "where lost_board.lost_writer = member.mem_code "
						+ "and mem_name like '%"+search+"%' and lost_type=?";
			}else {
				sql="select * from lost_board where "+column_name[searchn]+" like '%"+search+"%' and lost_type=?";
			}
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, lost_type);

			/*
			 * "select board_num,board_writer,board_title,board_date,board_contents,r  "
			 * +
			 * "from (select board_num,board_writer,board_title,board_date,board_contents,rownum r"
			 * +
			 * "from (select num,board_writer,board_title,board_date,board_contents,"
			 * + "where r >= ? and r <= ? "
			 */
			// pstmt.setInt(1, start);
			// pstmt.setInt(2, end);
			rs = pstmt.executeQuery();

			if (rs.next()) {
				articleList = new ArrayList(endRow);
				do {
					LostBoardDataBean article = new LostBoardDataBean();

					article.setLost_code(rs.getInt("lost_code"));
					article.setLost_writer(rs.getInt("lost_writer"));
					article.setLost_filecode(0);
					article.setLost_type(rs.getString("lost_type"));
					article.setLost_loc1(rs.getString("lost_loc1"));
					article.setLost_loc2(rs.getString("lost_loc2"));
					article.setLost_date(rs.getTimestamp("lost_date"));
					article.setLost_contents(rs.getString("lost_contents"));
					article.setLost_writer_name(mdb.getName(rs.getInt("lost_writer")));

					articleList.add(article);
				} while (rs.next());
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
		return articleList;

	}

	// contents
	public LostBoardDataBean getArticle(int lost_code) throws Exception {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		LostBoardDataBean article = null;
		MemberDao mdb = MemberDao.getInstance();
		try {
			conn = getConnection();

			pstmt = conn.prepareStatement("select * from Lost_Board where lost_code = ?");
			pstmt.setInt(1, lost_code);
			rs = pstmt.executeQuery();

			if (rs.next()) {

				article = new LostBoardDataBean();

				article.setLost_code(rs.getInt("lost_code"));
				article.setLost_writer(rs.getInt("lost_writer"));
				article.setLost_filecode(rs.getInt("lost_filecode"));
				article.setLost_loc1(rs.getString("lost_loc1"));
				article.setLost_loc2(rs.getString("lost_loc2"));
				article.setLost_date(rs.getTimestamp("lost_date"));
				article.setLost_contents(rs.getString("lost_contents"));
				article.setLost_writer_name(mdb.getName(rs.getInt("lost_writer")));

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
		return article;
	}

	// writePro
	public int insertArticle(LostBoardDataBean article) throws Exception {
		// TODO Auto-generated method stub

		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		int result = 0;

		String sql = "";

		try {
			conn = getConnection();

			sql = "insert into Lost_Board values(lboard_sequence.NEXTVAL,?,?,?,?,?,?,?)";

			pstmt = conn.prepareStatement(sql);

			pstmt.setInt(1, article.getLost_writer());
			pstmt.setInt(2, article.getLost_filecode());
			pstmt.setString(3, article.getLost_type());
			pstmt.setString(4, article.getLost_loc1());
			pstmt.setString(5, article.getLost_loc2());
			pstmt.setTimestamp(6, article.getLost_date());
			pstmt.setString(7, article.getLost_contents());

			System.out.println(article.getLost_contents());

			result = pstmt.executeUpdate();

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

	// updateForm
	public LostBoardDataBean updateGetArticle(int num) throws Exception {

		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		LostBoardDataBean article = null;
		MemberDao mdb = MemberDao.getInstance();
		try {
			conn = getConnection();

			pstmt = conn.prepareStatement("select * from Lost_Board where lost_code =?");
			pstmt.setInt(1, num);
			rs = pstmt.executeQuery();

			if (rs.next()) {
				article = new LostBoardDataBean();

				article.setLost_code(rs.getInt("lost_code"));
				article.setLost_writer(rs.getInt("lost_writer"));
				article.setLost_filecode(rs.getInt("lost_filecode"));
				article.setLost_type(rs.getString("lost_type"));
				article.setLost_loc1(rs.getString("lost_loc1"));
				article.setLost_loc2(rs.getString("lost_loc2"));
				article.setLost_date(rs.getTimestamp("lost_date"));
				article.setLost_contents(rs.getString("lost_contents"));
				article.setLost_writer_name(mdb.getName(rs.getInt("lost_writer")));

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
		return article;
	}

	// updatePro
	public int updateArticle(LostBoardDataBean article) throws Exception {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		MemberDao mdb = MemberDao.getInstance();
		int x = 0;

		String sql = "";

		try {
			conn = getConnection();

			sql = "update lost_board set lost_loc1=?, lost_loc2=?, lost_date=?, lost_contents=? where lost_code=?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, article.getLost_loc1());
			pstmt.setString(2, "지역2");
			pstmt.setTimestamp(3, article.getLost_date());
			pstmt.setString(4, article.getLost_contents());
			pstmt.setInt(5, article.getLost_code());

			x = pstmt.executeUpdate();

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
		return x;
	}

	// deletePro
	public int deleteArticle(int lost_code) throws Exception {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		MemberDao mdb = MemberDao.getInstance();
		int x = 0;

		String sql = "";
		try {
			conn = getConnection();
			sql = "delete from Lost_Board where lost_code = ?";

			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, lost_code);

			x = pstmt.executeUpdate();

			/*
			 * try { conn = getConnection();
			 * 
			 * pstmt =
			 * conn.prepareStatement("select * from board where lost_code = ?");
			 * pstmt.setInt(1, num); rs = pstmt.executeQuery();
			 * 
			 * if (rs.next()) { dbpasswd = rs.getString("passwd"); if
			 * (dbpasswd.equals(passwd)) { pstmt =
			 * conn.prepareStatement("delete from board where num=?");
			 * pstmt.setInt(1, num); pstmt.executeUpdate(); x = 1; // 글삭제 성공 }
			 * else x = 0; // 비밀번호 틀림 }
			 */
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
		return x;
	}

	// deleteForm
	public LostBoardDataBean deleteGetArticle(int num) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		LostBoardDataBean article = null;
		MemberDao mdb = MemberDao.getInstance();
		try {
			conn = getConnection();

			pstmt = conn.prepareStatement("select * from Lost_Board where lost_code =?");
			pstmt.setInt(1, num);
			rs = pstmt.executeQuery();

			if (rs.next()) {
				article = new LostBoardDataBean();

				article.setLost_code(rs.getInt("lost_code"));
				article.setLost_writer(rs.getInt("lost_writer"));
				article.setLost_filecode(rs.getInt("lost_filecode"));
				article.setLost_type(rs.getString("lost_type"));
				article.setLost_loc1(rs.getString("lost_loc1"));
				article.setLost_loc2(rs.getString("lost_loc2"));
				article.setLost_date(rs.getTimestamp("lost_date"));
				article.setLost_contents(rs.getString("lost_contents"));
				article.setLost_writer_name(mdb.getName(rs.getInt("lost_writer")));

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
		return article;
	}
}