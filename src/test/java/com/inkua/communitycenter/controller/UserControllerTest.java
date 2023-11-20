package com.inkua.communitycenter.controller;

import com.inkua.communitycenter.entity.User;
import com.inkua.communitycenter.service.impl.UserServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

class UserControllerTest {
    @Mock
    private UserServiceImpl userService;

    @InjectMocks
    private UserController userController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    void getUsers() {
        // Configurar el comportamiento del servicio
        List<User> expectedUsers = Arrays.asList(
                new User(1L, "Francisco", "franguillechahla@gmail.com", "12345"),
                new User(2L, "Guillermo", "guillermo@gmail.com", "67890")
        );
        when(userService.findAllUsers()).thenReturn(expectedUsers);

        // Llamada al método del controlador
        ResponseEntity<List<User>> responseEntity = userController.getUsers();
        List<User> actualUsers = responseEntity.getBody();

        // Verificar los resultados
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals(expectedUsers, actualUsers);
    }

    @Test
    void getByEmail() {
        String userEmail = "franguillechahla@gmail.com";

        // Configurar el comportamiento del servicio
        User expectedUser = new User(1L, "Francisco", userEmail, "12345");
        when(userService.findByEmail(userEmail)).thenReturn(Optional.of(expectedUser));

        // Llamada al método del controlador
        ResponseEntity<Optional<User>> responseEntity = userController.getByEmail(userEmail);
        Optional<User> actualUser = responseEntity.getBody();

        // Verificar los resultados
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals(expectedUser, actualUser.orElse(null),
                "El usuario devuelto no coincide con el usuario esperado.");
    }

    @Test
    void saveUser() {
        User userToSave = new User(2L, "Guillermo", "guillermo@example.com", "contraseña123");

        // Configurar el comportamiento del servicio al guardar el usuario
        User savedUser = new User(2L, "Guillermo", "guillermo@example.com", "contraseña123");
        when(userService.saveUser(userToSave)).thenReturn(savedUser);

        // Llamada al método del controlador
        ResponseEntity<User> responseEntity = userController.saveUser(userToSave);
        User returnedUser = responseEntity.getBody();

        // Verificar los resultados
        assertEquals(HttpStatus.CREATED, responseEntity.getStatusCode());
        assertEquals(savedUser, returnedUser, "El usuario devuelto no coincide con el usuario guardado.");
    }

    @Test
    void updateUserById() {
        // ID del usuario que se actualizará
        Long userId = 1L;

        // Usuario que se enviará al controlador para la actualización
        User updatedUserRequest = new User(null, "Lucia", "lucia@example.com", "pass123");

        // Configurar el comportamiento del servicio al actualizar el usuario
        User updatedUser = new User(userId, "UsuarioActualizado", "actualizado@example.com", "nuevaContraseña");
        when(userService.updateById(updatedUserRequest, userId)).thenReturn(updatedUser);

        // Llamada al método del controlador
        ResponseEntity<User> responseEntity = userController.updateUserById(updatedUserRequest, userId);
        User returnedUser = responseEntity.getBody();

        // Verificar los resultados
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals(updatedUser, returnedUser, "El usuario devuelto no coincide con el usuario actualizado.");
    }

    @Test
    void deleteById() {
        // ID del usuario que se eliminará
        Long userId = 2L;

        // Configurar el comportamiento del servicio al eliminar el usuario
        User deletedUser = new User(userId, "UsuarioEliminado", "eliminado@example.com", "contraseñaEliminada");
        when(userService.deleteUser(userId)).thenReturn(deletedUser);

        // Llamada al método del controlador
        ResponseEntity<User> responseEntity = userController.deleteById(userId);
        User returnedUser = responseEntity.getBody();

        // Verificar los resultados
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals(deletedUser, returnedUser, "El usuario devuelto no coincide con el usuario eliminado.");

    }
}