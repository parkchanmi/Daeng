package action.member.join;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import action.CommandAction;

public class confirmemailAction implements CommandAction{
	public String requestPro(HttpServletRequest request,
	        HttpServletResponse response)throws Throwable {
			
	        return "/view/member/join/confirmmem_email.jsp";
	    }

}
