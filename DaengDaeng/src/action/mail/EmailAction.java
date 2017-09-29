package action.mail;


import java.util.Properties;

import action.mail.*;

import javax.mail.*;
import javax.mail.internet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import action.CommandAction;

public class EmailAction implements CommandAction {//글목록 처리

    public String requestPro(HttpServletRequest request,
        HttpServletResponse response)throws Throwable {
    	request.setCharacterEncoding("utf-8");
    	String result="";
    	String from = "mimi226kr@gmail.com";
    	String to = request.getParameter("to_email");
    	String subject = "DaengDaeng.com 이메일 인증번호입니다.";
    	String authcode = request.getParameter("authcode");
    	String content="고객님의 인증번호는["+authcode+"]입니다. \n인증창에 입력후 가입을 진행하세요";
    	// 입력값 받음
    	System.out.println(to+content);
    	Properties p = new Properties(); // 정보를 담을 객체
    	 
    	p.put("mail.smtp.host","smtp.gmail.com"); // 네이버 SMTP
    	p.put("mail.smtp.port", "25");
    	p.put("mail.smtp.starttls.enable", "true");
    	p.put("mail.smtp.auth", "true");
    	p.put("mail.smtp.debug", "true");


    	p.setProperty("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
    	p.setProperty("mail.smtp.socketFactory.port", "465");
    	p.setProperty("mail.smtp.socketFactory.fallback", "false");
    	p.setProperty("mail.smtp.port", "465"); 



    	// SMTP 서버에 접속하기 위한 정보들
    	
    	try{
    		if(to!=null){
    	    Authenticator auth = new SMTPAuthenticatior();
    	    Session ses = Session.getInstance(p, auth);
    	     
    	    ses.setDebug(true);
    	     
    	    MimeMessage msg = new MimeMessage(ses); // 메일의 내용을 담을 객체
    	    msg.setSubject(subject); // 제목
    	     
    	    Address fromAddr = new InternetAddress(from);
    	    msg.setFrom(fromAddr); // 보내는 사람
    	   	System.out.println(from+"address:"+fromAddr);

    	    Address toAddr = new InternetAddress(to);
    	    msg.addRecipient(Message.RecipientType.TO, toAddr); // 받는 사람
    	     
    	    msg.setContent(content, "text/html;charset=UTF-8"); // 내용과 인코딩
    	     
    	    Transport.send(msg); // 전송
    	    }
    	    
    	} catch(Exception e){
    	    e.printStackTrace();
    	    //out.println("<script>alert('Send Mail Failed..');history.back();</script>");
    	    result="Send Mail Failed..";
    	    // 오류 발생시 뒤로 돌아가도록
    	   
    	}
    	  result="Send Mail Success!!";
    	request.setAttribute("authcode", authcode);
    	request.setAttribute("result", result);
    	
    	
        return "/view/member/join/sendMail.jsp";
    }
}
