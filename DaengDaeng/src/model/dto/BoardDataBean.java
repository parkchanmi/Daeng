package model.dto;

import java.sql.Timestamp;

public class BoardDataBean {
	private int num;
	private int board_num;
	private int board_writer;
	private int board_filecode;
	private String board_title;
	private Timestamp board_date;
	private String board_type;
	private int board_rdcount;
	private String board_contents;
	private int board_ref;
	private int board_step;
	private int board_level;
	private String board_writer_name;
	
	public int getBoard_num() {
		return board_num;
	}
	public void setBoard_num(int board_num) {
		this.board_num = board_num;
	}
	public int getBoard_writer() {
		return board_writer;
	}
	public void setBoard_writer(int board_writer) {
		this.board_writer = board_writer;
	}
	public int getBoard_filecode() {
		return board_filecode;
	}
	public void setBoard_filecode(int board_filecode) {
		this.board_filecode = board_filecode;
	}
	public String getBoard_title() {
		return board_title;
	}
	public void setBoard_title(String board_title) {
		this.board_title = board_title;
	}
	public Timestamp getBoard_date() {
		return board_date;
	}
	public void setBoard_date(Timestamp board_date) {
		this.board_date = board_date;
	}
	public String getBoard_type() {
		return board_type;
	}
	public void setBoard_type(String board_type) {
		this.board_type = board_type;
	}
	public int getBoard_rdcount() {
		return board_rdcount;
	}
	public void setBoard_rdcount(int board_rdcount) {
		this.board_rdcount = board_rdcount;
	}
	public String getBoard_contents() {
		return board_contents;
	}
	public void setBoard_contents(String board_contents) {
		this.board_contents = board_contents;
	}
	public int getBoard_ref() {
		return board_ref;
	}
	public void setBoard_ref(int board_ref) {
		this.board_ref = board_ref;
	}
	public int getBoard_step() {
		return board_step;
	}
	public void setBoard_step(int board_step) {
		this.board_step = board_step;
	}
	public int getBoard_level() {
		return board_level;
	}
	public void setBoard_level(int board_level) {
		this.board_level = board_level;
	}
	public String getBoard_writer_name() {
		return board_writer_name;
	}
	public void setBoard_writer_name(String board_writer_name) {
		this.board_writer_name = board_writer_name;
	}
	public int getNum() {
		return num;
	}
	public void setNum(int num) {
		this.num = num;
	}
	

}
