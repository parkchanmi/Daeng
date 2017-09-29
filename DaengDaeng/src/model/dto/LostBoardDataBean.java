package model.dto;

import java.sql.Timestamp;

public class LostBoardDataBean {
	private int num;
	private int lost_code;
	private int lost_writer;
	private int lost_filecode;
	private String lost_type;
	private String lost_loc1;
	private String lost_loc2;
	private String lost_contents;
	private Timestamp lost_date;
	private String lost_writer_name;
	private String lost_filepath;
	
	public int getNum() {
		return lost_code;
	}

	public void setNum(int lost_code) {
		this.lost_code = lost_code;
	}

	public int getLost_code() {
		return lost_code;
	}

	public void setLost_code(int lost_code) {
		this.lost_code = lost_code;
	}

	public int getLost_writer() {
		return lost_writer;
	}

	public void setLost_writer(int lost_writer) {
		this.lost_writer = lost_writer;
	}

	public int getLost_filecode() {
		return lost_filecode;
	}

	public void setLost_filecode(int lost_filecode) {
		this.lost_filecode = lost_filecode;
	}

	public String getLost_type() {
		return lost_type;
	}

	public void setLost_type(String lost_type) {
		this.lost_type = lost_type;
	}

	public String getLost_loc1() {
		return lost_loc1;
	}

	public void setLost_loc1(String lost_loc1) {
		this.lost_loc1 = lost_loc1;
	}

	public String getLost_loc2() {
		return lost_loc2;
	}

	public void setLost_loc2(String lost_loc2) {
		this.lost_loc2 = lost_loc2;
	}

	public String getLost_contents() {
		return lost_contents;
	}

	public void setLost_contents(String lost_contents) {
		this.lost_contents = lost_contents;
	}

	public Timestamp getLost_date() {
		return lost_date;
	}

	public void setLost_date(Timestamp lost_date) {
		this.lost_date = lost_date;
	}

	public String getLost_writer_name() {
		return lost_writer_name;
	}

	public void setLost_writer_name(String lost_writer_name) {
		this.lost_writer_name = lost_writer_name;
	}

	public String getLost_filepath() {
		return lost_filepath;
	}

	public void setLost_filepath(String lost_filepath) {
		this.lost_filepath = lost_filepath;
	}

}
