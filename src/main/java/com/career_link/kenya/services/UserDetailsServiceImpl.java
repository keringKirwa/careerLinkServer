package com.career_link.kenya.services;

import com.career_link.kenya.entities.ApplicationUser;
import com.career_link.kenya.entities.Role;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import static com.career_link.kenya.utils.ApplicationConstants.passwordEncoder;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {


    @Override
    public UserDetails loadUserByUsername(String emailAddress) throws UsernameNotFoundException {
        System.out.println("The login function was called ....");

        return ApplicationUser.builder()
                .username("kelvinkering")
                .emailAddress("admin@gmail.com")
                .password(passwordEncoder.encode("Kemails@2020"))
                .profileImageAddress("https://profile/image")
                .role(Role.ADMIN)
                .id(100)
                .build();
    }

    public ApplicationUser getDummyAppUser(String emailAddress) throws UsernameNotFoundException {
        return ApplicationUser.builder()
                .username("kelvinkering")
                .emailAddress("admin@gmail.com")
                .password(passwordEncoder.encode("Kemails@2020"))
                .profileImageAddress("https://profile/image")
                .role(Role.ADMIN)
                .id(100)
                .build();
    }
}
