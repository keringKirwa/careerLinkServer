package com.career_link.kenya.services;

import com.career_link.kenya.entities.*;
import com.career_link.kenya.repositories.AuthRepository;
import com.career_link.kenya.security.JwtTokenProvider;
import com.career_link.kenya.utils.ApplicationConstants;
import com.career_link.kenya.utils.LoggingTypes;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

import static com.career_link.kenya.utils.ApplicationConstants.passwordEncoder;

@Service
@Transactional
@RequiredArgsConstructor
public class AuthenticationService {

    private final JwtTokenProvider jwtTokenProvider;
    private final AuthenticationManager authenticationManager;
    private final UserDetailsServiceImpl userDetailsServiceImpl;

    private final AuthRepository authRepository;


    public String signUp(SignUpRequest signUpRequest) {
        ApplicationUser applicationUser = ApplicationUser.builder()
                .username(signUpRequest.getFirstName())
                .emailAddress(signUpRequest.getEmailAddress())
                .password(passwordEncoder.encode(signUpRequest.getPassword()))
                .profileImageAddress("https://profile/image")
                .isAccountNonExpired(true)
                .isEnabled(true)
                .isCredentialsNonExpired(true)
                .isAccountNonLocked(true)
                .role(Role.USER)
                .build();
        authRepository.save(applicationUser);
        return "Registration Successful";


    }


    public SignInResponse signIn(SignInRequest signInRequest) {

        try {
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            signInRequest.getEmailAddress(),
                            signInRequest.getPassword()
                    )
            );

            Map<String, String> dummyClaims = new HashMap<>();
            dummyClaims.put("userId", "123");
            dummyClaims.put("role", Role.USER.toString());

            ApplicationUser applicationUser = authRepository.findByEmailAddress(signInRequest.getEmailAddress());
            var jwtToken = jwtTokenProvider.generateJwtToken(dummyClaims, applicationUser);

            return SignInResponse.builder()
                    .username(applicationUser.getUsername())
                    .profileImageAddress(applicationUser.getEmailAddress())
                    .jwtToken(jwtToken)
                    .message("Sign in successful")
                    .profileImageAddress(applicationUser.getProfileImageAddress())
                    .build();


        } catch (Exception e) {
            ApplicationConstants.LOG(e.getMessage(), LoggingTypes.ERROR); //TODO: SAVE THE LOGS FROM HERE TO THE DATABASE .
            e.printStackTrace();
            return null;

        }

    }
}
