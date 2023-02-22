package com.api.api.services;

import com.api.api.domain.user.User;
import com.api.api.repositories.UserRepository;
import com.api.api.services.exceptions.ObjectNotFoundException;
import com.api.api.services.interfaces.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class UserService implements IUserService {

    @Autowired
    private UserRepository repository;


    public List<User> getAll(){
        return this.repository.findAll();
    }
    @Override
    public User findById(UUID id) {
        Optional<User> user = this.repository.findById(id);
        return user.orElseThrow(() -> new ObjectNotFoundException("Object not found"));
    }
}
