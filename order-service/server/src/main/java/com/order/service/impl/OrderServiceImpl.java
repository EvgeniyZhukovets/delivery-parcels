package com.order.service.impl;

import com.common.ResponseInfo;
import com.order.dto.OrderDto;
import com.order.dto.request.CancelOrderRequest;
import com.order.dto.request.ChangeStatusRequest;
import com.order.dto.request.CreateOrderRequest;
import com.order.dto.request.GetOrdersRequest;
import com.order.dto.response.CreateOrderResponse;
import com.order.dto.response.GetOrdersResponse;
import com.order.entity.Order;
import com.order.enums.OrderStatus;
import com.order.event.CreateOrderEvent;
import com.order.exception.InvalidOrderStatusStateException;
import com.order.exception.OrderNotFoundException;
import com.order.mapper.OrderMapper;
import com.order.repository.OrderRepository;
import com.order.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.orm.ObjectOptimisticLockingFailureException;
import org.springframework.retry.annotation.Retryable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

import static com.common.ResultCode.ASYNC_PROCESSING;
import static com.order.enums.OrderStatus.*;

@Service
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService {

    private final OrderRepository repository;
    private final RabbitTemplate rabbitTemplate;

    @Override
    @Transactional
    @Retryable(value = ObjectOptimisticLockingFailureException.class, maxAttempts = 5)
    public CreateOrderResponse createOrder(CreateOrderRequest request) {
        Order order = Order.builder().status(CREATED).userId(request.getUserId()).build();
        Order savedOrder = repository.save(order);
        CreateOrderEvent event = CreateOrderEvent.builder()
                .orderId(savedOrder.getId())
                .startPoint(request.getStartPoint())
                .destination(request.getDestination())
                .userId(request.getUserId())
                .build();
        rabbitTemplate.convertAndSend("createOrder", event);
        return CreateOrderResponse.builder()
                .id(savedOrder.getId())
                .responseInfo(ResponseInfo.getInstance(ASYNC_PROCESSING))
                .build();
    }

    @Override
    @Transactional
    @Retryable(value = ObjectOptimisticLockingFailureException.class, maxAttempts = 5)
    public void cancelOrder(CancelOrderRequest request) {
        Optional<Order> orderOptional = repository.findById(request.getOrderId());
        if (orderOptional.isEmpty()) {
            throw new OrderNotFoundException();
        }
        Order order = orderOptional.get();
        order.setStatus(CANCELLED);
        order.setCancelReason(request.getReason());
        repository.save(order);
        rabbitTemplate.convertAndSend("cancelOrder", order.getId());

    }

    @Override
    public GetOrdersResponse getOrders(GetOrdersRequest request) {
        List<Order> orders = repository.findAllByUserIdOrderByCreatedTime(request.getUserId());
        List<OrderDto> orderDtoList = OrderMapper.mapEntityToOrderDto(orders);
        return new GetOrdersResponse(orderDtoList);
    }

    @Override
    public GetOrdersResponse getAllOrders() {
        List<Order> orders = repository.findAll();
        List<OrderDto> orderDtoList = OrderMapper.mapEntityToOrderDto(orders);
        return new GetOrdersResponse(orderDtoList);
    }

    @Override
    @Transactional
    @Retryable(value = ObjectOptimisticLockingFailureException.class, maxAttempts = 5)
    public void changeStatus(ChangeStatusRequest request) {
        Optional<Order> orderOptional = repository.findById(request.getOrderId());
        if (orderOptional.isEmpty()) {
            throw new OrderNotFoundException();
        }
        Order order = orderOptional.get();
        if (!isChangeAllowed(order.getStatus(), request.getOrderStatus())) {
            throw new InvalidOrderStatusStateException();
        }
        order.setStatus(request.getOrderStatus());
        repository.save(order);
        if (CANCELLED == request.getOrderStatus()) {
            rabbitTemplate.convertAndSend("cancelOrder", order.getId());
        }
    }

    private boolean isChangeAllowed(OrderStatus from, OrderStatus to) {
        if (CREATED == from) {
            return CANCELLED == to || PAID == to || COMPLETED == to;
        }
        if (PAID == from) {
            return CANCELLED == to || COMPLETED == to;
        }
        return false;
    }
}
