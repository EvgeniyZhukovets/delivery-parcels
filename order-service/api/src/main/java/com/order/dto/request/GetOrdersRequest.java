package com.order.dto.request;

import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
public class GetOrdersRequest {

    @NotNull
    private Long userId;
}
