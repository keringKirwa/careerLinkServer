package com.career_link.kenya.services;

import com.career_link.kenya.entities.ApplicationUser;
import com.career_link.kenya.entities.SignInRequest;
import com.career_link.kenya.entities.SignInResponse;
import com.career_link.kenya.security.JwtTokenProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthenticationService {

    @Autowired
    PasswordEncoder passwordEncoder;
    @Autowired JwtTokenProvider jwtTokenProvider;
    @Autowired AuthenticationManager authenticationManager;
    @Autowired UserDetailsServiceImpl userDetailsServiceImpl;


    public SignInResponse signIn(SignInRequest request) {

        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.getEmailAddress(),
                        request.getPassword()
                )
        );
        ApplicationUser applicationUser = userDetailsServiceImpl.getDummyAppUser(request.getEmailAddress());

        var jwtToken = jwtTokenProvider.generateJwtToken(null, applicationUser);

        return SignInResponse.builder()
                .jwtToken(jwtToken)
                .username(applicationUser.getUsername())
                .message("Sign In Successful")
                .profileImageAddress(applicationUser.getProfileImageAddress())
                .build();
    }
}
