package model.free;

import java.sql.*;
import java.util.*;

import model.dto.BoardDataBean;
import model.member.MemberDao;

public class FreeDao {

	private static FreeDao instance = new FreeDao();

	public static FreeDao getInstance() {
		return instance;
	}

	private FreeDao() {
	}

	private Connection getConnection() throws Exception {
		String jdbcDriver = "jdbc:apache:commons:dbcp:pool";
		return DriverManager.getConnection(jdbcDriver);
	}

	public int getArticleCount(String board_type) throws Exception {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		int x = 0;

		try {
			conn = getConnection();

			pstmt = conn.prepareStatement("select count(*) from Board where board_type = ? ");
			pstmt.setString(1, board_type);
			rs = pstmt.executeQuery();

			if (rs.next()) {
				x = rs.getInt(1);
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
		return x;
	}

	public int getArticleCount(int searchn, String search, String board_type) throws Exception {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = "";

		String[] column_name = { "board_writer", "board_title", "board_contents" };

		int x = 0;

		try {
			conn = getConnection();

			if (searchn == 0) {
				sql = "select count (*) from board,member where board.board_writer = member.mem_code and mem_name like'%"
						+ search + "%' and board_type=? ";
			} else {
				sql = "select count(*) from Board where " + column_name[searchn] + " like '%" + search
						+ "%' and board_type=? ";
			}

			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, board_type);
			rs = pstmt.executeQuery();

			if (rs.next()) {
				x = rs.getInt(1);
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
		return x;
	}

	public List getArticles(int startRow, int endRow, String board_type) throws Exception {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		List articleList = null;
		MemberDao mdb = MemberDao.getInstance();

		try {
			conn = getConnection();
			pstmt = conn.prepareStatement("select * from (select rownum r, board_num, board_writer, board_title,board_date,board_type,board_rdcount,board_contents from"
					+ " (select * from board where board_type=? order by board_date desc)) where r>=? and r<=? ");
			pstmt.setString(1, board_type);
			pstmt.setInt(2, startRow);
			pstmt.setInt(3,endRow);
			rs = pstmt.executeQuery();
			System.out.println("s:"+startRow+"e:"+endRow);
			if (rs.next()) {
				articleList = new ArrayList(endRow);
				do {
					BoardDataBean article = new BoardDataBean();
					article.setBoard_num(rs.getInt("board_num"));
					article.setBoard_writer(rs.getInt("board_writer"));
					article.setBoard_title(rs.getString("board_title"));
					article.setBoard_date(rs.getTimestamp("board_date"));
					article.setBoard_rdcount(rs.getInt("board_rdcount"));
					article.setBoard_contents(rs.getString("board_contents"));
					article.setBoard_filecode(0);
					article.setBoard_type(rs.getString("board_type"));
					article.setBoard_writer_name(mdb.getName(rs.getInt("board_writer")));
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

	public List getArticles(int startRow, int endRow, int searchn, String search, String board_type) throws Exception {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		List articleList = null;
		MemberDao mdb = MemberDao.getInstance();
		System.out.println("s:"+startRow+"e:"+endRow);
		String sql = "";
		String[] column_name = { "board_writer_name", "board_title", "board_contents" };

		try {
			conn = getConnection();
			if (searchn == 0) {
				sql = "SELECT board_num,board_writer,mem_name,board_filecode,board_title,board_date,board_type,board_rdcount,board_contents,board_ref,board_step,board_level "
						+ "FROM  board,member " + "where board.board_writer = member.mem_code " + "and mem_name like '%"
						+ search + "%' and board_type=?";
			} else {
				sql = "select * from board where " + column_name[searchn] + " like '%" + search
						+ "%' and board_type=? ";
			}
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, board_type);
			rs = pstmt.executeQuery();

			if (rs.next()) {
				articleList = new ArrayList(endRow);
				do {
					BoardDataBean article = new BoardDataBean();
					article.setBoard_num(rs.getInt("board_num"));
					article.setBoard_writer(rs.getInt("board_writer"));
					article.setBoard_title(rs.getString("board_title"));
					article.setBoard_date(rs.getTimestamp("board_date"));
					article.setBoard_rdcount(rs.getInt("board_rdcount"));
					article.setBoard_ref(rs.getInt("board_ref"));
					article.setBoard_step(rs.getInt("board_step"));
					article.setBoard_level(rs.getInt("board_level"));
					article.setBoard_contents(rs.getString("board_contents"));
					article.setBoard_filecode(0);
					article.setBoard_type(rs.getString("board_type"));
					article.setBoard_writer_name(mdb.getName(rs.getInt("board_writer")));
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

	// 글쓰기 Pro
	public int insertArticle(BoardDataBean article, String board_type) throws Exception {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		int num = article.getNum();
		int board_ref = article.getBoard_ref();
		int board_step = article.getBoard_step();
		int board_level = article.getBoard_level();
		int number = 0;
		int result=0;
		String sql = "";

		try {
			conn = getConnection();

			pstmt = conn.prepareStatement("select max(board_num) from Board");
	
			rs = pstmt.executeQuery();

			if (rs.next())
				number = rs.getInt(1) + 1;// 글번호
			else
				number = 1;

			
				board_ref = number;
				board_step = 0;
				board_level = 0;
			

			sql = "insert into Board values(nboard_sequence.NEXTVAL,?,?,?,?,?,?,?,?,?,?)";

			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, article.getBoard_writer());
			pstmt.setInt(2, article.getBoard_filecode());
			pstmt.setString(3, article.getBoard_title());
			pstmt.setTimestamp(4, article.getBoard_date());
			pstmt.setString(5, article.getBoard_type());
			pstmt.setInt(6, article.getBoard_rdcount());// 조회수
			pstmt.setString(7, article.getBoard_contents());
			pstmt.setInt(8, article.getBoard_ref());
			pstmt.setInt(9, article.getBoard_step());
			pstmt.setInt(10, article.getBoard_level());
			result = pstmt.executeUpdate();
			System.out.println(result);
		} catch (Exception e) {
			e.printStackTrace();
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

	// contents
	public BoardDataBean getArticleCount(int num, String board_type) throws Exception {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		BoardDataBean article = null;
		MemberDao mdb = MemberDao.getInstance();
		try {
			conn = getConnection();

			pstmt = conn.prepareStatement("update Board set board_rdcount=board_rdcount+1 where board_num = ?");
			pstmt.setInt(1, num);
			pstmt.executeUpdate();

			pstmt = conn.prepareStatement("select * from Board where board_num = ? and board_type = ?");
			pstmt.setInt(1, num);
			pstmt.setString(2, board_type);
			rs = pstmt.executeQuery();

			if (rs.next()) {
				article = new BoardDataBean();
				article.setBoard_num(rs.getInt("board_num"));
				article.setBoard_writer(rs.getInt("board_writer"));
				article.setBoard_filecode(rs.getInt("board_filecode"));
				article.setBoard_title(rs.getString("board_title"));
				article.setBoard_date(rs.getTimestamp("board_date"));
				article.setBoard_rdcount(rs.getInt("board_rdcount"));
				article.setBoard_ref(rs.getInt("board_ref"));
				article.setBoard_step(rs.getInt("board_step"));
				article.setBoard_level(rs.getInt("board_level"));
				article.setBoard_contents(rs.getString("board_contents"));
				article.setBoard_writer_name(mdb.getName(rs.getInt("board_writer")));
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
	//updateForm
	public BoardDataBean updateGetArticle(int num) throws Exception {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		BoardDataBean article = null;
		MemberDao mdb = MemberDao.getInstance();
		try {
			conn = getConnection();

			pstmt = conn.prepareStatement("select * from Board where board_num =?");
			pstmt.setInt(1, num);
			rs = pstmt.executeQuery();

			if (rs.next()) {
				article = new BoardDataBean();

				article.setBoard_num(rs.getInt("board_num"));
				article.setBoard_writer(rs.getInt("board_writer"));
				article.setBoard_filecode(rs.getInt("board_filecode"));
				article.setBoard_title(rs.getString("board_title"));
				article.setBoard_date(rs.getTimestamp("board_date"));
				article.setBoard_type(rs.getString("board_type"));
				article.setBoard_rdcount(rs.getInt("board_rdcount"));
				article.setBoard_contents(rs.getString("board_contents"));
				article.setBoard_ref(rs.getInt("board_ref"));
				article.setBoard_step(rs.getInt("board_step"));
				article.setBoard_level(rs.getInt("board_level"));
				article.setBoard_writer_name(mdb.getName(rs.getInt("board_writer")));

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
	//updatePro
	public int updateArtilce(BoardDataBean article) throws Exception {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		int x = 0;
		
		String sql = "";

		try {
			conn = getConnection();

			sql = "update board set board_title=?, board_date=?, board_contents=? where board_num=?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, article.getBoard_title());
			pstmt.setTimestamp(2, article.getBoard_date());
			pstmt.setString(3, article.getBoard_contents());
			pstmt.setInt(4, article.getBoard_num());

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
	//delete Form
	public BoardDataBean deleteGetArticle(int num) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		BoardDataBean article = null;
		MemberDao mdb = MemberDao.getInstance();
		try {
			conn = getConnection();

			pstmt = conn.prepareStatement("select * from Board where board_num =?");
			pstmt.setInt(1, num);
			rs = pstmt.executeQuery();

			if (rs.next()) {
				article = new BoardDataBean();

				article.setBoard_num(rs.getInt("board_num"));
				article.setBoard_writer(rs.getInt("board_writer"));
				article.setBoard_filecode(rs.getInt("board_filecode"));
				article.setBoard_title(rs.getString("board_title"));
				article.setBoard_date(rs.getTimestamp("board_date"));
				article.setBoard_type(rs.getString("board_type"));
				article.setBoard_rdcount(rs.getInt("board_rdcount"));
				article.setBoard_contents(rs.getString("board_contents"));
				article.setBoard_ref(rs.getInt("board_ref"));
				article.setBoard_step(rs.getInt("board_step"));
				article.setBoard_level(rs.getInt("board_level"));
				article.setBoard_writer_name(mdb.getName(rs.getInt("board_writer")));

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

	public int deleteArticle(int board_num) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		MemberDao mdb = MemberDao.getInstance();
		int x = 0;

		String sql = "";
		try {
			conn = getConnection();
			sql = "delete from Board where board_num = ?";

			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, board_num);

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

}