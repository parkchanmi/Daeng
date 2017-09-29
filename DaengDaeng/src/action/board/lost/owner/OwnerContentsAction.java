package action.board.lost.owner;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import action.CommandAction;
import model.lost.OwnerDao;
import model.review.ReviewDao;
import model.dto.LostBoardDataBean;
import model.dto.ReviewDataBean;

public class OwnerContentsAction implements CommandAction {
	public String requestPro(HttpServletRequest request, HttpServletResponse response) throws Throwable {

		int num = Integer.parseInt(request.getParameter("num")); //lost_code

		String pageNum = request.getParameter("pageNum");

		OwnerDao dbPro = OwnerDao.getInstance();
		LostBoardDataBean article = dbPro.getArticle(num);

	
		// 해당 뷰에서 사용할 속성
		request.setAttribute("num", new Integer(num));
		request.setAttribute("pageNum", new Integer(pageNum));
		request.setAttribute("article", article);
		
		//리뷰작성
		String rev_typecode="강아지 찾기"; //게시판마다 이부분만 고쳐주면됨
		int count = 0;		
		ReviewDao rdbPro = ReviewDao.getInstance();
		ArrayList<ReviewDataBean> reviewList = rdbPro.getOwnerReview("강아지 찾기",num);
		if(reviewList==null) {count=0;}
		else{count = reviewList.size();//rdbPro.getArticleCount(); 
		request.setAttribute("articleList", reviewList);
		System.out.println(count + "num : " + num);
		}

		return "/view/board/lost/owner/owner_contents.jsp";
	}
}