package com.delivery.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum ParcelStatus {

    CREATED("CREATED"), ASSIGNED("ASSIGNED"), DELIVERING("DELIVERING"), DELIVERED("DELIVERED"), CANCELLED("CANCELLED");

    private String name;
}
