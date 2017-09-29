package action.board.lost.owner;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import action.CommandAction;
import model.dto.LostBoardDataBean;
import model.dto.MemberDataBean;
import model.lost.OwnerDao;

public class OwnerDeleteFormAction implements CommandAction {
	public String requestPro(HttpServletRequest request, HttpServletResponse response) throws Throwable {
		HttpSession session = request.getSession();
		MemberDataBean mdata = (MemberDataBean) session.getAttribute("memberDTO");
		if(mdata==null){
			return "/view/member/login/member_loginForm.jsp";
		}
		int num = Integer.parseInt(request.getParameter("num"));
		String pageNum = request.getParameter("pageNum");
		
		OwnerDao dbPro = OwnerDao.getInstance();
		LostBoardDataBean article = dbPro.deleteGetArticle(num);
		
		/*request.setAttribute("num", new Integer(num));*/
		request.setAttribute("pageNum", pageNum);
		request.setAttribute("article", article);
		
		return "/view/board/lost/owner/owner_deleteForm.jsp";

	}

}
