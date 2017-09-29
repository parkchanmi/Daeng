package action.admin.member;




import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import action.CommandAction;
import model.admin.AdminDao;
import model.dto.MemberDataBean;



public class MemberUpdateProAction implements CommandAction {//글목록 처리
    public String requestPro(HttpServletRequest request,
        HttpServletResponse response)throws Throwable {
    	request.setCharacterEncoding("UTF-8");
    	

		HttpSession session = request.getSession();
		MemberDataBean mdb = (MemberDataBean) session.getAttribute("memberDTO");
		/*String memcode = request.getParameter("mem_code");
		int memcode2 = Integer.parseInt(memcode);*/
		
		
    	String memcode=request.getParameter("mem_code");
    	if (memcode == null) {
    		memcode = "0";
		}
		int memcode02 = Integer.parseInt(memcode);
    	
    	String black=request.getParameter("mem_black");
    	String type = request.getParameter("mem_type");
    	String job = request.getParameter("mem_job");
    	
    	System.out.println("멤코드"+memcode02);
    	System.out.println("mem_black:"+black);
    	System.out.println("mem_type:"+type);
    	System.out.println("mem_job:"+job);
    	
    	/*AdminDao aPro = AdminDao.getInstance();
    		aPro.updateMember(memcode,black,type,job);
    	*/
    		
    	
    		AdminDao aPro = AdminDao.getInstance();// DB처리
    		int result = aPro.updateMember(memcode02,black,type,job); 
    		request.setAttribute("result",result);
    		
    		
    		System.out.println("mem_black"+black);
        	
        
    	
    	
        return "/view/admin/member/admin_memberupdatePro.jsp";
    }
}
