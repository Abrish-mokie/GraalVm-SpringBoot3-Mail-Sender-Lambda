package com.abrishmokie.JSMS.DTO;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

public record TwoFactorBody(
        @NotNull(message = "fromEmail Cannot be null")
        @NotEmpty(message = "fromEmail Cannot be empty")
        String fromEmail,
        @NotNull(message = "From cannot be null")
        @NotEmpty(message = "From cannot be empty")
        String from,
        @NotNull(message = "To cannot be null")
        @NotEmpty(message = "To cannot be empty")
        String to,
        @NotNull(message = "Subject cannot be null")
        @NotEmpty(message = "Subject cannot be empty")
        String subject,
        @NotNull(message = "Two factor code cannot be null")
        @NotEmpty(message = "Two factor code cannot be empty")
        String code,
        @NotNull(message = "Expiray time cannot be null")
        @NotEmpty(message = "Expiray time cannot be empty")
        String expiryTime
        
) {
}
