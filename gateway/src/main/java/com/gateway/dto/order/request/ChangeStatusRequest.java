package com.gateway.dto.order.request;

public record ChangeStatusRequest(Long orderId, String orderStatus) {
}
