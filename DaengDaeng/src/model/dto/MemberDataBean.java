package model.dto;

import java.sql.Timestamp;

public class MemberDataBean {
		
	    private int mem_code;
	    private String mem_type;
	    private String mem_email;
	    private String mem_pw;
	    private String mem_name;
	    private String mem_gender;
	    private String mem_age;
	    private String mem_hp;
	    private String mem_visit;
	    private Timestamp mem_regdate;
	    private String mem_black;
	    private int mem_dog_ok;
	    private String mem_loc;
	    private String mem_job;
	    
		public int getMem_code() {
			return mem_code;
		}
		public void setMem_code(int mem_code) {
			this.mem_code = mem_code;
		}
		public String getMem_type() {
			return mem_type;
		}
		public void setMem_type(String mem_type) {
			this.mem_type = mem_type;
		}
		public String getMem_email() {
			return mem_email;
		}
		public void setMem_email(String mem_email) {
			this.mem_email = mem_email;
		}
		public String getMem_pw() {
			return mem_pw;
		}
		public void setMem_pw(String mem_pw) {
			this.mem_pw = mem_pw;
		}
		public String getMem_name() {
			return mem_name;
		}
		public void setMem_name(String mem_name) {
			this.mem_name = mem_name;
		}
		public String getMem_gender() {
			return mem_gender;
		}
		public void setMem_gender(String mem_gender) {
			this.mem_gender = mem_gender;
		}
		public String getMem_age() {
			return mem_age;
		}
		public void setMem_age(String mem_age) {
			this.mem_age = mem_age;
		}
		public String getMem_hp() {
			return mem_hp;
		}
		public void setMem_hp(String mem_hp) {
			this.mem_hp = mem_hp;
		}
		public String getMem_visit() {
			return mem_visit;
		}
		public void setMem_visit(String mem_visit) {
			this.mem_visit = mem_visit;
		}
		public Timestamp getMem_regdate() {
			return mem_regdate;
		}
		public void setMem_regdate(Timestamp mem_regdate) {
			this.mem_regdate = mem_regdate;
		}
		public String getMem_black() {
			return mem_black;
		}
		public void setMem_black(String mem_black) {
			this.mem_black = mem_black;
		}
		public int getMem_dog_ok() {
			return mem_dog_ok;
		}
		public void setMem_dog_ok(int mem_dog_ok) {
			this.mem_dog_ok = mem_dog_ok;
		}
		public String getMem_loc() {
			return mem_loc;
		}
		public void setMem_loc(String mem_loc) {
			this.mem_loc = mem_loc;
		}
		public String getMem_job() {
			return mem_job;
		}
		public void setMem_job(String mem_job) {
			this.mem_job = mem_job;
		}
}

