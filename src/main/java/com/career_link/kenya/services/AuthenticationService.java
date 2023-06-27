package com.career_link.kenya.services;

import com.career_link.kenya.entities.ApplicationUser;
import com.career_link.kenya.entities.SignInRequest;
import com.career_link.kenya.entities.SignInResponse;
import com.career_link.kenya.security.JwtTokenProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class AuthenticationService {

    private final JwtTokenProvider jwtTokenProvider;
    private final AuthenticationManager authenticationManager;
    private final UserDetailsServiceImpl userDetailsServiceImpl;


    public SignInResponse signIn(SignInRequest request) {

        try {
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            request.getEmailAddress(),
                            request.getPassword()
                    )
            );

        } catch (Exception e) {
            e.printStackTrace();
            System.out.println(e.getMessage()); //TODO: SAVE THE LOGS FROM HERE TO THE DATABASE .

        }


        ApplicationUser applicationUser = userDetailsServiceImpl.getDummyAppUser(request.getEmailAddress());
        System.out.println(applicationUser.toString());

        Map<String, String> claims = new HashMap<>();
        claims.put("userId", "123");
        claims.put("role", "admin");

        var jwtToken = jwtTokenProvider.generateJwtToken(claims, applicationUser);
        System.out.println(jwtToken);

        return SignInResponse.builder()
                .jwtToken(jwtToken)
                .username(applicationUser.getUsername())
                .message("Sign In Successful")
                .profileImageAddress(applicationUser.getProfileImageAddress())
                .build();
    }
}
