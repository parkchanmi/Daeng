package action.board.free;

import java.util.Collections;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.free.FreeDao;
import action.CommandAction;

public class FreeListAction implements CommandAction {

	public String requestPro(HttpServletRequest request, HttpServletResponse response) throws Throwable {
		String pageNum = request.getParameter("pageNum");
		String search = request.getParameter("search");
		int searchn = 0;

		if (pageNum == null) {
			pageNum = "1";
		}

		if (search == null) {
			search = "";
		} else {
			searchn = Integer.parseInt(request.getParameter("searchn"));
		}

		int pageSize = 10;
		int currentPage = Integer.parseInt(pageNum);
		int startRow = (currentPage - 1) * pageSize + 1;
		int endRow = currentPage * pageSize;
		int count = 0;
		int number = 0;

		List articleList = null;
		FreeDao free_dbPro = FreeDao.getInstance();
		String board_type = "자유";
		if (search.equals("") || search == null) {
			// 검색하지 않았을 때 (전체리스트)
			count = free_dbPro.getArticleCount(board_type);
		} else {
			count = free_dbPro.getArticleCount(searchn, search, board_type);
		}
		System.out.println("글의갯수2:" + count);
		if (count > 0) {// 검색요건에 맞는 글이 있다면
			if (search.equals("") || search == null)
				articleList = free_dbPro.getArticles(startRow, endRow, board_type);
			else
				articleList = free_dbPro.getArticles(startRow, endRow, searchn, search, board_type);
		}
		
		number = count - (currentPage - 1) * pageSize;
		System.out.println("글의 시작점:"+number);
		request.setAttribute("currentPage", new Integer(currentPage));
		request.setAttribute("startRow", new Integer(startRow));
		request.setAttribute("endRow", new Integer(endRow));
		request.setAttribute("count", count);
		request.setAttribute("pageSize", new Integer(pageSize));
		request.setAttribute("number", new Integer(number));
		request.setAttribute("articleList", articleList);

		return "/view/board/free/free_list.jsp";

	}

}
