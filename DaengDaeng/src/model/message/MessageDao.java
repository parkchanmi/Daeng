package model.message;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import model.dto.MemberDataBean;
import model.dto.MessageDataBean;
import model.member.MemberDao;

public class MessageDao{

private static MessageDao instance = new MessageDao();
MemberDao mdo = MemberDao.getInstance();
public static MessageDao getInstance() {
	return instance;
}

private MessageDao() {
}

private Connection getConnection() throws Exception {
	String jdbcDriver = "jdbc:apache:commons:dbcp:pool";
	return DriverManager.getConnection(jdbcDriver);
}

public int sendMessage(MessageDataBean msgData){
	Connection conn = null;
	PreparedStatement pstmt = null;
	ResultSet rs = null;
	int result=0;
	try {
		conn = getConnection();
		//String query = "select * from member where mem_type='관리자'";
		String query = "insert into message values(send_message.nextval,?,?,?,?,?,?)";
		pstmt = conn.prepareStatement(query);
		pstmt.setInt(1, msgData.getMsg_sender());
		pstmt.setInt(2, msgData.getMsg_receiver());
		pstmt.setTimestamp(3, msgData.getMsg_date());
		pstmt.setInt(4, msgData.getMsg_read_ok());
		pstmt.setString(5, msgData.getMsg_contents());
		pstmt.setString(6, msgData.getMsg_title());
		result=pstmt.executeUpdate();//쿼리실행
		
		
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

public MessageDataBean getMessage(int msgID){//보낸쪽지읽기
	Connection conn = null;
	PreparedStatement pstmt = null;
	ResultSet rs = null;
	MessageDataBean msgData = null;
	int result=0;
	try {
		conn = getConnection();
		String query = "select * from message where msg_code=?";
		pstmt = conn.prepareStatement(query);
		pstmt.setInt(1, msgID);
		rs=pstmt.executeQuery();
		if(rs.next()){
			msgData = new MessageDataBean();
			msgData.setMsg_code(rs.getInt(1));
			msgData.setMsg_sender(rs.getInt(2));
			msgData.setMsg_receiver(rs.getInt(3));
			msgData.setMsg_date(rs.getTimestamp(4));
			msgData.setMsg_read_ok(rs.getInt(5));
			msgData.setMsg_contents(rs.getString(6));
			msgData.setMsg_title(rs.getString(7));
			msgData.setMsg_sender_name(mdo.getName(rs.getInt(2)));
			msgData.setMsg_receiver_name(mdo.getName(rs.getInt(3)));
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
	return msgData;
}
public MessageDataBean getMessage(int msgID, String receiver){//보낸쪽지읽기
	Connection conn = null;
	PreparedStatement pstmt = null;
	ResultSet rs = null;
	MessageDataBean msgData = null;
	int result=0;
	try {
		conn = getConnection();
		String query = "select * from message where msg_code=?";
		pstmt = conn.prepareStatement(query);
		pstmt.setInt(1, msgID);
		rs=pstmt.executeQuery();
		if(rs.next()){
			String sql = "update message set msg_read_ok=1 where msg_code=?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, msgID);
			pstmt.executeUpdate();
			msgData = new MessageDataBean();
			msgData.setMsg_code(rs.getInt(1));
			msgData.setMsg_sender(rs.getInt(2));
			msgData.setMsg_receiver(rs.getInt(3));
			msgData.setMsg_date(rs.getTimestamp(4));
			msgData.setMsg_read_ok(rs.getInt(5));
			msgData.setMsg_contents(rs.getString(6));
			msgData.setMsg_title(rs.getString(7));
			msgData.setMsg_sender_name(mdo.getName(rs.getInt(2)));
			msgData.setMsg_receiver_name(mdo.getName(rs.getInt(3)));
			
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
	return msgData;
}

public ArrayList<MessageDataBean> getS_Message(int sender){
	Connection conn = null;
	PreparedStatement pstmt = null;
	ResultSet rs = null;
	ArrayList<MessageDataBean> mList = new ArrayList<MessageDataBean>();
	int result=0;
	try {
		conn = getConnection();
		String query = "select * from message where msg_sender=?";
		pstmt = conn.prepareStatement(query);
		pstmt.setInt(1, sender);
		rs=pstmt.executeQuery();
		while(rs.next()){
			MessageDataBean msgData = new MessageDataBean();
			msgData.setMsg_code(rs.getInt(1));
			msgData.setMsg_sender(rs.getInt(2));
			msgData.setMsg_receiver(rs.getInt(3));
			msgData.setMsg_date(rs.getTimestamp(4));
			msgData.setMsg_read_ok(rs.getInt(5));
			msgData.setMsg_contents(rs.getString(6));
			msgData.setMsg_title(rs.getString(7));
			msgData.setMsg_sender_name(mdo.getName(rs.getInt(2)));
			msgData.setMsg_receiver_name(mdo.getName(rs.getInt(3)));
			mList.add(msgData);
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
public ArrayList<MessageDataBean> getR_Message(int receiver){
	Connection conn = null;
	PreparedStatement pstmt = null;
	ResultSet rs = null;
	int result=0;
	ArrayList<MessageDataBean> mList = new ArrayList<MessageDataBean>();

	try {
		conn = getConnection();
		String query = "select * from message where msg_receiver=?";
		pstmt = conn.prepareStatement(query);
		pstmt.setInt(1, receiver);
		rs=pstmt.executeQuery();
		while(rs.next()){
			MessageDataBean msgData = new MessageDataBean();
			msgData.setMsg_code(rs.getInt(1));
			msgData.setMsg_sender(rs.getInt(2));
			msgData.setMsg_receiver(rs.getInt(3));
			msgData.setMsg_date(rs.getTimestamp(4));
			msgData.setMsg_read_ok(rs.getInt(5));
			msgData.setMsg_contents(rs.getString(6));
			msgData.setMsg_title(rs.getString(7));
			msgData.setMsg_sender_name(mdo.getName(rs.getInt(2)));
			msgData.setMsg_receiver_name(mdo.getName(rs.getInt(3)));
			mList.add(msgData);
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


}}