package com.career_link.kenya.entities;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Getter
@Builder
public class SignInResponse{
    private String username;
    private String message;
    private String profileImageAddress;
    private String  jwtToken;

}