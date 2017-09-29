<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@page import="org.json.simple.JSONObject"%>
<%@page import="com.oreilly.servlet.multipart.DefaultFileRenamePolicy"%>
<%@page import="java.util.Enumeration"%>
<%@page import="com.oreilly.servlet.MultipartRequest"%>
<%@ page import="java.io.File"%>
<%@ page import="model.file.FileDao"%>
<%@ page import="model.dto.FileInfoDataBean"%>
<%
    // 이미지 업로드할 경로
   String uploadPath =  "D:/pcm/workspace/daengdaeng/WebContent/upload/";
	//String uploadPath =  "http://localhost:8081/DaengDaeng/upload/";
    System.out.println(uploadPath);
	//String uploadPath = "D://upload";
    int size = 10 * 1024 * 1024;  // 업로드 사이즈 제한 10M 이하
	
	String fileName = ""; // 파일명
	int fileSize =  0;
	String fileType="";
	try{
        // 파일업로드 및 업로드 후 파일명 가져옴
		MultipartRequest multi = new MultipartRequest(request, uploadPath, size, "utf-8", new DefaultFileRenamePolicy());
		Enumeration files = multi.getFileNames();
		String file = (String)files.nextElement(); 
		File fileObj = multi.getFile(file);   //파일객체
		
		
		fileName = multi.getFilesystemName(file);
		fileSize = (int)fileObj.length(); 
		fileType = multi.getContentType(file);                   //콘텐트타입    
       //	String originFileName = multi.getOriginalFileName(file);           //초기 파일명
       // String fileExtend = fileName.substring(fileName.lastIndexOf(".")+1); //파일 확장자
      
		System.out.println(fileName+fileType+fileSize);
	}catch(Exception e){
		e.printStackTrace();
	}
	
    // 업로드된 경로와 파일명을 통해 이미지의 경로를 생성
	uploadPath = "http://localhost:8081/DaengDaeng/upload/"+fileName;
	System.out.println(uploadPath);
	
	//DB저장DAO
    FileInfoDataBean fdata = new FileInfoDataBean();
	fdata.setFile_name(fileName);
	fdata.setFile_type(fileType);
	fdata.setFile_size(fileSize);
	fdata.setFile_path(uploadPath);
	
	FileDao fdao=FileDao.getInstance();
	int filecode=fdao.insertFile(fdata);
	
	
	
	// 생성된 경로를 JSON 형식으로 보내주기 위한 설정
	JSONObject jobj = new JSONObject();
	jobj.put("url", uploadPath);
	jobj.put("name", fileName);
	jobj.put("filecode",filecode);
	response.setContentType("application/json"); // 데이터 타입을 json으로 설정하기 위한 세팅
	out.print(jobj.toJSONString());
	%>
	