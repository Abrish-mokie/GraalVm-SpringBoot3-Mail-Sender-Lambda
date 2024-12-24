package com.abrishmokie.JSMS.controller;

import com.abrishmokie.JSMS.DTO.EmailVerificationBody;
import com.abrishmokie.JSMS.service.EmailService;
import jakarta.mail.MessagingException;
import jakarta.validation.Valid;
import lombok.Data;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import java.io.IOException;

@RestController
@Data
@Validated
@EnableWebMvc
public class EmailController {

    private final EmailService emailService;

    public EmailController(EmailService emailService) {
        this.emailService = emailService;
    }

    @PostMapping("/send-email")
    public ResponseEntity<String> sendEmail(@RequestBody @Valid EmailVerificationBody emailVerificationBody) {
        try {
            emailService.sendEmailWithHtml(emailVerificationBody);
            return ResponseEntity.ok().body("Email sent Successfully");
        }catch (Error | MessagingException | IOException e){
            return ResponseEntity.internalServerError().body(e.getMessage());
        }
    }


}
