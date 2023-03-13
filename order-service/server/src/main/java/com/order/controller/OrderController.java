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
import lombok.extern.log4j.Log4j2;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/order")
@RequiredArgsConstructor
@Log4j2
public class OrderController {

    private final OrderService service;

    @PostMapping("/create")
    public CreateOrderResponse createOrder(@RequestBody @Valid CreateOrderRequest request) {
        log.info("createOrder called with request: {}", request);
        return service.createOrder(request);
    }

    @PostMapping("/cancel")
    public StandardResponse cancelOrder(@RequestBody @Valid CancelOrderRequest request) {
        log.info("cancelOrder called with request: {}", request);
        service.cancelOrder(request);
        return new StandardResponse(ResponseInfo.getInstance(ResultCode.ASYNC_PROCESSING));
    }

    @GetMapping("/by-user")
    public GetOrdersResponse getOrders(@RequestBody @Valid GetOrdersRequest request) {
        log.info("getOrders called with request: {}", request);
        return service.getOrders(request);
    }

    @PutMapping("/status")
    public StandardResponse changeStatus(@RequestBody @Valid ChangeStatusRequest request) {
        log.info("changeStatus called with request: {}", request);
        service.changeStatus(request);
        return new StandardResponse();
    }

    @GetMapping("/all")
    public GetOrdersResponse getAllOrders() {
        log.info("getAllOrders called");
        return service.getAllOrders();
    }
}
