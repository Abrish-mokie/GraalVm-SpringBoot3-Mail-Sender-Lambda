package com.abrishmokie.JSMS.controller;

import com.abrishmokie.JSMS.DTO.TwoFactorBody;
import com.abrishmokie.JSMS.DTO.VerificationBody;
import com.abrishmokie.JSMS.enums.TypeOfEmailEnum;
import com.abrishmokie.JSMS.service.EmailService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

@RestController
@Validated
@EnableWebMvc
@RequiredArgsConstructor
public class EmailController {

    private final EmailService emailService;

    @PostMapping("/email-verification")
    public ResponseEntity<String> sendEmailVerification(@RequestBody @Valid VerificationBody verificationBody) {
        try {
            emailService.sendVerificationToken(TypeOfEmailEnum.EMAIL_VERIFICATION,verificationBody);
            return ResponseEntity.ok().body("Email sent Successfully");
        }catch (Error e){
            return ResponseEntity.internalServerError().body(e.getMessage());
        }
    }

    @PostMapping("/password-verification")
    public ResponseEntity<String> sendPasswordVerification(@RequestBody @Valid VerificationBody verificationBody) {
        try {
            emailService.sendVerificationToken(TypeOfEmailEnum.PASSWORD_VERIFICATION,verificationBody);
            return ResponseEntity.ok().body("Email sent Successfully");
        }catch (Error e){
            return ResponseEntity.internalServerError().body(e.getMessage());
        }
    }

    @PostMapping("/two-factor")
    public ResponseEntity<String> sendTwoFactorAuthentication(@RequestBody @Valid TwoFactorBody verificationBody){
        try {
            emailService.sendTwoFactor(TypeOfEmailEnum.TWO_FACTOR_AUTHENTICATION,verificationBody);
            return ResponseEntity.ok().body("Email sent Successfully");
        }catch (Error e){
            return ResponseEntity.internalServerError().body(e.getMessage());
        }
    }


}
