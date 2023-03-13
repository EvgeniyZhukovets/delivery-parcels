package com.order.listener;

import com.order.dto.request.ChangeStatusRequest;
import com.order.enums.OrderStatus;
import com.order.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class ParcelListener {

    private final OrderService service;

    @RabbitListener(queues = "parcelDelivered")
    public void parcelDelivered(Long orderId) {
        ChangeStatusRequest request = new ChangeStatusRequest(orderId, OrderStatus.COMPLETED);
        service.changeStatus(request);
    }
}
