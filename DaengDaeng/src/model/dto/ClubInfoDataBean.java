package model.dto;

import java.sql.Timestamp;

public class ClubInfoDataBean {
	
	private int ci_code;
	private int ci_leader;
	private int ci_file_code;
	private String ci_type;
	private String ci_name;
	private String ci_intro;
	private Timestamp ci_date;
	private String ci_filepath;
	private String leader_name;
	
	
	public int getCi_code() {
		return ci_code;
	}
	public void setCi_code(int ci_code) {
		this.ci_code = ci_code;
	}
	public int getCi_leader() {
		return ci_leader;
	}
	public void setCi_leader(int ci_leader) {
		this.ci_leader = ci_leader;
	}
	public int getCi_file_code() {
		return ci_file_code;
	}
	public void setCi_file_code(int ci_file_code) {
		this.ci_file_code = ci_file_code;
	}
	public String getCi_type() {
		return ci_type;
	}
	public void setCi_type(String ci_type) {
		this.ci_type = ci_type;
	}
	public String getCi_name() {
		return ci_name;
	}
	public void setCi_name(String ci_name) {
		this.ci_name = ci_name;
	}
	public String getCi_intro() {
		return ci_intro;
	}
	public void setCi_intro(String ci_intro) {
		this.ci_intro = ci_intro;
	}
	public Timestamp getCi_date() {
		return ci_date;
	}
	public void setCi_date(Timestamp ci_date) {
		this.ci_date = ci_date;
	}
	public String getCi_filepath() {
		return ci_filepath;
	}
	public void setCi_filepath(String ci_filepath) {
		this.ci_filepath = ci_filepath;
	}
	public String getLeader_name() {
		return leader_name;
	}
	public void setLeader_name(String leader_name) {
		this.leader_name = leader_name;
	}
   

}
