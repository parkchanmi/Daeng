package action.admin.lost;

import java.sql.Timestamp;
import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import action.CommandAction;
import model.admin.AdminDao;
import model.dto.BoardDataBean;
import model.dto.LostBoardDataBean;
import model.dto.MemberDataBean;
import model.dto.MessageDataBean;
import model.dto.ReviewDataBean;
import model.member.MemberDao;
import model.message.MessageDao;

public class LostListProAction implements CommandAction{
    public String requestPro(HttpServletRequest request,
            HttpServletResponse response)throws Throwable {
        	
    	request.setCharacterEncoding("UTF-8");
    	String loc=request.getParameter("lost_loc");
    	String lost_code = request.getParameter("lost_code");
    		AdminDao adpro = AdminDao.getInstance();
    		ArrayList<Integer> receiver = adpro.get_MsgReceiver(loc);
    		MessageDao mdpro = MessageDao.getInstance();
    		int result=0;
    		for(int i=0;i<receiver.size();i++){
    			MessageDataBean msg = new MessageDataBean();
    			msg.setMsg_sender(0);
    			msg.setMsg_receiver(receiver.get(i));
    			msg.setMsg_date(new Timestamp(System.currentTimeMillis()));
    			msg.setMsg_title("회원님의 지역에 실종접수가 등록되었습니다.");
    			msg.setMsg_contents("실종게시판 - 게시글번호 ["+lost_code+"]번을 열람해주세요.\n[링크]");
    			msg.setMsg_read_ok(0);
    			result=mdpro.sendMessage(msg);
    		}
    		request.setAttribute("result",result);
        	return "/view/admin/lost/admin_lostlistPro.jsp";
            
            
        }
    }