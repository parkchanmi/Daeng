package action.board.lost.owner;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import action.CommandAction;
import model.dto.MemberDataBean;

public class OwnerWriteFormAction implements CommandAction {
	
	public String requestPro(HttpServletRequest request, HttpServletResponse response) throws Throwable {
		HttpSession session = request.getSession();
		MemberDataBean mdata = (MemberDataBean) session.getAttribute("memberDTO");
		if(mdata==null){
			return "/view/member/login/member_loginForm.jsp";
		}
		int num=0;
		
		try{
			if(request.getParameter("num") != null) {
				num = Integer.parseInt(request.getParameter("num"));
				
				request.setAttribute("num", num);				
			}
		}catch (Exception e) {
			e.printStackTrace();
		}
		
		return "/view/board/lost/owner/owner_writeForm.jsp";
	}

}
