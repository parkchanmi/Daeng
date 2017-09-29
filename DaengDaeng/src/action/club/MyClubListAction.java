package action.club;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import action.CommandAction;
import model.club.ClubDao;
import model.dto.ClubInfoDataBean;
import model.dto.MemberDataBean;

public class MyClubListAction implements CommandAction {//글목록 처리

    public String requestPro(HttpServletRequest request,
        HttpServletResponse response)throws Throwable {
    	
    	HttpSession session = request.getSession();
		MemberDataBean mdata = (MemberDataBean) session.getAttribute("memberDTO");
		if(mdata==null){
			return "/view/member/login/member_loginForm.jsp";
		}
    	int memcode = mdata.getMem_code();
    	
    	ClubDao cdb = ClubDao.getInstance();
    	ArrayList<ClubInfoDataBean> cList = cdb.getMy_Club(memcode);
    	
    	request.setAttribute("cList", cList);
    	//세션제거
    	session.removeAttribute("clubinfo");
    	session.removeAttribute("leader");
    	session.removeAttribute("joinData");
        return "/view/club/myclub_list.jsp";
    }
}
