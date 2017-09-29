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

public class ClubJoinAction implements CommandAction {//글목록 처리

    public String requestPro(HttpServletRequest request,
        HttpServletResponse response)throws Throwable {
    	
    	request.setCharacterEncoding("UTF-8");
    	HttpSession session = request.getSession();
		MemberDataBean mdata = (MemberDataBean) session.getAttribute("memberDTO");
		if(mdata==null){
			return "/view/member/login/member_loginForm.jsp";
		}
    	ClubInfoDataBean cdata =(ClubInfoDataBean) session.getAttribute("clubinfo");
    	int cicode = cdata.getCi_code();//소모임코드
    	
    	int memcode = mdata.getMem_code();//회원코드
    	
    	ClubDao cdb = ClubDao.getInstance();
    	int result = cdb.joinClub(cicode,memcode);
    	
    	
    	ClubJoinDataBean joinData = cdb.IsMember(memcode, cicode);
    	session.setAttribute("joinData", joinData);
    	
    	request.setAttribute("result", result);
        return "/view/club/detail/club_joinPro.jsp";
    }
}
