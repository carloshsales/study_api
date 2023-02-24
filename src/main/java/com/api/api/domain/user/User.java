package com.api.api.domain.user;

import jakarta.persistence.*;
import lombok.*;

import java.math.BigInteger;
import java.util.UUID;

@Getter @Setter
@NoArgsConstructor @AllArgsConstructor
@ToString(exclude = {"password"})
@Entity
@Table(name = "td_user")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;
    private String name;

    @Column(unique = true)
    private String email;
    private String password;
}
