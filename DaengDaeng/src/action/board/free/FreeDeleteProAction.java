package action.board.free;

import java.sql.Timestamp;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import action.CommandAction;
import model.free.*;
import model.dto.*;

public class FreeDeleteProAction implements CommandAction {

	public String requestPro(HttpServletRequest request, HttpServletResponse response) throws Throwable {

		request.setCharacterEncoding("utf-8");

		BoardDataBean article = new BoardDataBean();

		String board_num = request.getParameter("num");

		HttpSession session = request.getSession();
		MemberDataBean mdata = (MemberDataBean) session.getAttribute("memberDTO");
		if(mdata==null){
			return "/view/member/login/member_loginForm.jsp";
		}
		

		String pageNum = request.getParameter("pageNum");

		FreeDao dbPro = FreeDao.getInstance();
		int check = dbPro.deleteArticle(Integer.parseInt(board_num));

		request.setAttribute("pageNum", new Integer(pageNum));
		request.setAttribute("check", check);

		return "/view/board/free/free_deletePro.jsp";

	}

}