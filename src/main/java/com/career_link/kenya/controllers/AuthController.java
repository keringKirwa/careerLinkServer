package com.career_link.kenya.controllers;

import com.career_link.kenya.entities.SignInRequest;
import com.career_link.kenya.entities.SignInResponse;
import com.career_link.kenya.entities.SignUpRequest;
import com.career_link.kenya.services.AuthenticationService;
import com.career_link.kenya.utils.ApplicationConstants;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequiredArgsConstructor
@RequestMapping(ApplicationConstants.ROOT_PATH)
public class AuthController {

    private final AuthenticationService authenticationService;


    @PostMapping("/auth/sign-in")
    public ResponseEntity<SignInResponse> signIn(@RequestBody SignInRequest signInRequest) {
        SignInResponse signInResponse = authenticationService.signIn(signInRequest);
        return ResponseEntity.ok(signInResponse);

    }

    @PostMapping("/auth/sign-up")
    public ResponseEntity<Object> signUp(@RequestBody SignUpRequest signUpRequest) {
        try {
            String regMessage = authenticationService.signUp(signUpRequest);

            return ResponseEntity.status(HttpStatus.ACCEPTED).body(regMessage);

        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error occurred during registration");
        }
    }


}
