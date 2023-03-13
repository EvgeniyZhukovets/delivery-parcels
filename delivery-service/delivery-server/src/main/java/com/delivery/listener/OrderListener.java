package com.delivery.listener;

import com.delivery.event.CreateOrderEvent;
import com.delivery.service.ParcelService;
import lombok.RequiredArgsConstructor;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class OrderListener {

    private final ParcelService service;

    @RabbitListener(queues = "createOrder")
    public void createOrder(CreateOrderEvent event) {
        service.createParcel(event);
    }

    @RabbitListener(queues = "cancelOrder")
    public void cancelOrder(Long orderId) {
        service.cancelParcel(orderId);
    }
}
