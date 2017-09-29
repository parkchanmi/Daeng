package action.member.login;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import action.CommandAction;
import model.member.MemberDao;
import model.dto.MemberDataBean;

public class LoginProAction implements CommandAction {

    public String requestPro(HttpServletRequest request,
        HttpServletResponse response)throws Throwable {
    	//파라미터처리
    	String email = request.getParameter("mem_email");
    	String pw=request.getParameter("mem_pw");
    	
    	///DB처리
    	MemberDao dbPro = MemberDao.getInstance();// DB처리
    	MemberDataBean memberDTO=dbPro.selectMember(email,pw); //정보있으면 이메일 / 없으면null
    	//String member="admin@admin.com";
    	
		//request.setAttribute("member", member);//(속성이름,원하는값)
		//request.setAttribute("memberDTO", memberDTO);
		HttpSession session = request.getSession();
		session.setAttribute("memberDTO", memberDTO);


        return "/view/member/login/member_loginPro.jsp";
    }
}