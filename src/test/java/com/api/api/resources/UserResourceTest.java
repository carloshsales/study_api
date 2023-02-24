package com.api.api.resources;

import com.api.api.domain.dto.UserDTO;
import com.api.api.domain.user.User;
import com.api.api.services.UserService;
import com.api.api.services.interfaces.IUserService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.modelmapper.ModelMapper;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.ResponseEntity;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@SpringBootTest
class UserResourceTest {

    private static final UUID ID = UUID.fromString("35361d85-107c-4f1f-a047-263093161c1b");
    public static final String NAME = "Test class";
    public static final String EMAIL = "test@class.mock";
    public static final String PASSWORD = "123";
    public static final String THE_EMAIL_PROVIDED_IS_ALREADY_IN_USE = "The email provided is already in use";
    public static final String OBJECT_NOT_FOUND = "Object not found";
    private User user;
    private UserDTO userDTO;

    @InjectMocks
    private UserResource resource;

    @Mock
    private IUserService service;

    @Mock
    private ModelMapper mapper;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        startUser();
    }

    @Test
    void findAll() {
    }

    @Test
    void whenFindByIdThenReturnSuccess() {
        when(service.findById(any(UUID.class))).thenReturn(user);
        when(mapper.map(any(), any())).thenReturn(userDTO);

        var response = resource.findById(ID);

        assertNotNull(response);
        assertNotNull(response.getBody());
        assertEquals(ResponseEntity.class, response.getClass());
        assertEquals(UserDTO.class, response.getBody().getClass());
        assertEquals(ID, response.getBody().getId());
        assertEquals(NAME, response.getBody().getName());
        assertEquals(EMAIL, response.getBody().getEmail());
        assertEquals(PASSWORD, response.getBody().getPassword());
    }

    @Test
    void insert() {
    }

    @Test
    void update() {
    }

    @Test
    void delete() {
    }

    private void startUser(){
        user = new User(ID, NAME, EMAIL, PASSWORD);
        userDTO = new UserDTO(ID, NAME, EMAIL, PASSWORD);
    }
}