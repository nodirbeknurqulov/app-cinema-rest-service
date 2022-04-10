package uz.pdp.appcinemarestservice.service;

import freemarker.template.Configuration;
import freemarker.template.TemplateException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.springframework.util.ResourceUtils;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;
import uz.pdp.appcinemarestservice.entity.Ticket;
import uz.pdp.appcinemarestservice.payload.MailDto;
import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.StringWriter;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class MailService {

    private final JavaMailSender mailSender;
    private final TemplateEngine templateEngine;

    public HttpEntity<?> sendMail(MailDto mailDto) {
        SimpleMailMessage simpleMailMessage = new SimpleMailMessage();
        simpleMailMessage.setFrom("nodirbeknurqulov096@gmail.com");
        simpleMailMessage.setTo(mailDto.getTo());
        simpleMailMessage.setSubject("Hello, B7!!!");
        simpleMailMessage.setText(mailDto.getMessage());

        mailSender.send(simpleMailMessage);
        return ResponseEntity.ok("Email successfully sent!");
    }

    public HttpEntity<?> sendMessageWithAttachment(MailDto mailDto) {

        MimeMessage message = mailSender.createMimeMessage();
        MimeMessageHelper helper = null;
        try {
            helper = new MimeMessageHelper(message, true);

            helper.setFrom("nodirbeknurqulov096@gmail.com");
            helper.setTo(mailDto.getTo());
            helper.setSubject(mailDto.getSubject());
            helper.setText(mailDto.getMessage(), "<h2>This is picture of Tashkent</h2>");

            File file = ResourceUtils.getFile("D:\\Java Modules\\5-modul\\app-cinema-rest-service\\src\\main\\resources\\static\\java.jpg");
            helper.addAttachment("java.jpg", file);

            mailSender.send(message);
            return ResponseEntity.ok("Email successfully sent with attachment!");
        } catch (MessagingException | FileNotFoundException e) {
            e.printStackTrace();
        }
        return ResponseEntity.ok("Error");
    }

    public HttpEntity<?> sendEmailWithTemplate(MailDto mailDto, List<Ticket> ticketList) throws MessagingException {
        MimeMessage message = mailSender.createMimeMessage();
        try {
            MimeMessageHelper helper = new MimeMessageHelper(message, true);
            Map<String, Object> objectMap = new HashMap<>();
            objectMap.put("ticketList",ticketList);

            Context context = new Context();
            context.setVariables(objectMap);
            String indexHtml = templateEngine.process("email-template", context);

            helper.setFrom("nodirbeknurqulov096@gmail.com");
            helper.setText(indexHtml, true);
            mailSender.send(message);
            return ResponseEntity.ok("Email successfully sent!!!");

        } catch (MessagingException e) {
            e.printStackTrace();
        }
        return ResponseEntity.ok("Error for sending email!!!");
    }
}
