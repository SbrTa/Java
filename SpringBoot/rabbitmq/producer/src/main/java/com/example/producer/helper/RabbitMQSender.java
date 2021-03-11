package com.example.producer.helper;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

/**
 * @author SbrTa
 * @since 3/11/21  10:33 PM
 */

@Service
public class RabbitMQSender {
    private final RabbitTemplate rabbitTemplate;

    @Value("${app.rabbitmq.exchange}")
    private String exchangeName;
    @Value("${app.rabbitmq.queue}")
    private String queueName;
    @Value("${app.rabbitmq.routingkey}")
    private String routingKeyName;

    public RabbitMQSender(RabbitTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
    }

    public void send(Object obj) {
        System.out.println("(" + queueName + ") Add data to queue :: START");
        System.out.println("Object :: " + obj.toString());
        rabbitTemplate.convertAndSend(exchangeName, routingKeyName, obj);
        System.out.println("(" + queueName + ") Add data to queue :: END");
    }
}
