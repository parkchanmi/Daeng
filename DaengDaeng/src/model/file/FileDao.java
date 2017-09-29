package model.file;

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
import model.dto.FileInfoDataBean;
import model.dto.MemberDataBean;
import model.dto.MessageDataBean;
import model.member.MemberDao;

public class FileDao {
	private static FileDao instance = new FileDao();
	public static FileDao getInstance() {
		return instance;
	}

	private FileDao() {
	}

	private Connection getConnection() throws Exception {
		String jdbcDriver = "jdbc:apache:commons:dbcp:pool";
		return DriverManager.getConnection(jdbcDriver);
	}
	
	public int insertFile(FileInfoDataBean fdata){
		int result=-1;
		int filecode=-1;
	    Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			conn = getConnection();
			pstmt = conn.prepareStatement("insert into file_info values (file_sequence.nextval,?,?,?,?)");
			pstmt.setString(1, fdata.getFile_name());
			pstmt.setString(2, fdata.getFile_type());
			pstmt.setInt(3, fdata.getFile_size());
			pstmt.setString(4, fdata.getFile_path());
				
			result=pstmt.executeUpdate();
			if(result==1){//인서트성공
				pstmt = conn.prepareStatement("select file_code from file_info where file_path=?");
				pstmt.setString(1, fdata.getFile_path());
				rs=pstmt.executeQuery();
				if(rs.next()){
					filecode=rs.getInt("file_code");
				}
			}
		
		} catch(Exception ex) {
			ex.printStackTrace();
		} finally {
			if (pstmt != null) try { pstmt.close(); } catch(SQLException ex) {}
			if (conn != null) try { conn.close(); } catch(SQLException ex) {}
		}
		
		return filecode;
	}
	
}
