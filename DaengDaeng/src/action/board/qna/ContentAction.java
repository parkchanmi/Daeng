package action.board.qna;

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
import model.board.BoardDao;

import model.review.ReviewDao;
import model.dto.ReviewDataBean;
import model.member.MemberDao;

public class ContentAction implements CommandAction {
	public String requestPro(HttpServletRequest request, HttpServletResponse response) throws Throwable {

		int num = Integer.parseInt(request.getParameter("num"));
		String pageNum = request.getParameter("pageNum");
		if(pageNum==null) pageNum="1";
		BoardDao dbPro = BoardDao.getInstance();
		BoardDataBean article = dbPro.getArticle(num);
		
		

		// 해당 뷰에서 사용할 속성
		request.setAttribute("num", new Integer(num));
		request.setAttribute("pageNum", new Integer(pageNum));
		request.setAttribute("article", article);
			
/*		String rowPageNum = request.getParameter("rowPageNum");
		
		if (rowPageNum ==null){
			rowPageNum = "1";
		}
		
		int pageSize = 100;
		int currentPage =Integer.parseInt(rowPageNum);
		int startRow = (currentPage -1) * pageSize +1;
		int endRow = currentPage * pageSize;
		int count  = 0;
		int number = 0;*/
		String rev_typecode="Q&A"; //게시판마다 이부분만 고쳐주면됨
		int count = 0;		
		ReviewDao rdbPro = ReviewDao.getInstance();
		ArrayList<ReviewDataBean> articleList = rdbPro.getReview("Q&A",num);
		if(articleList==null) {count=0;}
		else{count = articleList.size();//rdbPro.getArticleCount(); 
		}
		System.out.println(count);
		
		
		request.setAttribute("articleList", articleList);
				
/*		int count = 0;
		List articleList = null;
		count = rdbPro.getArticleCount();*/
				
		return "/view/board/qna/contents.jsp";
	}
}