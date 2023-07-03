package com.career_link.kenya.controllers;

import com.career_link.kenya.utils.ApplicationConstants;
import com.career_link.kenya.utils.LoggingTypes;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping(ApplicationConstants.ROOT_PATH)
public class MessagesController {

    @GetMapping(value = "/test")
    public String testApp() {
        ApplicationConstants.LOG("APP TEST SUCCEEDED", LoggingTypes.INFO);
        return "success message";

    }
}

