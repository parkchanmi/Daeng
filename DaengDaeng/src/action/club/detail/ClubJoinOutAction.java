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

public class ClubJoinOutAction implements CommandAction {//글목록 처리

    public String requestPro(HttpServletRequest request,
        HttpServletResponse response)throws Throwable {
    	
    	request.setCharacterEncoding("UTF-8");
    	HttpSession session = request.getSession();
		MemberDataBean mdata = (MemberDataBean) session.getAttribute("memberDTO");
		if(mdata==null){
			return "/view/member/login/member_loginForm.jsp";
		}
    	ClubJoinDataBean cdata =(ClubJoinDataBean) session.getAttribute("joinData");
    	int cjcode = cdata.getCj_code();//가입코드
    	
    	ClubDao cdb = ClubDao.getInstance();
    	int result = cdb.joinOutClub(cjcode);
    	if(result==1){
    		session.removeAttribute("joinData");
    	}
    	request.setAttribute("result", result);
        return "/view/club/detail/club_joinOut.jsp";
    }
}
