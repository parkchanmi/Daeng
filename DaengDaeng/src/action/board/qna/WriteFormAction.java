package action.board.qna;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import action.CommandAction;
import model.dto.MemberDataBean;

public class WriteFormAction implements CommandAction {
	
	public String requestPro(HttpServletRequest request, HttpServletResponse response) throws Throwable {
		
	
		int num = 0, board_ref = 1, board_step = 0, board_level = 0;
		if(request.getParameter("num")!=null) 
			num = Integer.parseInt(request.getParameter("num"));
		String pageNum = request.getParameter("pageNum");

        if (pageNum == null) {
            pageNum = "1";
        }
		try {
			if (num != 0) {//답변
				board_ref = Integer.parseInt(request.getParameter("board_ref"));
				board_step = Integer.parseInt(request.getParameter("board_step"));
				board_level = Integer.parseInt(request.getParameter("board_level"));
				request.setAttribute("num", num);
				request.setAttribute("board_ref", board_ref);
				request.setAttribute("board_step", board_step);
				request.setAttribute("board_level",board_level);

			}else{
				request.setAttribute("num", num);//0
				request.setAttribute("board_ref", board_ref);//1
				request.setAttribute("board_step", board_step);//0
				request.setAttribute("board_level",board_level);//0	
			}
		}catch (Exception e) {
			e.printStackTrace();
		}
		HttpSession session = request.getSession();
		MemberDataBean mdata = (MemberDataBean) session.getAttribute("memberDTO");
		if(mdata==null){
			return "/view/member/login/member_loginForm.jsp";
		}
		request.setAttribute("pageNum", pageNum);
		return "/view/board/qna/writeForm.jsp";
	}

}
