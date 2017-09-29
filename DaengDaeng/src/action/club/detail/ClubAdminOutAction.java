package action.club.detail;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import action.CommandAction;
import model.club.ClubDao;
import model.dto.ClubInfoDataBean;
import model.dto.ClubJoinDataBean;
import model.dto.MemberDataBean;

public class ClubAdminOutAction implements CommandAction {//글목록 처리

    public String requestPro(HttpServletRequest request,
        HttpServletResponse response)throws Throwable {
    	
    	request.setCharacterEncoding("UTF-8");
    	
    	String mem = request.getParameter("memcode");
    	HttpSession session = request.getSession();
		MemberDataBean mdata = (MemberDataBean) session.getAttribute("memberDTO");
		if(mdata==null){
			return "/view/member/login/member_loginForm.jsp";
		}
    	ClubInfoDataBean cdata=(ClubInfoDataBean) session.getAttribute("clubinfo");//세션에서 가져옴
    	int cicode=cdata.getCi_code();
    	int memcode = Integer.parseInt(mem);
    	ClubDao cdb = ClubDao.getInstance();
    	int result = cdb.joinOutClub(memcode,cicode);
    	
    	request.setAttribute("result", result);
        return "/view/club/detail/club_adminOut.jsp";
    }
}
