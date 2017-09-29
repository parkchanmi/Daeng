package action.board.lost.owner;

import java.sql.Timestamp;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import action.CommandAction;
import model.dto.LostBoardDataBean;
import model.dto.MemberDataBean;
import model.lost.OwnerDao;

public class OwnerDeleteProAction implements CommandAction {

	public String requestPro(HttpServletRequest request, HttpServletResponse response) throws Throwable {

		request.setCharacterEncoding("utf-8");

		LostBoardDataBean article = new LostBoardDataBean();

		String lost_code = request.getParameter("num");
		
		HttpSession session = request.getSession();
		MemberDataBean mdata = (MemberDataBean) session.getAttribute("memberDTO");
		if(mdata==null){
			return "/view/member/login/member_loginForm.jsp";
		}

	

/*		int num = Integer.parseInt(request.getParameter("num")); */
		String pageNum = request.getParameter("pageNum");

		OwnerDao dbPro = OwnerDao.getInstance();
		int check = dbPro.deleteArticle(Integer.parseInt(lost_code));

		request.setAttribute("pageNum", new Integer(pageNum));
		request.setAttribute("check", check);

		return "/view/board/lost/owner/owner_deletePro.jsp";

	}

}
