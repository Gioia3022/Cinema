package Cinema;

import javax.mail.Session;
import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Properties;
public class Mail {
    public static void send(String from,String pwd,String to,String sub,String msg){
        //Propriétés
        Properties p = new Properties();
        p.put("mail.smtp.host", "smtp.gmail.com");
        p.put("mail.smtp.socketFactory.port", "465");
        p.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
        p.put("mail.smtp.auth", "true");
        p.put("mail.smtp.port", "465");
        //Session
        javax.mail.Session s = Session.getDefaultInstance(p,
                new javax.mail.Authenticator() {
                    protected PasswordAuthentication getPasswordAuthentication() {
                        return new PasswordAuthentication(from, pwd);
                    }
                });
        //composer le message
        try {
            MimeMessage m = new MimeMessage(s);
            m.setFrom(new InternetAddress(from));
            m.setRecipients(
                    Message.RecipientType.TO,
                    InternetAddress.parse(to));
            m.setSubject(sub);
            m.setText(msg);
            //envoyer le message
            Transport.send(m);
            System.out.println("Message envoyé avec succès");
        } catch (MessagingException e) {
            e.printStackTrace();
        }
    }
}
