package action.club.detail;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import action.CommandAction;
import model.club.ClubDao;
import model.dto.ClubBoardDataBean;
import model.dto.ClubInfoDataBean;
import model.dto.ClubJoinDataBean;
import model.dto.MemberDataBean;

public class ClubBoardAction implements CommandAction {//글목록 처리

    public String requestPro(HttpServletRequest request,
        HttpServletResponse response)throws Throwable {
    	ClubDao cdb = ClubDao.getInstance();
    	HttpSession session = request.getSession();
		MemberDataBean mdata = (MemberDataBean) session.getAttribute("memberDTO");
		if(mdata==null){
			return "/view/member/login/member_loginForm.jsp";
		}
    	ClubInfoDataBean cdata=null;
    	String cicode=request.getParameter("cicode");
    	boolean leader = false;
    	if(cicode!=null){//소모임 게시판 처음 접근할때
    		cdata = cdb.getClub_Info(Integer.parseInt(cicode));//파라미터를 이용해서 db에서 가져옴 
    		session.setAttribute("clubinfo", cdata);
    	}
    	else{//소모임 detail내에서 전체글을 호출할때
    		cdata=(ClubInfoDataBean) session.getAttribute("clubinfo");//세션에서 가져옴
    	}
		
    	int clubCode = cdata.getCi_code();//현재 소모임의 code
		
    	int memCode = mdata.getMem_code(); // 로그인한 회원
		//해당회원이 소모임의 리더인지
		
		//해당회원이 소모임의 멤버인지 아닌지!
    	ClubJoinDataBean joinData = cdb.IsMember(memCode, clubCode);
    	session.setAttribute("joinData", joinData);
    	if(joinData!=null){
    		leader = cdb.IsLeader(clubCode, memCode);
    		session.setAttribute("leader", leader);
    	}
    	//게시글불러오기
		ArrayList<ClubBoardDataBean> cList = cdb.getClubArticles(cdata.getCi_code());
		if(cList!=null){
			request.setAttribute("articles", cList);
			request.setAttribute("count", cList.size());
		}
		
		
        return "/view/club/detail/club_board_list.jsp";
    }
}
