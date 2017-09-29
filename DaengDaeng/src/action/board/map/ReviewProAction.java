package action.board.map;

import java.sql.Timestamp;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import action.CommandAction;

import model.review.ReviewDao;
import model.dto.ReviewDataBean;
import model.map.MapDao;
import model.dto.MapDataBean;
import model.dto.MemberDataBean;
import model.member.MemberDao;

public class ReviewProAction implements CommandAction {
	public String requestPro(HttpServletRequest request, HttpServletResponse response) throws Throwable {
		
		request.setCharacterEncoding("utf-8");
		
		ReviewDataBean mapreview = new ReviewDataBean();
		
		String rev_typecode = request.getParameter("rev_typecode");
		String rev_contents = request.getParameter("rev_contents");
		String rev_typenum = request.getParameter("rev_typenum");
				
		HttpSession session = request.getSession();
		MemberDataBean mdata = (MemberDataBean) session.getAttribute("memberDTO");
		if(mdata==null){
			return "/view/member/login/member_loginForm.jsp";
		}
		int memecode = mdata.getMem_code();
		
		mapreview.setRev_memcode(memecode);
		mapreview.setRev_typecode(rev_typecode);
		System.out.println(rev_typecode);
		mapreview.setRev_typenum(Integer.parseInt(rev_typenum));
		mapreview.setRev_contents(rev_contents);
		mapreview.setRev_date(new Timestamp(System.currentTimeMillis()));
		
		ReviewDao mapdbPro = ReviewDao.getInstance();
		int result = mapdbPro.insertMapReview(mapreview);
		
		
		request.setAttribute("result", result);
		request.setAttribute("map_code", rev_typenum);
		
		return "/view/board/map/reviewPro.jsp";
	}

}
