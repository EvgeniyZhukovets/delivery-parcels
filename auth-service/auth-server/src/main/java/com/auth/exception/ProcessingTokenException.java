package com.auth.exception;

import lombok.NoArgsConstructor;

@NoArgsConstructor
public class ProcessingTokenException extends RuntimeException {

    public ProcessingTokenException(String message) {
        super(message);
    }
}
