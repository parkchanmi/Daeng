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

public class FindReceiverAction implements CommandAction {

    public String requestPro(HttpServletRequest request,
        HttpServletResponse response)throws Throwable {
    	
    	
    	String receiver = request.getParameter("m_receive"); //네임
    	System.out.println(receiver);
    	int memCode=-1;
    	if(receiver!=null){
    		MemberDao mdb = MemberDao.getInstance();
    		memCode=mdb.getMember(receiver); 
    	}
    	request.setAttribute("memcode",memCode);//없으면 -1 있으면 memCode
    	System.out.println("memcode:"+memCode);
    	
        return "/view/message/find_receiver.jsp";
    }
}
