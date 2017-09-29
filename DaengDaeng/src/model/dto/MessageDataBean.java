package model.dto;

import java.sql.Timestamp;

public class MessageDataBean {
	private int msg_code;
	private int msg_sender;
	private int msg_receiver;
	private String msg_sender_name;
	private String msg_receiver_name;
	private Timestamp msg_date;
	private int msg_read_ok;
	private String msg_contents;
	private String msg_title;
	
	public int getMsg_code() {
		return msg_code;
	}
	public void setMsg_code(int msg_code) {
		this.msg_code = msg_code;
	}
	public int getMsg_sender() {
		return msg_sender;
	}
	public void setMsg_sender(int msg_sender) {
		this.msg_sender = msg_sender;
	}
	public int getMsg_receiver() {
		return msg_receiver;
	}
	public void setMsg_receiver(int msg_receiver) {
		this.msg_receiver = msg_receiver;
	}
	public Timestamp getMsg_date() {
		return msg_date;
	}
	public void setMsg_date(Timestamp msg_date) {
		this.msg_date = msg_date;
	}
	public int getMsg_read_ok() {
		return msg_read_ok;
	}
	public void setMsg_read_ok(int msg_read_ok) {
		this.msg_read_ok = msg_read_ok;
	}
	public String getMsg_contents() {
		return msg_contents;
	}
	public void setMsg_contents(String msg_contents) {
		this.msg_contents = msg_contents;
	}
	public String getMsg_title() {
		return msg_title;
	}
	public void setMsg_title(String msg_title) {
		this.msg_title = msg_title;
	}
	public String getMsg_sender_name() {
		return msg_sender_name;
	}
	public void setMsg_sender_name(String msg_sender_name) {
		this.msg_sender_name = msg_sender_name;
	}
	public String getMsg_receiver_name() {
		return msg_receiver_name;
	}
	public void setMsg_receiver_name(String msg_receiver_name) {
		this.msg_receiver_name = msg_receiver_name;
	}

}
