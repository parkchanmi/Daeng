package model.dto;

import java.sql.Timestamp;

public class ScheduleDataBean {
	
	private int sch_code;
	private String sch_title; 
	private String sch_contents;
	private Timestamp sch_sdate;
	private Timestamp sch_edate;
	
	public int getSch_code() {
		return sch_code;
	}
	public void setSch_code(int sch_code) {
		this.sch_code = sch_code;
	}
	public String getSch_title() {
		return sch_title;
	}
	public void setSch_title(String sch_title) {
		this.sch_title = sch_title;
	}
	public String getSch_contents() {
		return sch_contents;
	}
	public void setSch_contents(String sch_contents) {
		this.sch_contents = sch_contents;
	}
	public Timestamp getSch_sdate() {
		return sch_sdate;
	}
	public void setSch_sdate(Timestamp sch_sdate) {
		this.sch_sdate = sch_sdate;
	}
	public Timestamp getSch_edate() {
		return sch_edate;
	}
	public void setSch_edate(Timestamp sch_edate) {
		this.sch_edate = sch_edate;
	}
	
	

}
