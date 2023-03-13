package com.auth.exception;

import lombok.NoArgsConstructor;

@NoArgsConstructor
public class InvalidCredentialsException extends RuntimeException {

    public InvalidCredentialsException(String message) {
        super(message);
    }
}
