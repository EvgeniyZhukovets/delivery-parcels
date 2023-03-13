package com.order.mapper;

import com.order.dto.OrderDto;
import com.order.entity.Order;

import java.util.List;

public class OrderMapper {

    public static List<OrderDto> mapEntityToOrderDto(List<Order> entities) {
        return entities.stream()
                .map(entity -> OrderDto.builder()
                        .id(entity.getId())
                        .status(entity.getStatus())
                        .cancelReason(entity.getCancelReason())
                        .createdTime(entity.getCreatedTime())
                        .lastModifiedTime(entity.getLastModifiedTime())
                        .build())
                .toList();
    }
}
