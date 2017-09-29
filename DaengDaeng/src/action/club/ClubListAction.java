package action.club;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import action.CommandAction;
import model.club.ClubDao;
import model.dto.ClubInfoDataBean;

public class ClubListAction implements CommandAction {//글목록 처리

    public String requestPro(HttpServletRequest request,
        HttpServletResponse response)throws Throwable {
    	ClubDao cdb = ClubDao.getInstance();
    	ArrayList<ClubInfoDataBean> cList = cdb.getAll_Club();
    	//세션제거
    	HttpSession session = request.getSession();
    	session.removeAttribute("clubinfo");
    	session.removeAttribute("leader");
    	session.removeAttribute("joinData");
    	
    	request.setAttribute("cList", cList);
        return "/view/club/club_list.jsp";
    }
}
