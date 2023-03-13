package com.gateway.dto.order.request;

public record CreateOrderRequest(Long userId, String startPoint, String destination) {
}
