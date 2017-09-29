package action.member.login;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import action.CommandAction;
import model.dto.MemberDataBean;
import model.member.MemberDao;

public class ChangepwProAction implements CommandAction {//글목록 처리

    public String requestPro(HttpServletRequest request,
        HttpServletResponse response)throws Throwable {
    	request.setCharacterEncoding("UTF-8");
    	//파라미터처리
    	String hp1=request.getParameter("hp1");
    	String hp2=request.getParameter("hp2");
    	String hp3=request.getParameter("hp3");
    	String hp = hp1+hp2+hp3;
   
    	String email = request.getParameter("mem_email");
    	String name = request.getParameter("mem_name");
    	
    	
    	
    	//DB처리
    	MemberDao dbPro = MemberDao.getInstance();// DB처리
    	int mem_code =dbPro.selectpw(email,name,hp);
    	
    	
    	
    	request.setAttribute("memberCode", mem_code);
        return "/view/member/login/member_changepwPro.jsp";
    }
}