package action.board.qna;

import java.sql.Timestamp;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import action.CommandAction;
import model.dto.ReviewDataBean;
import model.review.ReviewDao;
import model.dto.MemberDataBean;

public class RDeleteProAction implements CommandAction {
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
		
		/*review.setRev_memcode(memcode);
		review.setRev_typecode(rev_typecode);
		review.setRev_typenum(Integer.parseInt(rev_typenum));
		review.setRev_contents(rev_contents);
		review.setRev_date(new Timestamp(System.currentTimeMillis()));*/
		
		ReviewDao rdbPro = ReviewDao.getInstance();
		int check = rdbPro.deleteArticle(Integer.parseInt(rev_num));

	
		if (article_num != null) {
			request.setAttribute("num", article_num);
		}

		if (pageNum != null) {
			//pageNum = request.getParameter("pageNum");
			request.setAttribute("pageNum", pageNum);
		}
		
		//request.setAttribute("num", new Integer(num));
		//request.setAttribute("pageNum", new Integer(pageNum));
		
		return "/view/board/qna/rdeletePro.jsp";

	}

}
