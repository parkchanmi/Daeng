package action.board.free;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import action.CommandAction;
import model.dto.MemberDataBean;

public class FreeWriteFromAction implements CommandAction {
	
	public String requestPro(HttpServletRequest request, HttpServletResponse response) throws Throwable {
		
		HttpSession session = request.getSession();
		MemberDataBean mdata = (MemberDataBean) session.getAttribute("memberDTO");
		if(mdata==null){
			return "/view/member/login/member_loginForm.jsp";
		}
		int num = 0, board_ref = 1, board_step = 0, board_level = 0;
		try {
			if (request.getParameter("num") != null) {//답변
				num = Integer.parseInt(request.getParameter("num"));
				board_ref = Integer.parseInt(request.getParameter("board_ref"));
				board_step = Integer.parseInt(request.getParameter("board_step"));
				board_level = Integer.parseInt(request.getParameter("board_level"));
				
				request.setAttribute("num", new Integer(num));
				request.setAttribute("board_ref", new Integer(board_ref));
				request.setAttribute("board_step", new Integer(board_step));
				request.setAttribute("board_level", new Integer(board_level));

			}else{
				request.setAttribute("num", new Integer(num));
				request.setAttribute("board_ref", new Integer(board_ref));
				request.setAttribute("board_step", new Integer(board_step));
				request.setAttribute("board_level", new Integer(board_level));				
			}
		}catch (Exception e) {
			e.printStackTrace();
		}
		System.out.println(num);
		return "/view/board/free/free_writeForm.jsp";
	}

}
