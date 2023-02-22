package com.api.api.config;

import com.api.api.domain.user.User;
import com.api.api.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import java.util.List;

@Configuration
@Profile("local")
public class LocalConfig {

    @Autowired
    UserRepository userRepository;

    @Bean
    public void startDB(){
        User u1 = new User(null, "CH", "ch@mail.com", "123");
        User u2 = new User(null, "JM", "jm@mail.com", "123");
        User u3 = new User(null, "JP", "jp@mail.com", "123");

        userRepository.saveAll(List.of(u1, u2, u3));
    }
}
