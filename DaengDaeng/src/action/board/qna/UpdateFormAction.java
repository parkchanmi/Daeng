package action.board.qna;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import action.CommandAction;
import model.dto.BoardDataBean;
import model.dto.MemberDataBean;
import model.board.BoardDao;

public class UpdateFormAction implements CommandAction{
	public String requestPro(HttpServletRequest request, HttpServletResponse response)throws Throwable {
		HttpSession session = request.getSession();
		MemberDataBean mdata = (MemberDataBean) session.getAttribute("memberDTO");
		if(mdata==null){
			return "/view/member/login/member_loginForm.jsp";
		}
		int num=-1,pageNum=1;
		if(request.getParameter("num")!=null){
		num = Integer.parseInt(request.getParameter("num"));
		pageNum = Integer.parseInt(request.getParameter("pageNum"));
		}
		
		//String lost_contents = request.getParameter("lost_contents");
		System.out.println("글번호:"+num);
		BoardDao dbPro = BoardDao.getInstance();
		BoardDataBean article = dbPro.updateGetArticle(num);
		
		// 뷰에서 사용할 속성
		request.setAttribute("pageNum", pageNum);
		request.setAttribute("article", article);
		//request.setAttribute("lost_contents", lost_contents);

		return "/view/board/qna/updateForm.jsp";
	}
}