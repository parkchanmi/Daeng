package action.member.mypage;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import action.CommandAction;
import model.dog.DogDao;
import model.dto.DogDataBean;
import model.dto.MemberDataBean;
import model.member.MemberDao;

public class MpModify_PwFormAction implements CommandAction {// 글목록 처리
	public String requestPro(HttpServletRequest request, HttpServletResponse response) throws Throwable {

		return "/view/member/mypage/mp_modify_pwForm.jsp";
	}

}
