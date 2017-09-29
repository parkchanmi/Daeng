package action.admin.board;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import action.CommandAction;
import model.admin.AdminDao;
import model.dto.BoardDataBean;
import model.dto.MemberDataBean;
import model.dto.ReviewDataBean;
import model.member.MemberDao;

public class ReviewListFormAction implements CommandAction{
    public String requestPro(HttpServletRequest request,
            HttpServletResponse response)throws Throwable {
        	
    	request.setCharacterEncoding("UTF-8");
        	
    	HttpSession session = request.getSession();
		MemberDataBean mdb = (MemberDataBean) session.getAttribute("memberDTO");
		
        	
        	AdminDao adPro = AdminDao.getInstance();// DB처리	
        	MemberDao dbPro = MemberDao.getInstance();
        	String reviewtype =  request.getParameter("review_type");
        	if(reviewtype==null){
        		reviewtype="전체";
        	}
        	
        	ArrayList<ReviewDataBean> rList = adPro.get_ReviewList(reviewtype);
        	if(rList.isEmpty())
        		rList=null;
        	
        	
        	//System.out.println(mList.size()); 	
        	request.setAttribute("review_list", rList);
    		
        	return "/view/admin/board/admin_reviewlistForm.jsp";
            
            
        }
    }