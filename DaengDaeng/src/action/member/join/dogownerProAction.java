package action.member.join;


import java.sql.Timestamp;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;



import action.CommandAction;
import model.dog.DogDao;
import model.member.MemberDao;
import model.dto.DogDataBean;
import model.dto.MemberDataBean;

public class dogownerProAction implements CommandAction {

    public String requestPro(HttpServletRequest request,
        HttpServletResponse response)throws Throwable {
    	request.setCharacterEncoding("UTF-8");
    	
  
    	String hp1=request.getParameter("hp1");
    	String hp2=request.getParameter("hp2");
    	String hp3=request.getParameter("hp3");
    	String mem_hp = hp1+hp2+hp3;
    	
    	
    	MemberDataBean member = new MemberDataBean();
    	DogDataBean dog = new DogDataBean();
    	
    	
    	
    	
		member.setMem_type("견주");
		member.setMem_email(request.getParameter("mem_email"));
		member.setMem_pw(request.getParameter("mem_pw"));
		member.setMem_name(request.getParameter("mem_name"));
		member.setMem_gender(request.getParameter("mem_gender"));
		member.setMem_age(request.getParameter("mem_age"));
		member.setMem_hp(mem_hp);
		member.setMem_visit(request.getParameter("mem_visit"));
		member.setMem_regdate(new Timestamp(System.currentTimeMillis()) );//가입시간 저장
		member.setMem_black("정상");
    	member.setMem_dog_ok(1);
    	member.setMem_loc(request.getParameter("mem_loc"));
    	member.setMem_job(request.getParameter("mem_job"));
        
    	
        dog.setFile_code(0);
    	dog.setDog_name(request.getParameter("dog_name"));
    	dog.setDog_age(request.getParameter("dog_age"));
    	dog.setDog_gender(request.getParameter("dog_gender"));
    	dog.setDog_kind(request.getParameter("dog_kind"));
    	
    
    	
    	 MemberDao manager = MemberDao.getInstance();
    	 int result=manager.insertMember(member);
    	 if(result==1){
    		 //회원이메일로 코드찾는 메서드
    		 int memcode=manager.getMemcode(request.getParameter("mem_email"));
    		 DogDao Dog = DogDao.getInstance();
        	 Dog.insertDog(dog,memcode);
    	 }
    	 request.setAttribute("result", result);
        return "/view/member/join/member_normal_pro.jsp";
    }
}