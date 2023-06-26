package com.career_link.kenya.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;


@AllArgsConstructor
@NoArgsConstructor
@Data
@Getter
public class SignInRequest {
    private String emailAddress;
    private String password;

}
