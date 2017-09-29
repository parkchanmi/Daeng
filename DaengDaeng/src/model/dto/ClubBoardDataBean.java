package model.dto;

import java.sql.Timestamp;

public class ClubBoardDataBean {
	private int cb_num;
	private int cb_cjcode;
	private String cb_contents;
	private Timestamp cb_date;
	private int cb_filecode;
	private int cb_writer;
	private String cb_writer_name;
	
	public int getCb_num() {
		return cb_num;
	}
	public void setCb_num(int cb_num) {
		this.cb_num = cb_num;
	}
	
	public String getCb_contents() {
		return cb_contents;
	}
	public void setCb_contents(String cb_contents) {
		this.cb_contents = cb_contents;
	}
	public Timestamp getCb_date() {
		return cb_date;
	}
	public void setCb_date(Timestamp cb_date) {
		this.cb_date = cb_date;
	}
	public int getCb_filecode() {
		return cb_filecode;
	}
	public void setCb_filecode(int cb_filecode) {
		this.cb_filecode = cb_filecode;
	}
	public int getCb_cjcode() {
		return cb_cjcode;
	}
	public void setCb_cjcode(int cb_cjcode) {
		this.cb_cjcode = cb_cjcode;
	}
	
	public String getCb_writer_name() {
		return cb_writer_name;
	}
	public void setCb_writer_name(String cb_writer_name) {
		this.cb_writer_name = cb_writer_name;
	}
	public int getCb_writer() {
		return cb_writer;
	}
	public void setCb_writer(int cb_writer) {
		this.cb_writer = cb_writer;
	}
	
}
