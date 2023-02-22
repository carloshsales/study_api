package com.api.api.services.interfaces;

import com.api.api.domain.user.User;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public interface IUserService {

    User findById(UUID id);

}
