package com.abrishmokie.JSMS.service;

import com.abrishmokie.JSMS.DTO.TwoFactorBody;
import com.abrishmokie.JSMS.DTO.VerificationBody;
import com.abrishmokie.JSMS.Emailables.EmailVerificationEmailable;
import com.abrishmokie.JSMS.Emailables.Emailable;
import com.abrishmokie.JSMS.Emailables.TwoFactorEmailable;
import com.abrishmokie.JSMS.enums.TypeOfEmailEnum;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import lombok.extern.slf4j.Slf4j;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;


@Service
@Slf4j
public class EmailService {

    private final JavaMailSender mailSender;
    private final MustacheService mustacheService;

    public EmailService(JavaMailSender mailSender,MustacheService mustacheService){
        this.mailSender = mailSender;
        this.mustacheService = mustacheService;
    }

    public void sendVerificationToken(TypeOfEmailEnum type, VerificationBody body){
        EmailVerificationEmailable mailable = EmailVerificationEmailable.builder()
                .from(body.from())
                .fromEmail(body.fromEmail())
                .to(body.to())
                .subject(body.subject())
                .verificationToken(body.verificationToken())
                .build();


        Map<String,Object> context = new HashMap<>();
        context.put("purpose_1",type.getPurpose_v1());
        context.put("purpose_2",type.getPurpose_v2());
        context.put("tokenLink",mailable.getVerificationToken());
        try {
            String html = htmlTemplate(type.getFileName(), context);
            sendEmailWithHtml(mailable,html);
        }catch (Error | IOException | MessagingException e){
            throw new RuntimeException(e);
        }
    }

    public void sendTwoFactor(TypeOfEmailEnum type, TwoFactorBody body){
        TwoFactorEmailable mailable = TwoFactorEmailable.builder()
                .from(body.from())
                .fromEmail(body.fromEmail())
                .to(body.to())
                .subject(body.subject())
                .code(body.code())
                .expiryTime(body.expiryTime())
                .build();

        Map<String,Object> context = new HashMap<>();
        context.put("verificationCode",mailable.getCode());
        context.put("expiryTime",mailable.getExpiryTime());
        try {
            String html = htmlTemplate(type.getFileName(), context);
            sendEmailWithHtml(mailable,html);
        }catch (Error | IOException | MessagingException e){
            throw new RuntimeException(e);
        }
    }


    private String htmlTemplate(String templateName, Map<String,Object> context) throws IOException {
        return mustacheService.renderTemplate(templateName,context);
    }

    public void sendEmailWithHtml(Emailable emailable, String html) throws Error, MessagingException, IOException {
        System.out.println("\n testing html" + emailable);

        MimeMessage message = mailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message,true);


        helper.setFrom(emailable.getFromEmail() + "@abrishmokie.com",emailable.getFrom());
        helper.setTo(emailable.getTo());
        helper.setSubject(emailable.getSubject());
        helper.setText(html,true);

        mailSender.send(message);

    }

}
