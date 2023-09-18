package project.game_project.login;

import javax.mail.*;
import javax.mail.internet.*;
import java.util.Random;
import java.util.Date;
import java.util.Properties;
import project.game_project.cache.Cashe;

public class Java_Mail{
    
    
    public String email_secces(){
        int[] random_arr = {0,1,2,3,4,5,6,7,8,9};
        Random random = new Random();
        String result = "";
        for(int i = 0; i < 8; i++){
            int random_int = random.nextInt(9);
            result+= ""+random_arr[random_int];
        }
        Cashe.getInstance().setEmail_succes(result);
        return result;
    }
    
    public void naverMailSend(String email) {
        Properties p = System.getProperties();
        p.put("mail.smtp.starttls.enable", "true");
        p.put("mail.smtp.host", "smtp.naver.com");
        p.put("mail.smtp.auth", "true");
        p.put("mail.smtp.port", "587");
 
        Authenticator auth = new MyAuthentication();
        Session session = Session.getDefaultInstance(p, auth);
        MimeMessage msg = new MimeMessage(session);
 
        try {
            msg.setSentDate(new Date());
            InternetAddress from = new InternetAddress();
            
            from = new InternetAddress("보내는 사람 <ty_ty123@naver.com>");
            msg.setFrom(from);
 
            InternetAddress to = new InternetAddress(email);
            msg.setRecipient(Message.RecipientType.TO, to);
 
            msg.setSubject("Java_Game 프로젝트 계정 인증", "UTF-8");
            msg.setText("이메일 인증 : "+email_secces(), "UTF-8");
            msg.setHeader("content-Type", "text/html");
 
            javax.mail.Transport.send(msg);
        } catch (AddressException addr_e){
            addr_e.printStackTrace();
        } catch (MessagingException msg_e){
            msg_e.printStackTrace();
        }
    }
    
    
    
class MyAuthentication extends Authenticator {
 
    PasswordAuthentication account;
 
    public MyAuthentication(){
        String id = "ty_ty123@naver.com";
        String pw = "k01056517823@";
        account = new PasswordAuthentication(id, pw);
    }
 
    public PasswordAuthentication getPasswordAuthentication(){
        return account;
    }
}
    
    
}