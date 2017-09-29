package model.review;

import java.sql.*;
import java.util.*;

import model.dto.ReviewDataBean;
import model.member.MemberDao;

public class ReviewDao {

	private static ReviewDao instance = new ReviewDao();

	public static ReviewDao getInstance() {
		return instance;
	}

	private ReviewDao() {
	}

	private Connection getConnection() throws Exception {
		String jdbcDriver = "jdbc:apache:commons:dbcp:pool";
		return DriverManager.getConnection(jdbcDriver);
	}

	// QnA ContentAction 쓴댓글가져오기
	public ArrayList<ReviewDataBean> getReview(String type, int num) throws Exception {

		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		ArrayList articleList = null;
		MemberDao mdb = MemberDao.getInstance();

		String sql = "";

		try {
			conn = getConnection();

			sql = "select * from Review where rev_typecode=? and rev_typenum=?";

			pstmt = conn.prepareStatement(sql);

			pstmt.setString(1, type);
			pstmt.setInt(2, num);

			rs = pstmt.executeQuery();

			if (rs.next()) {
				articleList = new ArrayList(num);
				do {
					ReviewDataBean article = new ReviewDataBean();
					article.setRev_num(rs.getInt("rev_num"));
					article.setRev_memcode(rs.getInt("rev_memcode"));
					article.setRev_typecode(rs.getString("rev_typecode"));
					article.setRev_typenum(rs.getInt("rev_typenum"));
					article.setRev_contents(rs.getString("rev_contents"));
					article.setRev_date(rs.getTimestamp("rev_date"));
					article.setRev_writer_name(mdb.getName(rs.getInt("rev_memcode")));
					System.out.println(mdb.getName(rs.getInt("rev_memcode")));
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

	// QnAreviewPro 댓글쓰기
	public int insertReview(ReviewDataBean reviewarticle) throws Exception {

		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		int result = 0;
		String sql = "";

		try {
			conn = getConnection();

			sql = "insert into Review values(review_sequence.NEXTVAL,?,?,?,?,?)";

			pstmt = conn.prepareStatement(sql);

			pstmt.setInt(1, reviewarticle.getRev_memcode());
			pstmt.setString(2, reviewarticle.getRev_typecode());
			pstmt.setInt(3, reviewarticle.getRev_typenum());
			pstmt.setString(4, reviewarticle.getRev_contents());
			pstmt.setTimestamp(5, reviewarticle.getRev_date());

			System.out.println(reviewarticle.getRev_memcode());

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
	//삭제
	public int deleteArticle(int rev_num) throws Exception {
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		MemberDao mdb = MemberDao.getInstance();
		int x = 0;
		
		String sql = "";
		try {
			conn = getConnection();
			sql = "delete from Review where rev_num=?";
			
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, rev_num);
			
			x = pstmt.executeUpdate();
		} catch (Exception ex) {
			ex.printStackTrace();
		}finally {
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
	//map리뷰 MapDetailAction
	public ArrayList<ReviewDataBean> getMapReview(String rev_typecode, int map_code) throws Exception {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		ArrayList reviewList = new ArrayList<ReviewDataBean>();
		MemberDao mdb = MemberDao.getInstance();

		String sql = "";

		try {
			conn = getConnection();

			sql = "select * from Review where rev_typecode=? and rev_typenum=?";

			pstmt = conn.prepareStatement(sql);

			pstmt.setString(1, rev_typecode);
			pstmt.setInt(2, map_code);
			rs = pstmt.executeQuery();

			if (rs.next()) {
				reviewList = new ArrayList(map_code);
				do {
					ReviewDataBean article = new ReviewDataBean();
					article.setRev_num(rs.getInt("rev_num"));
					article.setRev_memcode(rs.getInt("rev_memcode"));
					article.setRev_typecode(rs.getString("rev_typecode"));
					article.setRev_typenum(rs.getInt("rev_typenum"));
					article.setRev_contents(rs.getString("rev_contents"));
					article.setRev_date(rs.getTimestamp("rev_date"));
					article.setRev_writer_name(mdb.getName(rs.getInt("rev_memcode")));
					System.out.println(mdb.getName(rs.getInt("rev_memcode")));
					reviewList.add(article);
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
		return reviewList;
	}
	//Map리뷰
	public int insertMapReview(ReviewDataBean mapreview) throws Exception {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		int result = 0;
		String sql = "";

		try {
			conn = getConnection();

			sql = "insert into Review values(review_sequence.NEXTVAL,?,?,?,?,?)";

			pstmt = conn.prepareStatement(sql);

			pstmt.setInt(1, mapreview.getRev_memcode());
			pstmt.setString(2, mapreview.getRev_typecode());
			pstmt.setInt(3, mapreview.getRev_typenum());
			pstmt.setString(4, mapreview.getRev_contents());
			pstmt.setTimestamp(5, mapreview.getRev_date());

			System.out.println(mapreview.getRev_memcode());

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

	public int deleteMapArticle(int rev_num) throws Exception {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		MemberDao mdb = MemberDao.getInstance();
		int x = 0;
		
		String sql = "";
		try {
			conn = getConnection();
			sql = "delete from Review where rev_num=?";
			
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, rev_num);
			
			x = pstmt.executeUpdate();
		} catch (Exception ex) {
			ex.printStackTrace();
		}finally {
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
	//Owner리뷰
	public int insertOwnerReview(ReviewDataBean reviewarticle) throws Exception {

		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		int result = 0;
		String sql = "";

		try {
			conn = getConnection();

			sql = "insert into Review values(review_sequence.NEXTVAL,?,?,?,?,?)";

			pstmt = conn.prepareStatement(sql);

			pstmt.setInt(1, reviewarticle.getRev_memcode());
			pstmt.setString(2, reviewarticle.getRev_typecode());
			pstmt.setInt(3, reviewarticle.getRev_typenum());
			pstmt.setString(4, reviewarticle.getRev_contents());
			pstmt.setTimestamp(5, reviewarticle.getRev_date());

			System.out.println(reviewarticle.getRev_memcode());

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
	public ArrayList<ReviewDataBean> getOwnerReview(String type, int num) throws Exception {

		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		ArrayList articleList = null;
		MemberDao mdb = MemberDao.getInstance();

		String sql = "";

		try {
			conn = getConnection();

			sql = "select * from Review where rev_typecode=? and rev_typenum=?";

			pstmt = conn.prepareStatement(sql);

			pstmt.setString(1, type);
			pstmt.setInt(2, num);

			rs = pstmt.executeQuery();

			if (rs.next()) {
				articleList = new ArrayList(num);
				do {
					ReviewDataBean article = new ReviewDataBean();
					article.setRev_num(rs.getInt("rev_num"));
					article.setRev_memcode(rs.getInt("rev_memcode"));
					article.setRev_typecode(rs.getString("rev_typecode"));
					article.setRev_typenum(rs.getInt("rev_typenum"));
					article.setRev_contents(rs.getString("rev_contents"));
					article.setRev_date(rs.getTimestamp("rev_date"));
					article.setRev_writer_name(mdb.getName(rs.getInt("rev_memcode")));
					System.out.println(mdb.getName(rs.getInt("rev_memcode")));
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

	public int insertFreeReview(ReviewDataBean reviewarticle) throws Exception {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		int result = 0;
		String sql = "";

		try {
			conn = getConnection();

			sql = "insert into Review values(review_sequence.NEXTVAL,?,?,?,?,?)";

			pstmt = conn.prepareStatement(sql);

			pstmt.setInt(1, reviewarticle.getRev_memcode());
			pstmt.setString(2, reviewarticle.getRev_typecode());
			pstmt.setInt(3, reviewarticle.getRev_typenum());
			pstmt.setString(4, reviewarticle.getRev_contents());
			pstmt.setTimestamp(5, reviewarticle.getRev_date());

			System.out.println(reviewarticle.getRev_memcode());

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

	public ArrayList<ReviewDataBean> getFreeReview(String rev_typecode, int num) throws Exception {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		ArrayList articleList = null;
		MemberDao mdb = MemberDao.getInstance();

		String sql = "";

		try {
			conn = getConnection();

			sql = "select * from Review where rev_typecode=? and rev_typenum=?";

			pstmt = conn.prepareStatement(sql);

			pstmt.setString(1, rev_typecode);
			pstmt.setInt(2, num);

			rs = pstmt.executeQuery();

			if (rs.next()) {
				articleList = new ArrayList(num);
				do {
					ReviewDataBean article = new ReviewDataBean();
					article.setRev_num(rs.getInt("rev_num"));
					article.setRev_memcode(rs.getInt("rev_memcode"));
					article.setRev_typecode(rs.getString("rev_typecode"));
					article.setRev_typenum(rs.getInt("rev_typenum"));
					article.setRev_contents(rs.getString("rev_contents"));
					article.setRev_date(rs.getTimestamp("rev_date"));
					article.setRev_writer_name(mdb.getName(rs.getInt("rev_memcode")));
					System.out.println(mdb.getName(rs.getInt("rev_memcode")));
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

}