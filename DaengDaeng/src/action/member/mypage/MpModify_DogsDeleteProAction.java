package action.member.mypage;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import action.CommandAction;
import model.dog.DogDao;
import model.dto.DogDataBean;

public class MpModify_DogsDeleteProAction implements CommandAction {
	public String requestPro(HttpServletRequest request, HttpServletResponse response) throws Throwable {

request.setCharacterEncoding("UTF-8");
		
		String dog_name = request.getParameter("dog_name");
		String dog_kind = request.getParameter("dog_kind");
		String dog_gender = request.getParameter("dog_gender");
		String dog_age = request.getParameter("dog_age");
		String file_code = request.getParameter("file_code");
		String dog_code = request.getParameter("dog_code");
		
    	if (file_code == null) {
    		file_code = "0";
    		}
    	int file_code02 = Integer.parseInt(file_code);
	    
    	if (dog_code == null) {
    		dog_code = "0";
    		}
    	int dog_code02 = Integer.parseInt(dog_code);
    	
    	DogDao ddb = DogDao.getInstance();    	
    	int result = ddb.deleteDog(dog_code02);
 
    	request.setAttribute("result",result);
		
    	return "/view/member/mypage/myPagePro.jsp";
	}
}
