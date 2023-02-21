package com.api.api.resources;

import com.api.api.domain.user.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
@RequestMapping("/users")
public class UserResource {

    @Autowired
    private FindUserByIdService findByIdService;


    @GetMapping("/{id}")
    public ResponseEntity<User> findById(@PathVariable UUID id){
        return ResponseEntity.status(HttpStatus.OK).body(findByIdService.execute(id));
    }

}
