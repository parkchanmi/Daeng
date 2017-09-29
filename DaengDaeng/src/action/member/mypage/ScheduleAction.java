package action.member.mypage;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import action.CommandAction;
import model.dto.MemberDataBean;
import model.member.MemberDao;

public class ScheduleAction implements CommandAction {//글목록 처리
	
    public String requestPro(HttpServletRequest request,
    		
        HttpServletResponse response)throws Throwable {
    	//파라미터처리  	
    	request.setCharacterEncoding("UTF-8");

    	
    	HttpSession session = request.getSession();
		MemberDataBean mdb = (MemberDataBean) session.getAttribute("memberDTO");
		session.setAttribute("sch_kind", "member");
    	
        return "/view/schedule/View.jsp";
    }
}
