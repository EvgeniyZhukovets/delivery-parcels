package com.order.exception;

import lombok.NoArgsConstructor;

@NoArgsConstructor
public class InvalidOrderStatusStateException extends RuntimeException {

    public InvalidOrderStatusStateException(String message) {
        super(message);
    }
}
