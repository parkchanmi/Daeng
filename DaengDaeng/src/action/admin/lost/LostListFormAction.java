package action.admin.lost;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import action.CommandAction;
import model.admin.AdminDao;
import model.dto.BoardDataBean;
import model.dto.LostBoardDataBean;
import model.dto.MemberDataBean;
import model.dto.ReviewDataBean;
import model.member.MemberDao;

public class LostListFormAction implements CommandAction{
    public String requestPro(HttpServletRequest request,
            HttpServletResponse response)throws Throwable {
        	
    	request.setCharacterEncoding("UTF-8");
        	
    	HttpSession session = request.getSession();
		MemberDataBean mdb = (MemberDataBean) session.getAttribute("memberDTO");
		
        	
        	AdminDao adPro = AdminDao.getInstance();// DB처리	
        	MemberDao dbPro = MemberDao.getInstance();
        	
        	String losttype =  request.getParameter("lost_type");
        	if(losttype==null){
        		losttype="전체";
        	}
        	ArrayList<LostBoardDataBean> lbList = adPro.get_LostBdardList(losttype);
        	if(lbList.isEmpty())
        		lbList=null;
        	
        	
        	request.setAttribute("lostboard_list", lbList);
    		
        	return "/view/admin/lost/admin_lostlistForm.jsp";
            
            
        }
    }