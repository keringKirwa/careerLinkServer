package com.career_link.kenya.services;

import com.career_link.kenya.entities.ApplicationUser;
import com.career_link.kenya.entities.Role;
import com.career_link.kenya.repositories.AuthRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import static com.career_link.kenya.utils.ApplicationConstants.passwordEncoder;

@Service
@RequiredArgsConstructor
public class UserDetailsServiceImpl implements UserDetailsService {
    private final AuthRepository authRepository ;


    @Override
    public UserDetails loadUserByUsername(String emailAddress) throws UsernameNotFoundException {

        return authRepository.findByEmailAddress(emailAddress);

    }

    public ApplicationUser getDummyAppUser(String emailAddress) {
        return ApplicationUser.builder()
                .username("admin@gmail.com")
                .emailAddress("admin@gmail.com")
                .password(passwordEncoder.encode("Kemails@2020"))
                .profileImageAddress("https://profile/image")
                .isAccountNonExpired(true)
                .isEnabled(true)
                .isCredentialsNonExpired(true)
                .isAccountNonLocked(true)
                .role(Role.ADMIN)
                .id(100)
                .build();
    }
}
