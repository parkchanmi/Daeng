package action.member.login;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import action.CommandAction;

public class FindemailFormAction implements CommandAction {//글목록 처리

    public String requestPro(HttpServletRequest request,
        HttpServletResponse response)throws Throwable {
    	request.setCharacterEncoding("UTF-8");
    	//파라미터처리
    	String name = request.getParameter("mem_name");
    	String hp = request.getParameter("mem_hp");
    	
    	
        return "/view/member/login/member_findemail.jsp";
    }
}