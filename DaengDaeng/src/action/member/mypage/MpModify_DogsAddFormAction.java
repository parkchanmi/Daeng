package action.member.mypage;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import action.CommandAction;
import model.dog.DogDao;
import model.dto.DogDataBean;
import model.dto.MemberDataBean;

public class MpModify_DogsAddFormAction  implements CommandAction {
	public String requestPro(HttpServletRequest request, HttpServletResponse response) throws Throwable {
		
		request.setCharacterEncoding("UTF-8");
		/*HttpSession session = request.getSession();
		MemberDataBean mdb = (MemberDataBean) session.getAttribute("memberDTO");*/
		
		/*int dog_memcode = mdb.getMem_code();*/
		
	/*	DogDao ddb = DogDao.getInstance();
    	DogDataBean ddbPro = ddb.selectDogMem(dog_memcode);
    	
    	request.setAttribute("dog_data",ddbPro);*/
		/*
		System.out.println("dog_memcodeForm : "+ dog_memcode);
    	*/
		return "/view/member/mypage/mp_modify_dogsaddform.jsp";
	}
}
