package com.order.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.order.enums.OrderStatus;
import lombok.Builder;
import lombok.Data;

import java.util.Date;

@Data
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public class OrderDto {

    private Long id;
    private OrderStatus status;
    private Date createdTime;
    private Date lastModifiedTime;
    private String cancelReason;
}
