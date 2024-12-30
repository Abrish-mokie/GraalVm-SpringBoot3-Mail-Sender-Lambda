package com.abrishmokie.JSMS.Emailables;

import lombok.Getter;
import lombok.experimental.SuperBuilder;

@SuperBuilder
@Getter
public class Emailable {

    private final String fromEmail;
    private final String from;
    private final String to;
    private final String subject;

    public Emailable(String fromEmail, String from, String to, String subject) {
        this.fromEmail = fromEmail;
        this.from = from;
        this.to = to;
        this.subject = subject;
    }
}
