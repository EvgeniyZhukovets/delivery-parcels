package com.delivery.exception;

import lombok.NoArgsConstructor;

@NoArgsConstructor
public class InvalidParcelStatusStateException extends RuntimeException {

    public InvalidParcelStatusStateException(String message) {
        super(message);
    }
}
