package com.career_link.kenya.services.api_service;

import com.career_link.kenya.entities.EmployeeResponse;
import org.springframework.web.service.annotation.GetExchange;
import org.springframework.web.service.annotation.HttpExchange;

@HttpExchange("/employee/1")
public interface EmployeeApiService {
     @GetExchange
     EmployeeResponse getOneEmployee();

}
