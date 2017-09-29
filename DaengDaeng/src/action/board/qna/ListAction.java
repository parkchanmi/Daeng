package action.board.qna;

import java.util.Collections;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.board.BoardDao;
import action.CommandAction;

public class ListAction implements CommandAction {
	
	public String requestPro(HttpServletRequest request, HttpServletResponse response) throws Throwable {
		
		//페이지값 처리
		String pageNum = request.getParameter("pageNum");
		String search = request.getParameter("search");
		int searchn = 0;
		
		if (pageNum == null) {
			pageNum = "1";
		}
		
		//검색했는지 확인
		if (search == null) {
			search = "";
		} else { //검색했다면
			searchn = Integer.parseInt(request.getParameter("searchn")); 
		}
		System.out.println("search : " + search +"searhn" + searchn );
		int pageSize = 10;
		int currentPage = Integer.parseInt(pageNum);
		int startRow = (currentPage - 1) * pageSize + 1;
		int endRow = currentPage * pageSize;
		int count = 0;
		int number = 0;
		
		List articleList = null;
		BoardDao dbPro = BoardDao.getInstance();
		//count = dbPro.getArticleCount();
		String board_type = "Q&A";
		
		
		if (search.equals("") || search == null) {
			//검색하지 않았을 때 (전체리스트)
			count = dbPro.getArticleCount(board_type);
		}else{
			count = dbPro.getArticleCount(searchn, search, board_type);
		}
		System.out.println("글의갯수:"+count);
		if (count > 0) {//검색요건에 맞는 글이 있다면
			if (search.equals("") || search == null)
				articleList = dbPro.getArticles(startRow, endRow, board_type);
			else
				articleList = dbPro.getArticles(startRow, endRow, searchn, search, board_type);
		}
		
		number = count - (currentPage - 1) * pageSize;
		request.setAttribute("currentPage", new Integer(currentPage));
		request.setAttribute("startRow", new Integer(startRow));
		request.setAttribute("endRow", new Integer(endRow));
		request.setAttribute("count", count);
		request.setAttribute("pageSize", new Integer(pageSize));
		request.setAttribute("number", new Integer(number));
		request.setAttribute("articleList", articleList);
		
		return "/view/board/qna/qna_list.jsp";
	}

}
