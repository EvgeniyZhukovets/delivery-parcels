package com.gateway.clients;

import com.common.StandardResponse;
import com.gateway.dto.order.request.CancelOrderRequest;
import com.gateway.dto.order.request.ChangeStatusRequest;
import com.gateway.dto.order.request.CreateOrderRequest;
import com.gateway.dto.order.request.GetOrdersRequest;
import com.gateway.dto.order.response.CreateOrderResponse;
import com.gateway.dto.order.response.GetOrdersResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name = "orderFeignClient", url = "${delivery.order.address}")
public interface OrderFeignClient {

    @PostMapping("/order/create")
    CreateOrderResponse createOrder(@RequestBody CreateOrderRequest request);

    @PostMapping("/order/cancel")
    StandardResponse cancelOrder(@RequestBody CancelOrderRequest request);

    @GetMapping("/order/by-user")
    GetOrdersResponse getOrders(@RequestBody GetOrdersRequest request);

    @PutMapping("/order/status")
    StandardResponse changeStatus(@RequestBody ChangeStatusRequest request);

    @GetMapping("/order/all")
    GetOrdersResponse getAllOrders();
}
