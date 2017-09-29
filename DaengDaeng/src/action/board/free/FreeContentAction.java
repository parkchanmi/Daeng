package action.board.free;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import action.CommandAction;
import model.dto.BoardDataBean;
import model.dto.MemberDataBean;
import model.free.FreeDao;

import model.review.ReviewDao;
import model.dto.ReviewDataBean;
import model.member.*;

public class FreeContentAction implements CommandAction {
	public String requestPro(HttpServletRequest request, HttpServletResponse response) throws Throwable {
		
		int num = Integer.parseInt(request.getParameter("num"));
		String pageNum = request.getParameter("pageNum");
		if(pageNum==null) pageNum="1";
		String board_type = "자유";
		
		FreeDao dbPro = FreeDao.getInstance();
		BoardDataBean article = dbPro.getArticleCount(num, board_type);
		
		
		request.setAttribute("num", new Integer(num));
		request.setAttribute("pageNum", new Integer(pageNum));
		request.setAttribute("article", article);
		
		String rev_typecode="자유"; //게시판마다 이부분만 고쳐주면됨
		int count = 0;		
		ReviewDao rdbPro = ReviewDao.getInstance();
		ArrayList<ReviewDataBean> articleList = rdbPro.getFreeReview(rev_typecode,num);
		if(articleList==null) {count=0;}
		else{count = articleList.size();//rdbPro.getArticleCount(); 
		}
		System.out.println(count);
		
		
		request.setAttribute("articleList", articleList);
		
		return "/view/board/free/free_contents.jsp";
	}

}
