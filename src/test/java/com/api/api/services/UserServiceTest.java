package com.api.api.services;

import com.api.api.domain.dto.UserDTO;
import com.api.api.domain.user.User;
import com.api.api.repositories.UserRepository;

import com.api.api.services.exceptions.DataIntegrityViolationException;
import com.api.api.services.exceptions.ObjectNotFoundException;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.modelmapper.ModelMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;


@SpringBootTest
class UserServiceTest {

    private static final UUID ID = UUID.fromString("35361d85-107c-4f1f-a047-263093161c1b");
    public static final String NAME = "Test class";
    public static final String EMAIL = "test@class.mock";
    public static final String PASSWORD = "123";
    @InjectMocks
    private UserService service;

    @Mock
    private UserRepository repository;
    @Mock
    private ModelMapper mapper;

    private User user;
    private UserDTO userDTO;
    private Optional<User> optionalUser;


    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        startUser();
    }

    @Test
    void whenInsertUserThenReturnAnUserSuccess() {
        when(repository.save(any())).thenReturn(user);

        var response = service.insert(userDTO);

        assertNotNull(response);
        assertEquals(User.class, response.getClass());
        assertEquals(ID, response.getId());
        assertEquals(NAME, response.getName());
        assertEquals(EMAIL, response.getEmail());
        assertEquals(PASSWORD, response.getPassword());
    }

    @Test
    void whenInsertUserThenReturnDataIntegrityViolationException() {
        when(repository.save(any())).thenThrow(new DataIntegrityViolationException("The email provided is already in use"));

        try{
            service.insert(userDTO);
        }catch (Exception e){
            assertNotNull(e.getMessage());
            assertEquals(DataIntegrityViolationException.class, e.getClass());
            assertEquals("The email provided is already in use", e.getMessage());
        }
    }

    @Test
    void update() {
    }

    @Test
    void delete() {
    }

    @Test
    void whenFindAllThenReturnAnListOfUsers() {
        when(repository.findAll()).thenReturn(List.of(user));

        var response = service.findAll();

        assertNotNull(response);
        assertEquals(1, response.size());
        assertEquals(User.class, response.get(0).getClass());
        assertEquals(ID, response.get(0).getId());
        assertEquals(NAME, response.get(0).getName());
        assertEquals(EMAIL, response.get(0).getEmail());
        assertEquals(PASSWORD, response.get(0).getPassword());
    }

    @Test
    void whenFindByIdThenReturnAnUserInstance() {
        when(repository.findById(any(UUID.class))).thenReturn(optionalUser);

        var response = service.findById(ID);

        assertNotNull(response);
        assertEquals(User.class, response.getClass());
        assertEquals(ID, response.getId());
        assertEquals(NAME, response.getName());
        assertEquals(EMAIL, response.getEmail());
        assertEquals(PASSWORD, response.getPassword());
    }

    @Test
    void whenFindByIdThenReturnAnObjectNotFoundException(){
        when(repository.findById(any(UUID.class))).thenThrow(new ObjectNotFoundException("Object not found"));
        try {
            service.findById(ID);
        }catch (Exception e){
            assertNotNull(e.getMessage());
            assertEquals(ObjectNotFoundException.class, e.getClass());
            assertEquals("Object not found", e.getMessage());
        }
    }

    private void startUser(){
        user = new User(ID, NAME, EMAIL, PASSWORD);
        userDTO = new UserDTO(ID, NAME, EMAIL, PASSWORD);
        optionalUser = Optional.of(new User(ID, NAME, EMAIL, PASSWORD));
    }
}