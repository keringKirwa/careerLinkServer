package com.career_link.kenya.services;


import com.career_link.kenya.entities.ApplicationUser;
import com.career_link.kenya.entities.Role;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    @Override
    public UserDetails loadUserByUsername(String emailAddress) throws UsernameNotFoundException {
        System.out.println("the login function was called ....");

        ApplicationUser appUser = new ApplicationUser();
        return ApplicationUser.builder()
                .username("kelvin kering")
                .emailAddress("admin@gmail.com")
                .password("password")
                .role(Role.ADMIN)
                .id(100)
                .build();
    }
}
