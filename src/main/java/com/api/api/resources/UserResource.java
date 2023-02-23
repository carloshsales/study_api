package com.api.api.resources;

import com.api.api.domain.dto.UserDTO;
import com.api.api.domain.user.User;
import com.api.api.services.interfaces.IUserService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/users")
public class UserResource {

    private final String ID = "/{id}";

    @Autowired
    private ModelMapper mapper;

    @Autowired
    IUserService service;

    @GetMapping(value = "/")
    public ResponseEntity<List<UserDTO>> findAll(){
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(service.findAll()
                        .stream()
                        .map(x -> mapper.map(x, UserDTO.class))
                        .collect(Collectors.toList()));
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<UserDTO> findById(@PathVariable UUID id){
        return ResponseEntity.status(HttpStatus.OK).body(mapper.map(service.findById(id), UserDTO.class));
    }

    @PostMapping(value = "/")
    public ResponseEntity<UserDTO> insert(@RequestBody UserDTO userDTO){
        URI uri = ServletUriComponentsBuilder
                .fromCurrentRequest().path(ID).buildAndExpand(service.insert(userDTO).getId()).toUri();
        return ResponseEntity.created(uri).build();
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<UserDTO> update(@PathVariable UUID id, @RequestBody UserDTO userDTO){
        userDTO.setId(id);
        return ResponseEntity.ok().body(mapper.map(service.update(userDTO), UserDTO.class));
    }
}
