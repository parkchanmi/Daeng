   package action;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import action.CommandAction;
import model.admin.AdminDao;

public class AdminMainAction implements CommandAction {//글목록 처리

    public String requestPro(HttpServletRequest request,
        HttpServletResponse response)throws Throwable {
    	request.setCharacterEncoding("UTF-8");
    	
    	AdminDao adPro = AdminDao.getInstance();// DB처리	
    	   
    	//회원구분 
    	int result1 = adPro.getCountnormal();
    	int result2 = adPro.getCountdogowner();
    	int result3 = adPro.getCountexpert();
    	
    	int sum1 = result1+result2+result3;
    	
    	double result01 = (result1/(double)sum1)*100;
    	double result02 = (result2/(double)sum1)*100;
    	double result03 = (result3/(double)sum1)*100;
    	
    	double re01 = Double.parseDouble( String.format( "%.2f" , result01 ) );
    	double re02 = Double.parseDouble( String.format( "%.2f" , result02 ) );
    	double re03 = Double.parseDouble( String.format( "%.2f" , result03 ) );
    
    	request.setAttribute("re01", re01); //일반회원 카운트
    	request.setAttribute("re02", re02); //견주회원 카운트
    	request.setAttribute("re03", re03);//전문가회원카운트
    	
    	
    	
    	
    	
    	
    	//유입경로
    	int visit1 = adPro.getCountsearch();//검색 카운트
    	int visit2 = adPro.getCountfriend();//지인추천 카운트
    	int visit3 = adPro.getCountad();//광고카운트
    	int visit4 = adPro.getCountsns();//SNS 카운트
    	int visit5 = adPro.getCountetc();//기타 카운트
    	//System.out.println("visit2"+visit2);
    	
    	//double visit12 = visit1+visit2;
    	//System.out.println("visit12"+visit12);
    	
    	int sum2 = visit1+visit2+visit3+visit4+visit5;
    			
    	double visit01 = (visit1/(double)sum2)*100;
    	double visit02 = (visit2/(double)sum2)*100;
    	double visit03 = (visit3/(double)sum2)*100;
    	double visit04 = (visit4/(double)sum2)*100;
    	double visit05 = (visit5/(double)sum2)*100;
    	//System.out.println("visit02"+visit02);
    	//System.out.println("visit03"+visit03);
    	double vi01 = Double.parseDouble( String.format( "%.2f" , visit01 ) );
    	double vi02 = Double.parseDouble( String.format( "%.2f" , visit02 ) );
    	double vi03 = Double.parseDouble( String.format( "%.2f" , visit03 ) );
    	double vi04 = Double.parseDouble( String.format( "%.2f" , visit04 ) );
    	double vi05 = Double.parseDouble( String.format( "%.2f" , visit05 ) );
    	request.setAttribute("vi01", vi01); 
    	request.setAttribute("vi02", vi02); 
    	request.setAttribute("vi03", vi03); 
    	request.setAttribute("vi04", vi04); 
    	request.setAttribute("vi05", vi05); 
    	
    	//회원 연령,성별
    	int userinfo1 = adPro.getUserinfo("남", "10대");
    	int userinfo2 = adPro.getUserinfo("여", "10대");
    	int userinfo3 = adPro.getUserinfo("남", "20대");
    	int userinfo4 = adPro.getUserinfo("여", "20대");
    	System.out.println(userinfo4);
    	int userinfo5 = adPro.getUserinfo("남", "30대");
    	System.out.println(userinfo5);
    	int userinfo6 = adPro.getUserinfo("여", "30대");
    	int userinfo7 = adPro.getUserinfo("남", "40대");
    	int userinfo8 = adPro.getUserinfo("여", "40대");
    	int userinfo9 = adPro.getUserinfo("남", "50대이상");
    	int userinfo10 = adPro.getUserinfo("여", "50대이상");
    	
    	int sum3 = userinfo1+userinfo2+userinfo3+userinfo4+userinfo5+userinfo6+userinfo7+userinfo8+userinfo9+userinfo10;
    	
    	double userinfo01 = (userinfo1/(double)sum3)*100;
    	double userinfo02 = (userinfo2/(double)sum3)*100;
    	double userinfo03 = (userinfo3/(double)sum3)*100;
    	double userinfo04 = (userinfo4/(double)sum3)*100;
    	double userinfo05 = (userinfo5/(double)sum3)*100;
    	double userinfo06 = (userinfo6/(double)sum3)*100;
    	double userinfo07 = (userinfo7/(double)sum3)*100;
    	double userinfo08 = (userinfo8/(double)sum3)*100;
    	double userinfo09 = (userinfo9/(double)sum3)*100;    
    	double userinfo010 = (userinfo10/(double)sum3)*100; 
    	
    	double uinfo01 = Double.parseDouble( String.format( "%.2f" , userinfo01 ) );
    	double uinfo02 = Double.parseDouble( String.format( "%.2f" , userinfo02 ) );
    	double uinfo03 = Double.parseDouble( String.format( "%.2f" , userinfo03 ) );
    	double uinfo04 = Double.parseDouble( String.format( "%.2f" , userinfo04 ) );
    	double uinfo05 = Double.parseDouble( String.format( "%.2f" , userinfo05 ) );
    	double uinfo06 = Double.parseDouble( String.format( "%.2f" , userinfo06 ) );
    	double uinfo07 = Double.parseDouble( String.format( "%.2f" , userinfo07 ) );
    	double uinfo08 = Double.parseDouble( String.format( "%.2f" , userinfo08 ) );
    	double uinfo09 = Double.parseDouble( String.format( "%.2f" , userinfo09 ) );
    	double uinfo010 = Double.parseDouble( String.format( "%.2f" , userinfo010 ) );
    	
    	request.setAttribute("uinfo01", uinfo01); 
    	request.setAttribute("uinfo02", uinfo02);
    	request.setAttribute("uinfo03", uinfo03);
    	request.setAttribute("uinfo04", uinfo04);
    	request.setAttribute("uinfo05", uinfo05);
    	request.setAttribute("uinfo06", uinfo06);
    	request.setAttribute("uinfo07", uinfo07);
    	request.setAttribute("uinfo08", uinfo08);
    	request.setAttribute("uinfo09", uinfo09);
    	request.setAttribute("uinfo010", uinfo010);
    	
    	return "admin_main.jsp"; 
    }
}
