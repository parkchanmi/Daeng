package model.dto;

import java.sql.Timestamp;

public class ClubJoinDataBean {
	private int cj_code;
	private int cj_memcode;
	private int cj_cicode;
	private Timestamp cj_date;
	
	
	public int getCj_code() {
		return cj_code;
	}
	public void setCj_code(int cj_code) {
		this.cj_code = cj_code;
	}
	public int getCj_memcode() {
		return cj_memcode;
	}
	public void setCj_memcode(int cj_memcode) {
		this.cj_memcode = cj_memcode;
	}
	public int getCj_cicode() {
		return cj_cicode;
	}
	public void setCj_cicode(int cj_cicode) {
		this.cj_cicode = cj_cicode;
	}
	
	public Timestamp getCj_date() {
		return cj_date;
	}
	public void setCj_date(Timestamp cj_date) {
		this.cj_date = cj_date;
	}
    
	
}
