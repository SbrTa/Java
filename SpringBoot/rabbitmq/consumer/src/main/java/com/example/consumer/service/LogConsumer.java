package com.example.consumer.service;

import com.example.consumer.model.Person;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * @author SbrTa
 * @since 3/12/21  11:58 AM
 */

@Component
public class LogConsumer {

    @RabbitListener(queues = "testLogQueue")
    public void log(Object person){
        System.out.println("Consuming data :: START");
        System.out.println(person.toString());
        System.out.println("Consuming data :: END\n");
    }
}
