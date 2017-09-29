package action.board.lost.finder;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import action.CommandAction;
import model.dto.LostBoardDataBean;
import model.lost.FinderDao;

public class FinderUpdateFormAction implements CommandAction {
	public String requestPro(HttpServletRequest request, HttpServletResponse response)throws Throwable {
		int num = Integer.parseInt(request.getParameter("num"));
		String pageNum = request.getParameter("pageNum");
		
		FinderDao dbPro = FinderDao.getInstance();
		LostBoardDataBean article = dbPro.updataGetArticle(num);
		
		request.setAttribute("pageNum", new Integer(pageNum));
		request.setAttribute("article", article);

		return "/view/board/lost/finder/finder_updateForm.jsp";
		
	}
	

}
