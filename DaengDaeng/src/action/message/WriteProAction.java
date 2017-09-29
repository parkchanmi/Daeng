package action.message;


import java.sql.Timestamp;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import action.CommandAction;
import model.dto.MemberDataBean;
import model.dto.MessageDataBean;
import model.member.MemberDao;
import model.message.MessageDao;

public class WriteProAction implements CommandAction {

    public String requestPro(HttpServletRequest request,
        HttpServletResponse response)throws Throwable {
    	
    	//쪽지DB처리
    	String receiver = request.getParameter("m_receive");
    	String title = request.getParameter("m_title");
    	String contents = request.getParameter("m_contents");
    	HttpSession session = request.getSession();
    	MemberDataBean mdata = (MemberDataBean) session.getAttribute("memberDTO");
    	int sender = mdata.getMem_code();
    	MemberDao mdb = MemberDao.getInstance();
    	int receiver_num=mdb.getMember(receiver);
    	//messageDataBean생성
    	MessageDataBean msgData = new MessageDataBean();
    	msgData.setMsg_contents(contents);
    	msgData.setMsg_date(new Timestamp(System.currentTimeMillis()));
    	msgData.setMsg_read_ok(0);
    	msgData.setMsg_receiver(receiver_num);
    	msgData.setMsg_sender(sender);
    	msgData.setMsg_title(title);
    	//messageDao 메서드실행
    	MessageDao msgDao = MessageDao.getInstance();
    	int result = msgDao.sendMessage(msgData);//성공:1 실패:0
    	
    	request.setAttribute("result",result);
    	
    	
        return "/view/message/message_writePro.jsp";
    }
}
