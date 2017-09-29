package action.admin.member;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


import action.CommandAction;
import model.admin.AdminDao;
import model.dto.AdGrantDataBean;

import model.dto.MemberDataBean;
import model.member.MemberDao;

public class MemberListFormAction implements CommandAction {//글목록 처리

    public String requestPro(HttpServletRequest request,
        HttpServletResponse response)throws Throwable {
    	request.setCharacterEncoding("UTF-8");
    	
       	AdminDao aPro = AdminDao.getInstance();// DB처리	
     //  	MemberDao dbPro = MemberDao.getInstance();// DB처리	
    	
       	String memtype = request.getParameter("mem_type");
       	String memblack = request.getParameter("mem_black");
       	if(memtype==null) memtype="전체";
       	if(memblack==null) memblack="전체";
       	System.out.println(memtype+memblack);
    	ArrayList<MemberDataBean> member=aPro.getSelectboxMember(memtype,memblack);
       	if(!member.isEmpty())
       		System.out.println(member.size());
       	else{
       		System.out.println("결과없음");
       	}
    	
    	
       	
    	

    

   	/*
   	ArrayList<AdGrantDataBean> aList = aPro.get_AdminList();
    	if(aList.isEmpty())
    		aList=null;
    	
    	   
    	//회원구분 
    	int result1 = aPro.getCountnormal();
    	int result2 = aPro.getCountdogowner();
    	int result3 = aPro.getCountexpert();
    	
    	int sum = result1+result2+result3;
    	*/
    	
    	request.setAttribute("mem_list", member);
    	//request.setAttribute("ad_list", aList);
    	//request.setAttribute("sum", sum);
    	

		
    	return "/view/admin/member/admin_memberlistForm.jsp";
        
        
    }
}