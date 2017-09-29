package action.board.lost.finder;

import java.sql.Timestamp;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import action.CommandAction;
import model.review.ReviewDao;
import model.dto.ReviewDataBean;
import model.dto.MemberDataBean;
import model.member.MemberDao;

import model.lost.OwnerDao;
import model.dto.LostBoardDataBean;

public class FinderReviewProAction implements CommandAction {
	
	public String requestPro(HttpServletRequest request, HttpServletResponse response) throws Throwable {
		request.setCharacterEncoding("UTF-8");

		ReviewDataBean reviewarticle = new ReviewDataBean();

		String rev_typecode = request.getParameter("rev_typecode");
		String rev_contents = request.getParameter("rev_contents");
		String rev_typenum = request.getParameter("rev_typenum");
		System.out.println(rev_typecode + rev_contents + rev_typenum);

		HttpSession session = request.getSession();
		MemberDataBean mdata = (MemberDataBean) session.getAttribute("memberDTO");
		if(mdata==null){
			return "/view/member/login/member_loginForm.jsp";
		}
		int memcode = mdata.getMem_code();

		// article.setRev_num(rev_num);
		reviewarticle.setRev_memcode(memcode);
		reviewarticle.setRev_typecode(request.getParameter("rev_typecode"));
		reviewarticle.setRev_typenum(Integer.parseInt(rev_typenum));
		reviewarticle.setRev_contents(request.getParameter("rev_contents"));
		reviewarticle.setRev_date(new Timestamp(System.currentTimeMillis()));

		ReviewDao rdbPro = ReviewDao.getInstance();
		int result = rdbPro.insertOwnerReview(reviewarticle);
		request.setAttribute("result", result);

		/*
		 * int count = 0; List articleList = null; count = rdbPro. ();
		 */

		int num = 0;
		String pageNum = "";
		if (request.getParameter("num") != null) {
			num = Integer.parseInt(request.getParameter("num"));
			request.setAttribute("num", num);
		}

		if (request.getParameter("pageNum") != null) {
			pageNum = request.getParameter("pageNum");
			request.setAttribute("pageNum", pageNum);
		}

		// 해당 뷰에서 사용할 속성
		request.setAttribute("num", new Integer(num));
		request.setAttribute("pageNum", new Integer(pageNum));

		return "/view/board/lost/finder/finder_reviewPro.jsp";

	}
}