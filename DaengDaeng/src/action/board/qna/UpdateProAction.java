package action.board.qna;

import java.sql.Timestamp;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import action.CommandAction;
import model.dto.BoardDataBean;
import model.board.BoardDao;
import model.dto.MemberDataBean;

public class UpdateProAction implements CommandAction {
	
	public String requestPro(HttpServletRequest request, HttpServletResponse response) throws Throwable {
		
		request.setCharacterEncoding("utf-8");
		
		BoardDataBean article = new BoardDataBean();
		
		HttpSession session = request.getSession();
		MemberDataBean mdata = (MemberDataBean) session.getAttribute("memberDTO");
		if(mdata==null){
			return "/view/member/login/member_loginForm.jsp";
		}
		int num = Integer.parseInt(request.getParameter("num"));
		article.setBoard_num(num);
		article.setBoard_filecode(0);
		article.setBoard_title(request.getParameter("board_title"));
		article.setBoard_date(new Timestamp(System.currentTimeMillis()));
		article.setBoard_contents(request.getParameter("board_contents"));
		
		
		
		BoardDao dbPro = BoardDao.getInstance();
		int check = dbPro.updateArticle(article);
		
		request.setAttribute("check", check);
		request.setAttribute("pageNum", check);
		return "/view/board/qna/updatePro.jsp";

	}

}
