package com.delivery.exception;

import lombok.NoArgsConstructor;

@NoArgsConstructor
public class IllegalParcelStatusChangeException extends RuntimeException {

    public IllegalParcelStatusChangeException(String message) {
        super(message);
    }
}
