package action.member.mypage;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import action.CommandAction;
import model.dto.MemberDataBean;
import model.member.MemberDao;

public class MpInfo_UpdateProAction implements CommandAction {//글목록 처리
	
    public String requestPro(HttpServletRequest request,
    		
        HttpServletResponse response)throws Throwable {
    	//파라미터처리  	
    	request.setCharacterEncoding("UTF-8");

    	
    	HttpSession session = request.getSession();
		MemberDataBean mdb = (MemberDataBean) session.getAttribute("memberDTO");
		int memcode=mdb.getMem_code();
		String mem_type=request.getParameter("mem_type");
    	String pw=request.getParameter("mem_pw");
    	String hp = request.getParameter("mem_hp");
    	String loc = request.getParameter("mem_loc");
    	
        	
    	MemberDao dbPro = MemberDao.getInstance();// DB처리
    	int result = dbPro.updateMember(pw,hp,loc,mem_type, memcode); 
    	request.setAttribute("result",result);
    	
    	MemberDataBean mdb_modify = dbPro.getMember(mdb.getMem_code());
    	session.setAttribute("memberDTO", mdb_modify);
    	
    	
        return "/view/member/mypage/myPagePro.jsp";
    }
}
