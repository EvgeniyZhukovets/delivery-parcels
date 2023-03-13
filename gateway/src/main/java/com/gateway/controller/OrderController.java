package com.gateway.controller;

import com.common.ResponseInfo;
import com.common.ResultCode;
import com.common.StandardResponse;
import com.gateway.clients.OrderFeignClient;
import com.gateway.dto.order.request.CancelOrderRequest;
import com.gateway.dto.order.request.ChangeStatusRequest;
import com.gateway.dto.order.request.CreateOrderRequest;
import com.gateway.dto.order.request.GetOrdersRequest;
import com.gateway.dto.order.response.CreateOrderResponse;
import com.gateway.dto.order.response.GetOrdersResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/order")
@RequiredArgsConstructor
public class OrderController {

    private final OrderFeignClient feignClient;

    @PostMapping("/create")
    public CreateOrderResponse createOrder(@RequestBody CreateOrderRequest request) {
        return feignClient.createOrder(request);
    }

    @PostMapping("/cancel")
    public StandardResponse cancelOrder(@RequestBody CancelOrderRequest request) {
        feignClient.cancelOrder(request);
        return new StandardResponse(ResponseInfo.getInstance(ResultCode.ASYNC_PROCESSING));
    }

    @GetMapping("/by-user")
    public GetOrdersResponse getOrders(@RequestBody GetOrdersRequest request) {
        return feignClient.getOrders(request);
    }

    @PutMapping("/status")
    public StandardResponse changeStatus(@RequestBody ChangeStatusRequest request) {
        feignClient.changeStatus(request);
        return new StandardResponse();
    }

    @GetMapping("/all")
    public GetOrdersResponse getAllOrders() {
        return feignClient.getAllOrders();
    }
}
