package com.gateway.controller;

import com.common.StandardResponse;
import com.gateway.clients.OrderFeignClient;
import com.gateway.dto.order.request.CancelOrderRequest;
import com.gateway.dto.order.request.ChangeStatusRequest;
import com.gateway.dto.order.request.CreateOrderRequest;
import com.gateway.dto.order.request.GetOrdersRequest;
import com.gateway.dto.order.response.CreateOrderResponse;
import com.gateway.dto.order.response.GetOrdersResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/order")
@RequiredArgsConstructor
@Log4j2
public class OrderController {

    private final OrderFeignClient feignClient;

    @PostMapping("/create")
    public CreateOrderResponse createOrder(@RequestBody CreateOrderRequest request) {
        log.info("createOrder called");
        return feignClient.createOrder(request);
    }

    @PostMapping("/cancel")
    public StandardResponse cancelOrder(@RequestBody CancelOrderRequest request) {
        log.info("cancelOrder called");
        return feignClient.cancelOrder(request);
    }

    @GetMapping("/by-user")
    public GetOrdersResponse getOrders(@RequestBody GetOrdersRequest request) {
        log.info("getOrders called");
        return feignClient.getOrders(request);
    }

    @PutMapping("/status")
    public StandardResponse changeStatus(@RequestBody ChangeStatusRequest request) {
        log.info("changeStatus called");
        return feignClient.changeStatus(request);
    }

    @GetMapping("/all")
    public GetOrdersResponse getAllOrders() {
        log.info("getAllOrders called");
        return feignClient.getAllOrders();
    }
}
