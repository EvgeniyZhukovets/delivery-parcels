package com.order.controller;

import com.common.ResponseInfo;
import com.common.ResultCode;
import com.common.StandardResponse;
import com.order.dto.request.CancelOrderRequest;
import com.order.dto.request.ChangeStatusRequest;
import com.order.dto.request.CreateOrderRequest;
import com.order.dto.request.GetOrdersRequest;
import com.order.dto.response.CreateOrderResponse;
import com.order.dto.response.GetOrdersResponse;
import com.order.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/order")
@RequiredArgsConstructor
public class OrderController {

    private final OrderService service;

    @PostMapping("/create")
    public CreateOrderResponse createOrder(@RequestBody @Valid CreateOrderRequest request) {
        return service.createOrder(request);
    }

    @PostMapping("/cancel")
    public StandardResponse cancelOrder(@RequestBody @Valid CancelOrderRequest request) {
        service.cancelOrder(request);
        return new StandardResponse(ResponseInfo.getInstance(ResultCode.ASYNC_PROCESSING));
    }

    @GetMapping("/by-user")
    public GetOrdersResponse getOrders(@RequestBody @Valid GetOrdersRequest request) {
        return service.getOrders(request);
    }

    @PutMapping("/status")
    public StandardResponse changeStatus(@RequestBody @Valid ChangeStatusRequest request) {
        service.changeStatus(request);
        return new StandardResponse();
    }

    @GetMapping("/all")
    public GetOrdersResponse getAllOrders() {
        return service.getAllOrders();
    }
}
