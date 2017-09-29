package action.board.qna;

import java.sql.Timestamp;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import action.CommandAction;
import model.board.BoardDao;
import model.dto.BoardDataBean;
import model.dto.MemberDataBean;
import model.member.MemberDao;

public class WriteProAction implements CommandAction {

	public String requestPro(HttpServletRequest request, HttpServletResponse response) throws Throwable {

		request.setCharacterEncoding("UTF-8");

		BoardDataBean article = new BoardDataBean();
		
		String board_contents=request.getParameter("board_contents");
		int num=0,board_ref=0,board_step=0,board_level=0;
		if(request.getParameter("num")!=null){
			num = Integer.parseInt(request.getParameter("num"));
			board_ref = Integer.parseInt(request.getParameter("board_ref"));
			board_step = Integer.parseInt(request.getParameter("board_step"));
			board_level = Integer.parseInt(request.getParameter("board_level"));
		};
		HttpSession session = request.getSession();
		MemberDataBean mdata = (MemberDataBean) session.getAttribute("memberDTO");
		if(mdata==null){
			return "/view/member/login/member_loginForm.jsp";
		}
		int memcode = mdata.getMem_code();
		
	
		
		article.setNum(num);
		article.setBoard_writer(memcode);
		article.setBoard_filecode(0);
		article.setBoard_title(request.getParameter("board_title"));
		article.setBoard_date(new Timestamp(System.currentTimeMillis()));
		article.setBoard_type(request.getParameter("board_type"));
		article.setBoard_rdcount(0);
		article.setBoard_contents(request.getParameter("board_contents"));
		article.setBoard_ref(board_ref);
		article.setBoard_step(board_step);
		article.setBoard_level(board_level);
		
		BoardDao dbPro = BoardDao.getInstance();
		int result = dbPro.insertArticle(article);
		request.setAttribute("result", result);

		return "/view/board/qna/writePro.jsp";
	}
}