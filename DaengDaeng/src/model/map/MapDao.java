package model.map;

import java.sql.*;
import java.util.*;

import model.dto.MapDataBean;
import model.member.MemberDao;

public class MapDao {

	private static MapDao instance = new MapDao();

	public static MapDao getInstance() {

		return instance;
	}

	private Connection getConnection() throws Exception {
		String jdbcDriver = "jdbc:apache:commons:dbcp:pool";
		return DriverManager.getConnection(jdbcDriver);
	}

	public ArrayList<MapDataBean> getMap(String type) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		ArrayList articleList = new ArrayList();

		String sql = "";

		try {
			conn = getConnection();

			sql = "select * from map where map_type=?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, type);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				MapDataBean article = new MapDataBean();

				article.setMap_code(rs.getInt("map_code"));
				article.setMap_type(rs.getString("map_type"));
				article.setMap_lat(rs.getDouble("map_lat"));
				article.setMap_lng(rs.getDouble("map_lng"));
				article.setMap_corp(rs.getString("map_corp"));
				article.setMap_address(rs.getString("map_address"));
				article.setMap_contents(rs.getString("map_contents"));
				System.out.println(article.getMap_lat());
				articleList.add(article);

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

	public MapDataBean getMapDetail(int map_code) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		MapDataBean map =null;

		String sql = "";

		try {
			conn = getConnection();

			sql = "select * from map where map_code=?";
			pstmt = conn.prepareStatement(sql);
			//pstmt.setString(1, map_code);
			pstmt.setInt(1, map_code);
			rs = pstmt.executeQuery();

			if (rs.next()) {
				map = new MapDataBean();

				map.setMap_code(rs.getInt("map_code"));
				map.setMap_type(rs.getString("map_type"));
				map.setMap_lat(rs.getDouble("map_lat"));
				map.setMap_lng(rs.getDouble("map_lng"));
				map.setMap_corp(rs.getString("map_corp"));
				map.setMap_address(rs.getString("map_address"));
				map.setMap_contents(rs.getString("map_contents"));
				
				System.out.println("detail"+map.getMap_lat());

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
		return map;

	}

}