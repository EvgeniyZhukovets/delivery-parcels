package com.order.dto.request;

import com.order.enums.OrderStatus;
import lombok.AllArgsConstructor;
import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
@AllArgsConstructor
public class ChangeStatusRequest {

    @NotNull
    private Long orderId;
    @NotNull
    private OrderStatus orderStatus;
}
