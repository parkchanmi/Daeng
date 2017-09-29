package action.admin.board;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import action.CommandAction;
import model.admin.AdminDao;


public class ReviewListProAction implements CommandAction{
    public String requestPro(HttpServletRequest request,
            HttpServletResponse response)throws Throwable {
				
    	String rev_num = request.getParameter("rev_num");
    	System.out.println(rev_num);
    	if (rev_num == null) {
    		rev_num = "0";
    		}
    	int rev_num02 = Integer.parseInt(rev_num);
    	
    	AdminDao aPro  = AdminDao.getInstance();    	
    	int result = aPro.deleteReview(rev_num02);
    	
    	
    	return "/view/admin/board/admin_reviewlistPro.jsp";
    		
    }
}
