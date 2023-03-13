package com.order.dto.request;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
public class CreateOrderRequest {

    @NotNull
    private Long userId;
    @NotBlank
    private String startPoint;
    @NotBlank
    private String destination;
}
