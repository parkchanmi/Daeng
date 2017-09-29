package action.message;


import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import action.CommandAction;
import model.dto.MemberDataBean;
import model.dto.MessageDataBean;
import model.message.MessageDao;

public class SendListAction implements CommandAction {

    public String requestPro(HttpServletRequest request,
        HttpServletResponse response)throws Throwable {
    	HttpSession session = request.getSession();
    	MemberDataBean mdata = (MemberDataBean) session.getAttribute("memberDTO");
    	int sender = mdata.getMem_code();
    	MessageDao msgDao = MessageDao.getInstance();
    	ArrayList<MessageDataBean> mList =msgDao.getS_Message(sender);
    	if(mList.isEmpty())
    		mList=null;
    	request.setAttribute("message_list", mList);
    	return "/view/message/message_send.jsp";
    }
}
