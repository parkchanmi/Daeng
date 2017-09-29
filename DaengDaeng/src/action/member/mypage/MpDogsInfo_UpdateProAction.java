package action.member.mypage;

import java.io.File;
import java.util.ArrayList;
import java.util.Enumeration;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

import action.CommandAction;
import model.dog.DogDao;
import model.dto.DogDataBean;
import model.dto.FileInfoDataBean;
import model.dto.MemberDataBean;
import model.file.FileDao;
import model.member.MemberDao;

public class MpDogsInfo_UpdateProAction implements CommandAction  {
	public String requestPro(HttpServletRequest request, HttpServletResponse response) throws Throwable {
		// 파라미터처리
		
		request.setCharacterEncoding("UTF-8");
		
		String savePath="D:/pcm/workspace/daengdaeng/WebContent/upload/";
    	String dbPath="http://localhost:8081/DaengDaeng/upload/";
    	HttpSession session = request.getSession();
		MemberDataBean mdata = (MemberDataBean) session.getAttribute("memberDTO");
		if(mdata==null){
			return "/view/member/login/member_loginForm.jsp";
		}
    	 MultipartRequest multi = null;
         boolean sizeError=false;
         int fileMaxSize = 10*1024*1024; // 파일최대사이즈 10MB로 지정
         String fileName = ""; // 파일명
     	int fileSize =  0;
     	String fileType="";
         try{
             multi = new MultipartRequest(request, savePath, fileMaxSize, "UTF-8", new DefaultFileRenamePolicy());//파일저장 
                                     // request : request 객체
                                     // savePath : 저장될 서버 경로
                                     // fileMaxSize : 파일 최대 크기
                                     // "UTF-8" : 인코딩 방식
                                     // new DefaultFileRenamePolicy() : 같은 이름의 파일명 방지 처리
             Enumeration files = multi.getFileNames();
     		String file = (String)files.nextElement(); 
     		File fileObj = multi.getFile(file);   //파일객체
     		
     		
     		fileName = multi.getFilesystemName(file);
     		fileSize = (int)fileObj.length(); 
     		fileType = multi.getContentType(file); 
     		System.out.print(fileName+' ');
     		
         }catch (Exception e){
             if(e.getMessage().indexOf("exceeds limit") > -1) { //파일사이즈 초과된 경우
                 sizeError = true;
             }
             //e.printStackTrace();
         }
         if(sizeError){
             response.setContentType("text/html; charset=UTF-8");
             response.setCharacterEncoding("UTF-8");
             response.getWriter().write("<script>alert(''); location.href=''; </script>");
             return "errorpage.jsp";
         }
  
 //MultipartRequest에서 제공해주는 getParameter함수를 사용해서
 //input값을 가져옴, string으로 넘어오는 값들
         
         // 글 쓰기
		
		
		
		
		
		
		
		
		
		
		
		
		
		String dog_name = multi.getParameter("dog_name");
		String dog_kind = multi.getParameter("dog_kind");
		String dog_gender = multi.getParameter("dog_gender");
		String dog_age = multi.getParameter("dog_age");
		String dog_code = multi.getParameter("dog_code");
		
		  // 글 쓰기
        FileInfoDataBean fdata = new FileInfoDataBean();
 		fdata.setFile_name(fileName);
 		fdata.setFile_type(fileType);
 		fdata.setFile_size(fileSize);
 		
 		fdata.setFile_path(dbPath+fileName);
 		
 		FileDao fdao=FileDao.getInstance();
 		int filecode=fdao.insertFile(fdata); //파일저장
 		
    	
    	if (dog_code == null) {
    		dog_code = "0";
    		}
    	int dog_code02 = Integer.parseInt(dog_code);
    	
    	DogDao ddb = DogDao.getInstance();
    	
    	int result = ddb.updateDog(dog_name,dog_kind,dog_gender,dog_age,filecode,dog_code02);
 
    	request.setAttribute("result",result);
    	

		return "/view/member/mypage/myPagePro.jsp";
	}
}
