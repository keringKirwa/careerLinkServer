package com.career_link.kenya.exception_handlers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ProblemDetail;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class NullPointerExceptionHandler {
    @ExceptionHandler
    public ProblemDetail onNullPointerException(NullPointerException nullPointerException){
        return ProblemDetail.forStatusAndDetail(HttpStatus.BAD_REQUEST, nullPointerException.getMessage());


    }


}