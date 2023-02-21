package com.api.api.domain.user;

import jakarta.persistence.*;
import lombok.*;

import java.util.UUID;

@Setter @Getter
@NoArgsConstructor @AllArgsConstructor
@EqualsAndHashCode
@ToString
@Entity
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;
    private String name;
    @Column(unique = true)
    private String email;

    @EqualsAndHashCode.Exclude
    private String password;
}
