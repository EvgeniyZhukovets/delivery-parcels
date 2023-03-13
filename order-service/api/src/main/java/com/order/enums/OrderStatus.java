package com.order.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum OrderStatus {

    CREATED("CREATED"), PAID("PAID"), COMPLETED("COMPLETED"), CANCELLED("CANCELLED");

    private String name;
}
