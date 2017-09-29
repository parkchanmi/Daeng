package model.dto;

import java.sql.Timestamp;

public class ReportDataBean {
	private int repk_num;
	private int repk_memcode;
	private String repk_typecode;
	private int repk_typenum;
	private String repk_why;
	private Timestamp repk_date;
	
	public int getRepk_num() {
		return repk_num;
	}
	public void setRepk_num(int repk_num) {
		this.repk_num = repk_num;
	}
	public int getRepk_memcode() {
		return repk_memcode;
	}
	public void setRepk_memcode(int repk_memcode) {
		this.repk_memcode = repk_memcode;
	}
	public String getRepk_typecode() {
		return repk_typecode;
	}
	public void setRepk_typecode(String repk_typecode) {
		this.repk_typecode = repk_typecode;
	}
	public int getRepk_typenum() {
		return repk_typenum;
	}
	public void setRepk_typenum(int repk_typenum) {
		this.repk_typenum = repk_typenum;
	}
	public String getRepk_why() {
		return repk_why;
	}
	public void setRepk_why(String repk_why) {
		this.repk_why = repk_why;
	}
	public Timestamp getRepk_date() {
		return repk_date;
	}
	public void setRepk_date(Timestamp repk_date) {
		this.repk_date = repk_date;
	}
	

}
