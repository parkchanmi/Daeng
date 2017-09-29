package action.board.free;

import java.sql.Timestamp;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import action.CommandAction;
import model.dto.*;
import model.review.*;

public class FreeReviewDeleteProAction implements CommandAction {
	public String requestPro(HttpServletRequest request, HttpServletResponse response) throws Throwable {
		
		request.setCharacterEncoding("utf-8");

		ReviewDataBean review = new ReviewDataBean();
		String article_num = request.getParameter("num");
		String pageNum = request.getParameter("pageNum");
		String rev_num = request.getParameter("rev_num");
		
		HttpSession session = request.getSession();
		MemberDataBean mdata = (MemberDataBean) session.getAttribute("memberDTO");
		if(mdata==null){
			return "/view/member/login/member_loginForm.jsp";
		}
		int memcode = mdata.getMem_code();
		
		ReviewDao rdbPro = ReviewDao.getInstance();
		int check = rdbPro.deleteArticle(Integer.parseInt(rev_num));
		

		if (article_num != null) {
			request.setAttribute("num", article_num);
		}

		if (pageNum != null) {
			//pageNum = request.getParameter("pageNum");
			request.setAttribute("pageNum", pageNum);
		}
		return "/view/board/free/free_reviewdeletePro.jsp";
	}

}
