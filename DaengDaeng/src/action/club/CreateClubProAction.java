package action.club;

import java.io.File;
import java.sql.Timestamp;
import java.util.Enumeration;
import java.util.Iterator;
import java.util.List;

import javax.servlet.ServletConfig;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

import action.CommandAction;
import file.FileSaveHelper;
import model.club.ClubDao;
import model.dto.ClubInfoDataBean;
import model.dto.FileInfoDataBean;
import model.dto.MemberDataBean;
import model.file.FileDao;

public class CreateClubProAction implements CommandAction {//글목록 처리

    public String requestPro(HttpServletRequest request,
        HttpServletResponse response)throws Throwable {
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
         	
     		int memcode = mdata.getMem_code();
     		String ci_type=multi.getParameter("ci_type");
     		String ci_name=multi.getParameter("ci_name");
     		String ci_intro=multi.getParameter("ci_intro");
     		ci_intro = ci_intro.replace("\r\n","<br>"); //textarea의 ENTER값 처리            
             
     		 FileInfoDataBean fdata = new FileInfoDataBean();
     		fdata.setFile_name(fileName);
     		fdata.setFile_type(fileType);
     		fdata.setFile_size(fileSize);
     		
     		fdata.setFile_path(dbPath+fileName);
     		
     		FileDao fdao=FileDao.getInstance();
     		int filecode=fdao.insertFile(fdata); //파일저장
            //db셋팅
             
     		ClubInfoDataBean cdata= new ClubInfoDataBean();//소모임정보 저장용
        	cdata.setCi_leader(memcode); //세션에서 찾은 로그인회원id
        	cdata.setCi_date(new Timestamp(System.currentTimeMillis()));
        	cdata.setCi_file_code(filecode);
        	cdata.setCi_intro(ci_intro);
        	cdata.setCi_name(ci_name);
        	cdata.setCi_type(ci_type);
        	
        	ClubDao cdo = ClubDao.getInstance();
        	int result = cdo.createClub(cdata);
             //dao실행

     
        	request.setAttribute("result", result);



        	return "/view/club/create_clubPro.jsp";
    }
}
