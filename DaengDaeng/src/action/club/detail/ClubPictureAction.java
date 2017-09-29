package action.club.detail;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import action.CommandAction;
import model.club.ClubDao;
import model.dto.ClubInfoDataBean;

public class ClubPictureAction implements CommandAction {//글목록 처리

    public String requestPro(HttpServletRequest request,
        HttpServletResponse response)throws Throwable {
    	
        return "/view/club/detail/club_picture_list.jsp";
    }
}
