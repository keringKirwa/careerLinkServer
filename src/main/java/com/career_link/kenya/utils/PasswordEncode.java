package com.career_link.kenya.utils;


import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

public class PasswordEncode {
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

}