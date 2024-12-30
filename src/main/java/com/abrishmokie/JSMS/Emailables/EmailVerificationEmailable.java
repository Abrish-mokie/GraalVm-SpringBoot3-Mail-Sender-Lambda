package com.abrishmokie.JSMS.Emailables;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.experimental.SuperBuilder;

@EqualsAndHashCode(callSuper = true)
@Getter
@SuperBuilder
public class EmailVerificationEmailable extends Emailable {

    private final String verificationToken;

}
