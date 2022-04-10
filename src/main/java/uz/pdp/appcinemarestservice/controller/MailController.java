package uz.pdp.appcinemarestservice.controller;

import freemarker.template.TemplateException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import uz.pdp.appcinemarestservice.payload.MailDto;
import uz.pdp.appcinemarestservice.service.MailService;

import javax.mail.MessagingException;
import java.io.IOException;

// Nurkulov Nodirbek 4/4/2022  8:50 AM
@RestController
@RequestMapping("/api/email")
@RequiredArgsConstructor
public class MailController {

    private final MailService mailSenderService;

    @PostMapping
    public HttpEntity<?>sendEmail (@RequestBody MailDto mailDto){
        return mailSenderService.sendMessageWithAttachment(mailDto);
    }
}
