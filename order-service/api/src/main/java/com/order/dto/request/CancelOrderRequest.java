package com.order.dto.request;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
public class CancelOrderRequest {

    @NotNull
    private Long orderId;
    @NotBlank
    private String reason;
}
