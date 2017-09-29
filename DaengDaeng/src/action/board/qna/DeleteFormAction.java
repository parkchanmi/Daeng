package action.board.qna;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import action.CommandAction;
import model.dto.BoardDataBean;
import model.dto.MemberDataBean;
import model.board.BoardDao;

public class DeleteFormAction implements CommandAction {
	
	public String requestPro(HttpServletRequest request, HttpServletResponse response) throws Throwable {
		HttpSession session = request.getSession();
		MemberDataBean mdata = (MemberDataBean) session.getAttribute("memberDTO");
		if(mdata==null){
			return "/view/member/login/member_loginForm.jsp";
		}
		int num = Integer.parseInt(request.getParameter("num"));
		String pageNum = request.getParameter("pageNum");
		
		BoardDao dbPro = BoardDao.getInstance();
		BoardDataBean article = dbPro.deleteGetArticle(num);
		
		request.setAttribute("pageNum", pageNum);
		request.setAttribute("article", article);
		
		return "/view/board/qna/deleteForm.jsp";
	}
	

}
