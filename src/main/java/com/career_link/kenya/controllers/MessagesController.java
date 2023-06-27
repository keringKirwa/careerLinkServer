package com.career_link.kenya.controllers;

import com.career_link.kenya.entities.Message;
import com.career_link.kenya.producers.MessageProducer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class MessagesController {


    @Autowired
    private MessageProducer messageProducer;

    @PostMapping(value = "/test")
    public String testApp(@RequestBody Message message) {
        System.out.println("data received is here :" );
        messageProducer.publishTextMessage(message);
        return "success message";

    }
}

