package action.admin.board;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import action.CommandAction;
import model.admin.AdminDao;
import model.dog.DogDao;

public class BoardListProAction implements CommandAction{
    public String requestPro(HttpServletRequest request,
            HttpServletResponse response)throws Throwable {
				
    	String board_num = request.getParameter("board_num");
    	System.out.println(board_num);
    	if (board_num == null) {
    		board_num = "0";
    		}
    	int board_num02 = Integer.parseInt(board_num);
    	
    	AdminDao aPro  = AdminDao.getInstance();    	
    	int result = aPro.deleteBoard(board_num02);
    	
    	
    	return "/view/admin/board/admin_boardlistPro.jsp";
    		
    }
}
