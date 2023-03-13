package com.delivery.config;

import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.amqp.core.Declarables;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


@Configuration
public class RabbitMqConfig {

    @Bean
    public MessageConverter messageConverter() {
        return new Jackson2JsonMessageConverter();
    }

    @Bean
    public AmqpTemplate template(ConnectionFactory connectionFactory) {
        RabbitTemplate template = new RabbitTemplate(connectionFactory);
        template.setMessageConverter(messageConverter());
        return template;
    }

    @Bean
    public Declarables declarables() {
        Queue assignCourier = new Queue("assignCourier", false);
        Queue changeOrderStatus = new Queue("changeOrderStatus", false);
        Queue parcelDelivered = new Queue("parcelDelivered", false);
        Queue changeCourierStatus = new Queue("changeCourierStatus", false);
        return new Declarables(
                assignCourier,
                changeOrderStatus,
                parcelDelivered,
                changeCourierStatus
        );
    }
}
