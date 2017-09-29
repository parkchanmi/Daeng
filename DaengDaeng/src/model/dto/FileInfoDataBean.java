package model.dto;

public class FileInfoDataBean {
	
	private int file_code;
	private String file_name;
	private String file_type;
	private int file_size;
    private String file_path;
    
	public int getFile_code() {
		return file_code;
	}
	public void setFile_code(int file_code) {
		this.file_code = file_code;
	}
	public String getFile_name() {
		return file_name;
	}
	public void setFile_name(String file_name) {
		this.file_name = file_name;
	}
	public String getFile_type() {
		return file_type;
	}
	public void setFile_type(String file_type) {
		this.file_type = file_type;
	}
	public int getFile_size() {
		return file_size;
	}
	public void setFile_size(int file_size) {
		this.file_size = file_size;
	}
	
	public String getFile_path() {
		return file_path;
	}
	public void setFile_path(String file_path) {
		this.file_path = file_path;
	}
	
	 
}
