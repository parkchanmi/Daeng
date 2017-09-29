package action.member.join;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import action.CommandAction;

public class dogownerFormAction implements CommandAction {//글목록 처리

    public String requestPro(HttpServletRequest request,
        HttpServletResponse response)throws Throwable {
    	request.setCharacterEncoding("UTF-8");
    	//파라미터 처리
    	


    	
        return "/view/member/join/member_dogowner.jsp";
    }
}
