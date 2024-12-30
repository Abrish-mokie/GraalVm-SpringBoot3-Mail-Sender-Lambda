package com.abrishmokie.JSMS.enums;

import lombok.Getter;
import org.springframework.stereotype.Component;

@Getter
public enum TypeOfEmailEnum {
    EMAIL_VERIFICATION("complete your email verification","confirm your email","EmailTokenVerification.mustache"),
    PASSWORD_VERIFICATION("complete your password reset","reset your password","EmailTokenVerification.mustache"),
    TWO_FACTOR_AUTHENTICATION("","","TwoFactorAuthentication.mustache");

    private final String purpose_v1;
    private final String purpose_v2;
    private final String fileName;

    TypeOfEmailEnum(String purpose_v1, String purpose_v2, String fileName){
        this.purpose_v1 = purpose_v1;
        this.purpose_v2 = purpose_v2;
        this.fileName = fileName;
    }

}
