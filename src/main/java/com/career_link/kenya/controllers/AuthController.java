package com.career_link.kenya.controllers;

import com.career_link.kenya.entities.SignInRequest;
import com.career_link.kenya.entities.SignInResponse;
import com.career_link.kenya.entities.SignUpRequest;
import com.career_link.kenya.services.AuthenticationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/api/v1/auth")
public class  AuthController {

    @Autowired
    AuthenticationService authenticationService;


    @PostMapping("/sign-in")
    public ResponseEntity<SignInResponse> signIn(@RequestBody SignInRequest signInRequest) {
       SignInResponse signInResponse= authenticationService.signIn(signInRequest);
        return ResponseEntity.ok(signInResponse);

    }

    @PostMapping("/sign-up")
    public ResponseEntity<Object> signUp(@RequestBody SignUpRequest signUpRequest) {
        try {

            // Process the sign-up request and save the user details

            System.out.println("Sign-up Request: " + signUpRequest.toString());
            return ResponseEntity.status(HttpStatus.ACCEPTED).body("Registration successful!");

        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error occurred during registration");
        }
    }





}
