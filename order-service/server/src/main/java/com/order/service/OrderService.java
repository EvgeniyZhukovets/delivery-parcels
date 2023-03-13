package com.order.service;

import com.order.dto.request.CancelOrderRequest;
import com.order.dto.request.ChangeStatusRequest;
import com.order.dto.request.CreateOrderRequest;
import com.order.dto.request.GetOrdersRequest;
import com.order.dto.response.CreateOrderResponse;
import com.order.dto.response.GetOrdersResponse;

public interface OrderService {

    CreateOrderResponse createOrder(CreateOrderRequest request);

    void cancelOrder(CancelOrderRequest request);

    GetOrdersResponse getOrders(GetOrdersRequest request);

    GetOrdersResponse getAllOrders();

    void changeStatus(ChangeStatusRequest request);
}
