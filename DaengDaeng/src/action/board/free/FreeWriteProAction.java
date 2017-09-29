package action.board.free;

import java.sql.Timestamp;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import action.CommandAction;
import model.free.FreeDao;
import model.dto.BoardDataBean;
import model.dto.MemberDataBean;
import model.member.MemberDao;

public class FreeWriteProAction implements CommandAction {
	public String requestPro(HttpServletRequest request, HttpServletResponse response) throws Throwable {
		
		request.setCharacterEncoding("UTF-8");

		BoardDataBean article = new BoardDataBean();
		String board_type = "자유";
		
		String board_contents=request.getParameter("board_contents");
		String board_title=request.getParameter("board_title");
				
		
		HttpSession session = request.getSession();
		MemberDataBean mdata = (MemberDataBean) session.getAttribute("memberDTO");
		if(mdata==null){
			return "/view/member/login/member_loginForm.jsp";
		}
		int memcode = mdata.getMem_code();
		
		System.out.println(board_contents);
		
		article.setBoard_writer(memcode);
		article.setBoard_filecode(0);
		article.setBoard_title(board_title);
		System.out.println(board_title);
		article.setBoard_date(new Timestamp(System.currentTimeMillis()));
		article.setBoard_type(board_type);
		article.setBoard_rdcount(0);
		article.setBoard_contents(board_contents);
		
		FreeDao freedbPro = FreeDao.getInstance();
		int result = freedbPro.insertArticle(article, board_type);
		request.setAttribute("result", result);
		
		return "/view/board/free/free_writePro.jsp";
	}

}
