package action.club.detail;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import action.CommandAction;
import model.club.ClubDao;
import model.dto.ClubInfoDataBean;
import model.dto.MemberDataBean;

public class ClubMemberAction implements CommandAction {//글목록 처리

    public String requestPro(HttpServletRequest request,
        HttpServletResponse response)throws Throwable {
    	
    	request.setCharacterEncoding("UTF-8");
    	HttpSession session = request.getSession();
		MemberDataBean mdata = (MemberDataBean) session.getAttribute("memberDTO");
		if(mdata==null){
			return "/view/member/login/member_loginForm.jsp";
		}
    	ClubInfoDataBean cdata =(ClubInfoDataBean) session.getAttribute("clubinfo");
    	int cicode = cdata.getCi_code();
    	
    	
    	
    	ClubDao cdb = ClubDao.getInstance();
    	ArrayList<MemberDataBean> mList = cdb.getClub_member(cicode);
    	
    	request.setAttribute("memList", mList);
        return "/view/club/detail/club_member_list.jsp";
    }
}
