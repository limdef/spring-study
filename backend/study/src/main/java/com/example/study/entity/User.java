package com.example.study.entity;

import jakarta.persistence.*;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
@Entity(name = "users")
public class User {
    @Id
    private String name;

    @Setter
    private String password;

    @Enumerated(EnumType.STRING)
    private Authority authority;

    public enum Authority {
        ADMIN, USER
    }
}
