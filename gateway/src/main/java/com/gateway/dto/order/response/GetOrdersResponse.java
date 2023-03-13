package com.gateway.dto.order.response;

import com.common.ResponseInfo;
import com.gateway.dto.order.common.OrderDto;

import java.util.List;

public record GetOrdersResponse(List<OrderDto> orders, ResponseInfo responseInfo) {
}
