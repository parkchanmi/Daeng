package action.board.free;

import java.sql.Timestamp;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import action.CommandAction;
import model.dto.BoardDataBean;
import model.dto.MemberDataBean;
import model.free.*;

public class FreeUpdateProAction implements CommandAction {
	public String requestPro(HttpServletRequest request, HttpServletResponse response) throws Throwable {

		request.setCharacterEncoding("utf-8");

		BoardDataBean article = new BoardDataBean();
		int board_num = Integer.parseInt(request.getParameter("num"));
		String board_title = request.getParameter("board_title");
		String board_type = "자유";
		String board_contents = request.getParameter("board_contents");

		HttpSession session = request.getSession();
		MemberDataBean mdata = (MemberDataBean) session.getAttribute("memberDTO");
		if(mdata==null){
			return "/view/member/login/member_loginForm.jsp";
		}
		int memcode = mdata.getMem_code();
		
		article.setBoard_num(board_num);
		article.setBoard_title(board_title);
		article.setBoard_date(new Timestamp(System.currentTimeMillis()));
		article.setBoard_contents(board_contents);
		
		FreeDao dbPro = FreeDao.getInstance();
		int check = dbPro.updateArtilce(article);
		
		request.setAttribute("check", check);
		
		return "/view/board/free/free_updatePro.jsp";
		
	}

}