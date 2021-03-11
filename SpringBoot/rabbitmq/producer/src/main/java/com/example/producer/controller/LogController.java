package com.example.producer.controller;

import com.example.producer.helper.RabbitMQSender;
import com.example.producer.model.Person;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author SbrTa
 * @since 3/11/21  10:19 PM
 */

@RestController
public class LogController {

    private final RabbitMQSender sender;

    public LogController(RabbitMQSender sender) {
        this.sender = sender;
    }

    @GetMapping(value = "/")
    public String index() {
        return "Producer is running...";
    }

    @GetMapping(value = "/log")
    public String log(@RequestParam("name") String name,
                      @RequestParam("age") int age,
                      @RequestParam("gender") String gender) {
        Person person = new Person();
        person.setName(name);
        person.setAge(age);
        person.setGender(gender);
        sender.send(person);
        return person.toString() + " :: Logged successfully";
    }
}
