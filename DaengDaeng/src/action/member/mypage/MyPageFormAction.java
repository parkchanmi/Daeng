package action.member.mypage;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import action.CommandAction;
import model.dog.DogDao;
import model.dto.DogDataBean;
import model.dto.MemberDataBean;
import model.member.MemberDao;

public class MyPageFormAction implements CommandAction {// 글목록 처리

	public String requestPro(HttpServletRequest request, HttpServletResponse response) throws Throwable {

		HttpSession session = request.getSession();
		MemberDataBean mdb = (MemberDataBean) session.getAttribute("memberDTO");
		if(mdb==null){
			return "/view/member/login/member_loginForm.jsp";
		}
		int dog_memcode = mdb.getMem_code();
		
		DogDao ddPro = DogDao.getInstance();		
		
		ArrayList<DogDataBean> dog_list = ddPro.getDogList(dog_memcode);
		request.setAttribute("DogList", dog_list);
		return "/view/member/mypage/myPageForm.jsp";

	}
}
