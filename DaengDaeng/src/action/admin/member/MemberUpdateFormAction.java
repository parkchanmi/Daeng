package action.admin.member;


import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import action.CommandAction;
import model.admin.AdminDao;
import model.dto.AdGrantDataBean;
import model.dto.DogDataBean;
import model.dto.MemberDataBean;
import model.member.MemberDao;


public class MemberUpdateFormAction implements CommandAction {//글목록 처리
    public String requestPro(HttpServletRequest request,
        HttpServletResponse response)throws Throwable {
    	request.setCharacterEncoding("UTF-8");
    
   // 	HttpSession session = request.getSession();
	//	MemberDataBean mdb = (MemberDataBean) session.getAttribute("memberDTO");
		
    	String memcode = request.getParameter("mem_code");
        System.out.println("memcode:"+memcode);
       	
       	///DB처리
       	AdminDao aPro = AdminDao.getInstance();// DB처리
       
       	
       	int memcode2 = Integer.parseInt(memcode);
       
    	AdGrantDataBean adminDTO=aPro.selectAdmin(memcode2);
    	if(adminDTO!=null){
    		//System.out.println("db에서 가져온 코드"+adminDTO.getAd_memcode());
     	   request.setAttribute("ad_grant", adminDTO);
    	}
     /*  	ArrayList<AdGrantDataBean> gList = aPro.get_GrantList();
    	if(gList.isEmpty())
    		gList=null;*/
    	
    	
       MemberDao mPro = MemberDao.getInstance();
       MemberDataBean mdata = mPro.getMember(memcode2);
       	
       System.out.println(mdata.getMem_black());
       
       request.setAttribute("member", mdata);
      
    
          
        return "/view/admin/member/admin_memberupdateForm.jsp";
    }
}
