package com.gateway.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum Role {

    USER("USER"), ADMIN("ADMIN"), COURIER("COURIER");

    private String name;
}
