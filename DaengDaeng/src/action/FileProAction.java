package action;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

import action.CommandAction;
import model.dto.FileInfoDataBean;

public class FileProAction implements CommandAction {//글목록 처리

    public String requestPro(HttpServletRequest request,
        HttpServletResponse response)throws Throwable {
    	//String savePath = request.getServletContext().getRealPath("upload");
    	String savePath="d://upload";
    	System.out.println(savePath);
    	int sizeLimit = 1024*1024*15;
    	FileInfoDataBean fdata = new FileInfoDataBean();//파일정보 저장용
    	MultipartRequest multi = new MultipartRequest(request, savePath, sizeLimit, "utf-8", new DefaultFileRenamePolicy()); //저장
    	String type = multi.getParameter("ci_type");
    	String name = multi.getParameter("ci_name");
    	String info = multi.getParameter("ci_intro");
    	// 전송받은 데이터가 파일일 경우 getFilesystemName()으로 파일 이름을 받아올 수 있다.
    	String fileName = multi.getFilesystemName("ci_filecode");
    	
    	// 업로드한 파일의 전체 경로를 DB에 저장하기 위함
    	String m_fileFullPath = savePath + "/" + fileName;
    	 
    	 
    	// 데이터들을 담을 그릇인 DTO(혹은 Bean) 객체를 생성 후, 데이터들을 set해준다.
    	//MemberDTO memberDTO = new MemberDTO();
    	 
    	fdata.setFile_name(fileName);
    	fdata.setFile_path(m_fileFullPath);
    	 
    	// Service 객체 생성.(서비스가 없고 DAO에서 직접 처리한다면 DAO 객체 생성)
    	//MemberService service = MemberService.getInstance();
    	 
    	// 서비스에서 만들어놓은 insert 수행 메서드 사용. set으로 담아줬던 DTO를 넘겨서 insert 수행.
    //	service.insertMember(memberDTO);
    	 
    	// 검증 처리는 생략했다.
    	// 검증은 Controller 혹은 Action에서 해도 되고, 입력 폼이 있는 jsp에서 해도 된다. 둘다 해도 되고..
    	// 그런 건 각자 알아서.
    	 
    	// 만약 return할 페이지에 방금 전송한 데이터들을 출력하고 싶다면 DTO를 속성에 담아준다.
    	request.setAttribute("filename", fileName);
    	 request.setAttribute("filepath", m_fileFullPath);
    	// ↓ 모든 것이 성공적으로 수행되었을 경우 return 될 page
    	return "filepro.jsp";
    }
}
