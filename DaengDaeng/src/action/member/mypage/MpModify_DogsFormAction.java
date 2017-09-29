package action.member.mypage;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import action.CommandAction;
import model.dog.DogDao;
import model.dto.DogDataBean;
import model.dto.MemberDataBean;
import model.member.MemberDao;

public class MpModify_DogsFormAction implements CommandAction {// 글목록 처리
	public String requestPro(HttpServletRequest request, HttpServletResponse response) throws Throwable {
		
		String dog_code = request.getParameter("dog_code");
		
    	if (dog_code == null) {
    		dog_code = "0";
    		}
    	int dog_code02 = Integer.parseInt(dog_code);
    	
    	DogDao ddb = DogDao.getInstance();
    	ddb.getDog(dog_code02);
    	
    	request.setAttribute("dog_data", ddb.getDog(dog_code02));
    	request.setAttribute("dog_code",dog_code02);
    	
    	
		return "/view/member/mypage/mp_modify_dogsForm.jsp";
	}

}