package model.dto;

import java.sql.Timestamp;

public class ReviewDataBean {
	private int rev_num;
	private int rev_memcode;
	private String rev_typecode;
	private int rev_typenum;
	private String rev_contents;
	private Timestamp rev_date;
	private String rev_writer_name;
	
	public int getRev_num() {
		return rev_num;
	}
	public void setRev_num(int rev_num) {
		this.rev_num = rev_num;
	}
	public int getRev_memcode() {
		return rev_memcode;
	}
	public void setRev_memcode(int rev_memcode) {
		this.rev_memcode = rev_memcode;
	}
	public String getRev_typecode() {
		return rev_typecode;
	}
	public void setRev_typecode(String rev_typecode) {
		this.rev_typecode = rev_typecode;
	}
	public int getRev_typenum() {
		return rev_typenum;
	}
	public void setRev_typenum(int rev_typenum) {
		this.rev_typenum = rev_typenum;
	}
	public String getRev_contents() {
		return rev_contents;
	}
	public void setRev_contents(String rev_contents) {
		this.rev_contents = rev_contents;
	}
	public Timestamp getRev_date() {
		return rev_date;
	}
	public void setRev_date(Timestamp rev_date) {
		this.rev_date = rev_date;
	}
	public String getRev_writer_name() {
		return rev_writer_name;
	}
	public void setRev_writer_name(String board_writer_name) {
		this.rev_writer_name = board_writer_name;
	}

}
