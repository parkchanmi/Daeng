package action.club.detail;

import java.util.Calendar;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import action.CommandAction;
import model.club.ClubDao;
import model.dto.ClubInfoDataBean;
import model.dto.MemberDataBean;

public class ClubScheduleAction implements CommandAction {//글목록 처리

    public String requestPro(HttpServletRequest request,
        HttpServletResponse response)throws Throwable {
    	//필요한 파라미터 가지고 이동!
    	HttpSession session = request.getSession();
		MemberDataBean mdata = (MemberDataBean) session.getAttribute("memberDTO");
		if(mdata==null){
			return "/view/member/login/member_loginForm.jsp";
		}
    	session.setAttribute("sch_kind", "club");
    	
        return "/view/club/detail/View.jsp";
    }
}
