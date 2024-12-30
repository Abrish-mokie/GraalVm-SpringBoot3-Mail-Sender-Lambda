package com.abrishmokie.JSMS.Emailables;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

@EqualsAndHashCode(callSuper = true)
@Getter
@SuperBuilder
public class TwoFactorEmailable extends Emailable{
    private final String code;
    private final String expiryTime;

}
