package action.message;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import action.CommandAction;

public class WriteFormAction implements CommandAction {

    public String requestPro(HttpServletRequest request,
        HttpServletResponse response)throws Throwable {
    	
    	
        return "/view/message/message_writeForm.jsp";
    }
}
