package teamproject.cs5.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;
import teamproject.cs5.models.User;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

@Service
public class MailerService {
    private final JavaMailSender mailSender;
    private final TemplateEngine templateEngine;
    private final String host = "http://212.191.91.13:8083/api/confirmationToken";

    @Autowired
    public MailerService(JavaMailSender mailSender, TemplateEngine templateEngine) {
        this.mailSender = mailSender;
        this.templateEngine = templateEngine;
    }

    @Async
    public void sendVerificationMail(String token, User user){
        try{
            MimeMessage mimeMessage = mailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, "utf-8");
            Context context = new Context();
            String welcome = "Hello " + user.getName() + " " + user.getSurname();
            String link = host + "/" + token;
            context.setVariable("welcome", welcome);
            context.setVariable("link", link);
            String body = templateEngine.process("template", context);
            helper.setText(body, true);
            helper.setTo(user.getEmail());
            helper.setSubject("Confirm your email");
            helper.setFrom("cs5@teamproject.pl");
            mailSender.send(mimeMessage);
        }catch (MessagingException e){
            throw new IllegalStateException("failed to send email");
        }

    }
    @Async
    public void sendMail(String to,String subject, String content){

        try{
            MimeMessage mimeMessage = mailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, "utf-8");
            helper.setText(content);
            helper.setTo(to);
            helper.setSubject(subject);
            helper.setFrom("cs5@teamproject.pl");
            mailSender.send(mimeMessage);
        }catch (MessagingException e){
            throw new IllegalStateException("failed to send email");
        }    }
}
