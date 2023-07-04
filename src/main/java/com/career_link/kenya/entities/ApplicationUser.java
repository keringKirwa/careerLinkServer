package com.career_link.kenya.entities;


import jakarta.persistence.*;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import java.util.Collection;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "APPLICATION_USER")
public class ApplicationUser implements UserDetails {


    @Id
    @SequenceGenerator(
            name = "application_user_sequence_gen",
            sequenceName = "application_user_sequence",
            allocationSize = 1
    )
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Integer id;

    private String username; //THIS IS THE USER EMAIL ADDRESS(UNIQUE ID)
    private String password;
    private String fullName;

    private String profileImageAddress;
    private String emailAddress;

    @Builder.Default
    private boolean isAccountNonLocked = true;
    @Builder.Default
    private boolean isCredentialsNonExpired = true;
    @Builder.Default
    private boolean isEnabled = false;
    @Builder.Default
    private boolean isAccountNonExpired = true;

    @Enumerated(EnumType.STRING)
    private Role role;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority(role.name()));
    }


}
