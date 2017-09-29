package model.dto;

import java.sql.Timestamp;

public class VisitStatDataBean {
	
	private Timestamp vs_date;
	private int vd_count;
	
	public Timestamp getVs_date() {
		return vs_date;
	}
	public void setVs_date(Timestamp vs_date) {
		this.vs_date = vs_date;
	}
	public int getVd_count() {
		return vd_count;
	}
	public void setVd_count(int vd_count) {
		this.vd_count = vd_count;
	}

}
