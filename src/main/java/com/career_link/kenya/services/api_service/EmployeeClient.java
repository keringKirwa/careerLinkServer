package com.career_link.kenya.services.api_service;

import com.career_link.kenya.entities.EmployeeResponse;
import org.springframework.web.service.annotation.GetExchange;
import org.springframework.web.service.annotation.HttpExchange;

@HttpExchange(url = "/employee/1",accept = "application/json", contentType = "application/json" )
public interface EmployeeClient {
     @GetExchange
     EmployeeResponse getOneEmployee();

}
