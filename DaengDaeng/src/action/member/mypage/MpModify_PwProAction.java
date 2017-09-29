package action.member.mypage;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import action.CommandAction;
import model.dto.MemberDataBean;
import model.member.MemberDao;

public class MpModify_PwProAction implements CommandAction {//글목록 처리

    public String requestPro(HttpServletRequest request,
    	HttpServletResponse response)throws Throwable {
    	//파라미터처리
    	String email=request.getParameter("mem_email");
    	 	/*
    	///DB처리
    	MemberDao dbPro = MemberDao.getInstance();// DB처리
    	MemberDataBean memberDTO=dbPro.selectMypagemodify(email); //정보있으면 이메일 / 없으면null
    	//String member="admin@admin.com";
    	*/
		//request.setAttribute("member", member);//(속성이름,원하는값)
    	HttpSession session = request.getSession();
		MemberDataBean mdb = (MemberDataBean) session.getAttribute("memberDTO");
		if(mdb==null){
			return "/view/member/login/member_loginForm.jsp";
		}
		
        return "/view/member/mypage/mp_modifyForm.jsp";
    }
}
