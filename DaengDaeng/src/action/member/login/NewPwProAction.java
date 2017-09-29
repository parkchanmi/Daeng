package action.member.login;

import javax.servlet.ServletRequest;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import action.CommandAction;
import model.dto.MemberDataBean;
import model.member.MemberDao;

public class NewPwProAction implements CommandAction {//글목록 처리

    public String requestPro(HttpServletRequest request,
        HttpServletResponse response)throws Throwable {
    	request.setCharacterEncoding("UTF-8"); 
        	//파라미터처리
  
		MemberDao dbPro = MemberDao.getInstance();// DB처리
        	
			String password = request.getParameter("mem_pw");
			int memcode = Integer.parseInt(request.getParameter("mem_code"));

			int result =dbPro.newpw(password ,memcode);
        	request.setAttribute("result", result);
            return "/view/member/login/member_newpw_pro.jsp";
        }
}
