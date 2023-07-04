package com.career_link.kenya.entities;

public record EmployeeResponse(String status, Employee data) {

    public record Employee(String id, String employee_name, String employee_salary, String employee_age, String profile_image) {
    }
}
