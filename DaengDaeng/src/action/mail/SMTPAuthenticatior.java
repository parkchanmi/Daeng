package action.mail;
import javax.mail.Authenticator;
import javax.mail.PasswordAuthentication;

public class SMTPAuthenticatior extends Authenticator{ 
    @Override
    protected PasswordAuthentication getPasswordAuthentication() {
    	return new PasswordAuthentication("mimi226kr@gmail.com","cksal5028");

    }
}

