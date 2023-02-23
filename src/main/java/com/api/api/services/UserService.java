package com.api.api.services;

import com.api.api.domain.dto.UserDTO;
import com.api.api.domain.user.User;
import com.api.api.repositories.UserRepository;
import com.api.api.services.exceptions.DataIntegrityViolationException;
import com.api.api.services.exceptions.ObjectNotFoundException;
import com.api.api.services.interfaces.IUserService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class UserService implements IUserService {

    @Autowired
    private ModelMapper mapper;

    @Autowired
    private UserRepository repository;

    @Override
    public User insert(UserDTO userDTO) {
        verifyUserExistByEmail(userDTO);
        return repository.save(mapper.map(userDTO, User.class));
    }

    @Override
    public User update(UserDTO userDTO){
        verifyUserExistByEmail(userDTO);
        return repository.save(mapper.map(userDTO, User.class));
    }

    @Override
    public void delete(UUID id){
        findById(id);
        repository.deleteById(id);
    }

    @Override
    public List<User> findAll(){
        return repository.findAll();
    }

    @Override
    public User findById(UUID id) {
        return repository.findById(id).orElseThrow(() -> new ObjectNotFoundException("Object not found"));
    }

    @Override
    public Optional<User> findByEmail(String email){
        return repository.findByEmail(email);
    }

    private void verifyUserExistByEmail(UserDTO user){
        var findUser = findByEmail(user.getEmail());
        if (findUser.isPresent() && !findUser.get().getId().equals(user.getId())){
            throw new DataIntegrityViolationException("The email provided is already in use");
        }
    }

}
