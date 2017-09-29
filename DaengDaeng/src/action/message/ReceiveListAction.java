package action.message;


import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import action.CommandAction;
import model.dto.MemberDataBean;
import model.dto.MessageDataBean;
import model.message.MessageDao;

public class ReceiveListAction implements CommandAction {//받은쪽지

    public String requestPro(HttpServletRequest request,
        HttpServletResponse response)throws Throwable {
    	
    	HttpSession session = request.getSession();
    	MemberDataBean mdata = (MemberDataBean) session.getAttribute("memberDTO");
    	int receiver = mdata.getMem_code();
    	
    	MessageDao msgDao = MessageDao.getInstance();
    	ArrayList<MessageDataBean> mList = msgDao.getR_Message(receiver);
    	if(mList.isEmpty())
    		mList=null;
    	request.setAttribute("message_list", mList);
    	
    	return "/view/message/message_receive.jsp";
    }
}
