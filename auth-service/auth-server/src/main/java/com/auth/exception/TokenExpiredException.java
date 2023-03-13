package com.auth.exception;

import lombok.NoArgsConstructor;

@NoArgsConstructor
public class TokenExpiredException extends RuntimeException {

    public TokenExpiredException(String message) {
        super(message);
    }
}
