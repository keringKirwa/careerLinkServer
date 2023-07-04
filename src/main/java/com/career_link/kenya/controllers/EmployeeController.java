package com.career_link.kenya.controllers;

import com.career_link.kenya.entities.EmployeeResponse;
import com.career_link.kenya.services.api_service.EmployeeClient;
import com.career_link.kenya.utils.ApplicationConstants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping(ApplicationConstants.ROOT_PATH)
public class EmployeeController {
    @Autowired
    private EmployeeClient employeeClient;

    @GetMapping(value = "/get-one-employee")
    public EmployeeResponse getOneEmployee() {

        /*
         * By this way , we are able to call  a function in an interface because its annotated with the
         * @GetExchange , and spring boot uses the WebClient configuration to create an implementation of the api funtion tha t
         * interacts with the server.
         */

        return employeeClient.getOneEmployee();

    }

}

