package com.career_link.kenya.controllers;

import com.career_link.kenya.entities.SignInRequest;
import com.career_link.kenya.entities.SignInResponse;
import com.career_link.kenya.entities.SignUpRequest;
import com.career_link.kenya.services.AuthenticationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequiredArgsConstructor
public class  AuthController {

    private final AuthenticationService authenticationService;


    @PostMapping("/auth/login")
    public ResponseEntity<SignInResponse> signIn(@RequestBody SignInRequest signInRequest) {
       SignInResponse signInResponse= authenticationService.signIn(signInRequest);
        return ResponseEntity.ok(signInResponse);

    }

    @PostMapping("/auth/register")
    public ResponseEntity<Object> register(@RequestBody SignUpRequest signUpRequest) {
        /*
         * No JWT is generated in the signUp.
         */

        try {

            System.out.println("sign up Request :: " + signUpRequest.toString());
            return ResponseEntity.status(HttpStatus.ACCEPTED).body("hello login successful...");

        } catch (BadCredentialsException e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid username or password");
        }

    }

}
