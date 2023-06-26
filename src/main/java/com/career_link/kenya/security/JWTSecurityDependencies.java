package com.career_link.kenya.security;

import com.career_link.kenya.services.UserDetailsServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

/**
 * Allow access to the register and  the login page ... , then all the other pages the users must  be authenticated.
 */

@Configuration
public class JWTSecurityDependencies {
    @Autowired
    UserDetailsServiceImpl userDetailsService;
    @Autowired PasswordEncoder passwordEncoder;

    @Bean
    public AuthenticationProvider OnCreateAuthenticationProvider() {

        DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
        provider.setUserDetailsService(userDetailsService);
        provider.setPasswordEncoder(passwordEncoder);
        return provider;
    }

    @Bean
    public AuthenticationManager OnCreateauthenticationManager(@Lazy AuthenticationConfiguration authenticationConfiguration) throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }


    @Bean
    public PasswordEncoder OnCreatePasswordEncoder() {
        return new BCryptPasswordEncoder();
    }


}