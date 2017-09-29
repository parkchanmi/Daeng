package action.member.mypage;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import action.CommandAction;

public class MpCalendarFormAction implements CommandAction {//글목록 처리

    public String requestPro(HttpServletRequest request,
        HttpServletResponse response)throws Throwable {
        return "/view/member/mypage/mp_calendarForm.jsp";
    }
}
