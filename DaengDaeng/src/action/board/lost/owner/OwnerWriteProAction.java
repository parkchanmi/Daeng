package action.board.lost.owner;

import java.sql.Timestamp;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import action.CommandAction;
import model.dto.LostBoardDataBean;
import model.dto.MemberDataBean;
import model.lost.OwnerDao;

public class OwnerWriteProAction implements CommandAction {
	
	public String requestPro(HttpServletRequest request, HttpServletResponse response) throws Throwable {
		
		request.setCharacterEncoding("utf8");
		
		LostBoardDataBean article = new LostBoardDataBean();
		
		String lost_contents=request.getParameter("lost_contents");
		String lost_type=request.getParameter("lost_type");
		String lost_loc1=request.getParameter("lost_loc1");
		String lost_loc2="지역2";//println
		
		HttpSession session = request.getSession();
		MemberDataBean mdata = (MemberDataBean) session.getAttribute("memberDTO");
		if(mdata==null){
			return "/view/member/login/member_loginForm.jsp";
		}
		int memcode=mdata.getMem_code();//println
		
		System.out.println(memcode+lost_contents+lost_type+lost_loc1+lost_loc2);
		//article.setLost_code(Integer.parseInt(request.getParameter("lost_code")));
		article.setLost_writer(memcode); 
		article.setLost_filecode(0);
		article.setLost_type(lost_type);
		article.setLost_loc1(lost_loc1);
		article.setLost_loc2(lost_loc2);
		article.setLost_date(new Timestamp(System.currentTimeMillis()));
		article.setLost_contents(lost_contents);
			
		OwnerDao dbPro = OwnerDao.getInstance();
		int result=dbPro.insertArticle(article); //result로 값을 제대로 가져가는지 확인
		request.setAttribute("result", result);
		return "/view/board/lost/owner/owner_writePro.jsp";
	}

}