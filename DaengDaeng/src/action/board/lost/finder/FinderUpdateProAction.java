package action.board.lost.finder;

import java.sql.Timestamp;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import action.CommandAction;
import model.dto.LostBoardDataBean;
import model.lost.FinderDao;
import model.dto.MemberDataBean;

public class FinderUpdateProAction implements CommandAction {
	public String requestPro(HttpServletRequest request, HttpServletResponse response) throws Throwable {
		
		request.setCharacterEncoding("utf-8");
		
		LostBoardDataBean article = new LostBoardDataBean();
		
		String lost_code = request.getParameter("lost_code");
		String lost_contents = request.getParameter("lost_contents");
		String lost_loc1=request.getParameter("lost_loc1");
		String lost_loc2=request.getParameter("지역2");
		
		HttpSession session = request.getSession();
		MemberDataBean mdata = (MemberDataBean) session.getAttribute("memberDTO");
		if(mdata==null){
			return "/view/member/login/member_loginForm.jsp";
		}
		int memcode=mdata.getMem_code();
		
		System.out.println(memcode+lost_contents+lost_loc1+lost_loc2);
		
		article.setLost_code(Integer.parseInt(lost_code));
	//	article.setLost_writer(memcode);
		article.setLost_loc1(lost_loc1);
		article.setLost_loc2(lost_loc2);
		article.setLost_date(new Timestamp(System.currentTimeMillis()));
		article.setLost_contents(lost_contents);
		
		FinderDao dbPro = FinderDao.getInstance();
		int check = dbPro.updateArticle(article);
		
		request.setAttribute("check", check);
		/*int result = dbPro.insertArticle(article);
		request.setAttribute("result", result);*/
		
		return"/view/board/lost/finder/finder_updatePro.jsp";
		
	}

}