package com.auth.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum CourierStatus {

    AVAILABLE("AVAILABLE"), BUSY("BUSY");

    private String name;
}
