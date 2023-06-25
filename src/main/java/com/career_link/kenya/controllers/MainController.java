package com.career_link.kenya.controllers;

import com.career_link.kenya.entities.Message;
import com.career_link.kenya.producers.MessageProducer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


@RestController
@CrossOrigin("*")
public class MainController {
    @Autowired
    private  MessageProducer messageProducer;

    @GetMapping(value = "/test")
    public String testApp(@RequestBody Message message) {
        System.out.println("data received is here :" );
        messageProducer.publishTextMessage(message);
        return "success message";

    }

}


