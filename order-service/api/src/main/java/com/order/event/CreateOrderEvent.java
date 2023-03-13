package com.order.event;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CreateOrderEvent implements Serializable {

    private String startPoint;
    private String destination;
    private Long orderId;
    private Long userId;
}
