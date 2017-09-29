package action.board.lost.owner;

import java.util.Collections;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.lost.OwnerDao;
import action.CommandAction;

public class OwnerListAction implements CommandAction {

	public String requestPro(HttpServletRequest request, HttpServletResponse response) throws Throwable {

		String pageNum = request.getParameter("pageNum");
		String search = request.getParameter("search");
		int searchn = 0;

		if (pageNum == null) {
			pageNum = "1";
		}

		if (search == null) {
			search = "";
		} else { // 검색했다면
			searchn = Integer.parseInt(request.getParameter("searchn"));
		}
		System.out.println("search : " + search + "searhn" + searchn);

		int pageSize = 10;
		int currentPage = Integer.parseInt(pageNum);
		int startRow = (currentPage - 1) * pageSize + 1;// 한 페이지의 시작글 번호
		int endRow = currentPage * pageSize;// 한 페이지의 마지막 글번호
		int count = 0;
		int number = 0;

		List articleList = null;
		String lost_type = "강아지 찾기";
		OwnerDao dbPro = OwnerDao.getInstance();

		if (search.equals("") || search == null) {
			// 검색하지 않았을 때 (전체리스트)
			count = dbPro.getArticleCount(lost_type);
		} else {
			count = dbPro.getArticleCount(lost_type, searchn, search);
		}
		System.out.println("글의갯수2:" + count);

		if (count > 0) {
			if (search.equals("") || search == null) {
				articleList = dbPro.getArticles(startRow, endRow, lost_type);
			} else {
				articleList = dbPro.getArticles(startRow, endRow, lost_type, searchn, search);
			}
		}
		number = count - (currentPage - 1) * pageSize;// 8
		System.out.println(number);
		request.setAttribute("currentPage", new Integer(currentPage));
		request.setAttribute("startRow", new Integer(startRow));
		request.setAttribute("endRow", new Integer(endRow));
		request.setAttribute("count", count);
		request.setAttribute("pageSize", new Integer(pageSize));
		request.setAttribute("number", new Integer(number));
		request.setAttribute("articleList", articleList);

		return "/view/board/lost/owner/owner_list.jsp";
	}

}
