package action.club.detail;

import java.sql.Timestamp;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import action.CommandAction;
import model.club.ClubDao;
import model.dto.ClubBoardDataBean;
import model.dto.ClubInfoDataBean;
import model.dto.ClubJoinDataBean;
import model.dto.MemberDataBean;

public class ClubBoardWriteProAction implements CommandAction {//글목록 처리

    public String requestPro(HttpServletRequest request,
        HttpServletResponse response)throws Throwable {
    	
    	request.setCharacterEncoding("UTF-8");
    	HttpSession session = request.getSession();
		MemberDataBean mdata = (MemberDataBean) session.getAttribute("memberDTO");
		if(mdata==null){
			return "/view/member/login/member_loginForm.jsp";
		}
    	ClubJoinDataBean cdata =(ClubJoinDataBean) session.getAttribute("joinData");
    	int cjcode=cdata.getCj_code();
    	String cb_contents=request.getParameter("cb_contents");
    	String file_code=request.getParameter("file_code");
    	
    	System.out.println(cjcode+cb_contents+file_code);
    	
    	
    	ClubBoardDataBean cbdata= new ClubBoardDataBean();
    	cbdata.setCb_cjcode(cjcode);
    	cbdata.setCb_contents(cb_contents);
    	cbdata.setCb_date(new Timestamp(System.currentTimeMillis()));
    	cbdata.setCb_filecode(0);
    	
    	ClubDao cdb = ClubDao.getInstance();
    	
    	int result=cdb.writeClubBoard(cbdata);
    	request.setAttribute("result", result);
    	
        return "/view/club/detail/club_board_writePro.jsp";
    }
}
