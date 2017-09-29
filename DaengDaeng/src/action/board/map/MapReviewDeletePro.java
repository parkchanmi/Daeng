package action.board.map;

import java.sql.Timestamp;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import action.CommandAction;
import model.dto.ReviewDataBean;
import model.review.ReviewDao;
import model.dto.MemberDataBean;

public class MapReviewDeletePro implements CommandAction {
	public String requestPro(HttpServletRequest request, HttpServletResponse response) throws Throwable {
		
		request.setCharacterEncoding("utf-8");
		
		ReviewDataBean mapreview = new ReviewDataBean();
		String map_code = request.getParameter("map_code");
		String rev_num = request.getParameter("rev_num");
		
		HttpSession session = request.getSession();
		MemberDataBean mdata = (MemberDataBean) session.getAttribute("memberDTO");
		if(mdata==null){
			return "/view/member/login/member_loginForm.jsp";
		}
		int memcode = mdata.getMem_code();
		
		System.out.println("rev_num : " + rev_num);
		System.out.println("map_code : " + map_code);
		
		ReviewDao mapreviewPro = ReviewDao.getInstance();
		int check = mapreviewPro.deleteMapArticle(Integer.parseInt(rev_num));
		
		if ( map_code != null) {
			
			request.setAttribute("map_code", map_code);
		}
		request.setAttribute("result", check);
		
		return "/view/board/map/mapreviewdeletePro.jsp";
	}

}