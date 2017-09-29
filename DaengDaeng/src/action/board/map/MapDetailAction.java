package action.board.map;

import java.util.ArrayList;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import action.CommandAction;
import model.dto.MemberDataBean;
import model.dto.ReviewDataBean;
import model.dto.MapDataBean;
import model.map.MapDao;
import model.review.ReviewDao;

public class MapDetailAction implements CommandAction {
	public String requestPro(HttpServletRequest request, HttpServletResponse response) throws Throwable {

		int map_code = Integer.parseInt(request.getParameter("map_code"));
		// int map_code = Integer.parseInt(request.getParameter("map_code"));
		System.out.println(map_code);

		MapDao dbPro = MapDao.getInstance();
		MapDataBean map = dbPro.getMapDetail(map_code);
		request.setAttribute("map", map);

		String rev_typecode = "지도"; // 게시판마다 이부분만 고쳐주면됨
		int count = 0;
		ReviewDao rdbPro = ReviewDao.getInstance();
		ArrayList<ReviewDataBean> reviewList = rdbPro.getMapReview(rev_typecode, map_code);
		if (reviewList!=null) {
			count = reviewList.size();// rdbPro.getArticleCount();
		} 
		System.out.println("리뷰갯수:"+count);
		if(reviewList!=null){
		request.setAttribute("map_review_list", reviewList);
		}
		return "/view/board/map/map_detail.jsp";
	}

}
