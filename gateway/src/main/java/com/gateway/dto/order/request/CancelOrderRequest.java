package com.gateway.dto.order.request;

public record CancelOrderRequest(Long orderId, String reason) {
}
