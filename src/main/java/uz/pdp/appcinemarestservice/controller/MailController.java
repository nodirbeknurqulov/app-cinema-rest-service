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
    public HttpEntity<?> sendEmil(@RequestBody MailDto mailDto) throws MessagingException, TemplateException, IOException {
        //1. oddiy text xabar yuboradi
//        return mailSenderService.sendMail(mailDto);
        //2. oddiy text xabar bilan birga file yuboradi
//        return mailSenderService.sendMessageWithAttachment(mailDto);
        //3. template email yuborish
//        return mailSenderService.sendEmailWithTemplate(mailDto);
        //4. sendEmailHtml
        return mailSenderService.sendEmailWithTemplate(mailDto);
    }
}
