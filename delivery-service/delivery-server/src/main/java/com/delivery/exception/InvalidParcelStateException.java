package com.delivery.exception;

import lombok.NoArgsConstructor;

@NoArgsConstructor
public class InvalidParcelStateException extends RuntimeException {

    public InvalidParcelStateException(String message) {
        super(message);
    }
}
