package com.api.api.services.interfaces;

import com.api.api.domain.dto.UserDTO;
import com.api.api.domain.user.User;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public interface IUserService {

    User insert(UserDTO user);
    User findById(UUID id);
    List<User> findAll();
    Optional<User> findByEmail(String email);

}
