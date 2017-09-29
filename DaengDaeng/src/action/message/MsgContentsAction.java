package action.message;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import action.CommandAction;
import model.dto.MessageDataBean;
import model.member.MemberDao;
import model.message.MessageDao;

public class MsgContentsAction implements CommandAction{
	 public String requestPro(HttpServletRequest request,
		        HttpServletResponse response)throws Throwable {
		 String receive = request.getParameter("type");//받은쪽지 읽으면 수신여부확인하려고
		 String msgID = request.getParameter("msgID");
		 MessageDao msgDao = MessageDao.getInstance();
		 MessageDataBean msgData;
		 if(receive!=null){//받은쪽지
			 msgData=msgDao.getMessage(Integer.parseInt(msgID),receive);
		 }else{//보낸쪽지
			msgData=msgDao.getMessage(Integer.parseInt(msgID));
		 }

		 request.setAttribute("msgData", msgData);
		 return "/view/message/message_contents.jsp";
	 }
}
