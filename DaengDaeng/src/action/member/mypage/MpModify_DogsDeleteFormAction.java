package action.member.mypage;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import action.CommandAction;
import model.dog.DogDao;
import model.dto.DogDataBean;

public class MpModify_DogsDeleteFormAction implements CommandAction {
	public String requestPro(HttpServletRequest request, HttpServletResponse response) throws Throwable {

		request.setCharacterEncoding("UTF-8");
		String dog_code = request.getParameter("dog_code");

		if (dog_code == null) {
			dog_code = "0";
		}
		int dog_code02 = Integer.parseInt(dog_code);

		DogDao ddb = DogDao.getInstance();
		DogDataBean ddbPro = ddb.selectDog(dog_code02);

		request.setAttribute("dog_data", ddbPro);
		
		
		return "/view/member/mypage/mp_modify_dogsdeleteform.jsp";
	}
}
