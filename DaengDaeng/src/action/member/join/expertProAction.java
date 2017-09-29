package action.member.join;


import java.sql.Timestamp;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;



import action.CommandAction;
import model.member.MemberDao;
import model.dto.MemberDataBean;

public class expertProAction implements CommandAction {

    public String requestPro(HttpServletRequest request,
        HttpServletResponse response)throws Throwable {
    	request.setCharacterEncoding("UTF-8");
    	
  
    	String hp1=request.getParameter("hp1");
    	String hp2=request.getParameter("hp2");
    	String hp3=request.getParameter("hp3");
    	String mem_hp = hp1+hp2+hp3;
    	
    	
    	MemberDataBean member = new MemberDataBean();
		
		member.setMem_type("전문가");
		member.setMem_email(request.getParameter("mem_email"));
		member.setMem_pw(request.getParameter("mem_pw"));
		member.setMem_name(request.getParameter("mem_name"));
		member.setMem_gender(request.getParameter("mem_gender"));
		member.setMem_age(request.getParameter("mem_age"));
		member.setMem_hp(mem_hp);
		member.setMem_visit(request.getParameter("mem_visit"));
		member.setMem_regdate(new Timestamp(System.currentTimeMillis()) );//가입시간 저장
		member.setMem_black("정상");
    	member.setMem_dog_ok(0);
    	member.setMem_loc(request.getParameter("mem_loc"));
    	member.setMem_job(request.getParameter("mem_job"));
    	
    	
    
    	
    	 MemberDao manager = MemberDao.getInstance();
    	 int result = manager.insertMember(member);
    	 request.setAttribute("result", result);
        return "/view/member/join/member_expert_pro.jsp";
    }
}