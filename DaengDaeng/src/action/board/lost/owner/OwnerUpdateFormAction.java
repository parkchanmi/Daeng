package action.board.lost.owner;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import action.CommandAction;
import model.dto.LostBoardDataBean;
import model.dto.MemberDataBean;
import model.lost.OwnerDao;

public class OwnerUpdateFormAction implements CommandAction {
	
	public String requestPro(HttpServletRequest request, HttpServletResponse response)throws Throwable {
		HttpSession session = request.getSession();
		MemberDataBean mdata = (MemberDataBean) session.getAttribute("memberDTO");
		if(mdata==null){
			return "/view/member/login/member_loginForm.jsp";
		}
		int num = Integer.parseInt(request.getParameter("num"));
		String pageNum = request.getParameter("pageNum");
		//String lost_contents = request.getParameter("lost_contents");

		OwnerDao dbPro = OwnerDao.getInstance();
		LostBoardDataBean article = dbPro.updateGetArticle(num);

		// 뷰에서 사용할 속성
		request.setAttribute("pageNum", new Integer(pageNum));
		request.setAttribute("article", article);
		//request.setAttribute("lost_contents", lost_contents);

		return "/view/board/lost/owner/owner_updateForm.jsp";
	}
}