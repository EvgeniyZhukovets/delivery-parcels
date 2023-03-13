package com.delivery.exception;

import lombok.NoArgsConstructor;

@NoArgsConstructor
public class ParcelNotFoundException extends RuntimeException {

    public ParcelNotFoundException(String message) {
        super(message);
    }
}
