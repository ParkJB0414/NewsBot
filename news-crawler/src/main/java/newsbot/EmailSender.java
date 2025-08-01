package newsbot;

import java.util.Properties;

import jakarta.mail.Authenticator;
import jakarta.mail.Message;
import jakarta.mail.MessagingException;
import jakarta.mail.PasswordAuthentication;
import jakarta.mail.Session;
import jakarta.mail.Transport;
import jakarta.mail.internet.InternetAddress;
import jakarta.mail.internet.MimeMessage;

public class EmailSender {
    public static void sendEmail(String subject, String content) {
        final String username = "pjb9704@gmail.com";       // 본인의 Gmail
        final String password = "ergqvfyxlxwnzapr";        // 앱 비밀번호

        Properties props = new Properties();
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.port", "587");

        Session session = Session.getInstance(props, new Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(username, password);
            }
        });

        try {
            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(username));
            message.setRecipients(
                Message.RecipientType.TO,
                InternetAddress.parse("pjb9704@naver.com")
            );
            message.setSubject(subject);
            message.setText(content);

            Transport.send(message);
            System.out.println("이메일 전송 완료");

        } catch (MessagingException e) {
            e.printStackTrace();
        }
    }
}

