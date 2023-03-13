package com.auth.listener;

import com.auth.event.ChangeCourierStatusEvent;
import com.auth.service.CourierService;
import lombok.RequiredArgsConstructor;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class CourierStatusListener {

    private final CourierService service;

    @RabbitListener(queues = "changeCourierStatus")
    public void createOrder(ChangeCourierStatusEvent event) {
        service.changeCourierStatus(event);
    }
}
