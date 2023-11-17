package com.inkua.communitycenter.service.impl;

import com.inkua.communitycenter.entity.User;
import com.inkua.communitycenter.repository.IUserRepository;
import com.inkua.communitycenter.service.IUserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;
import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class UserServiceImplTest {
    @Mock
    private IUserRepository userRepository;

    @InjectMocks
    private IUserService userService = new UserServiceImpl();

    private User user;
    @BeforeEach
    public void setUp() {
        user = new User();
        user.setId(1L);
        user.setEmail("test@example.com");
    }

    @Test
    void findAllUsers() {
        Mockito.when(userRepository.findAll()).thenReturn(List.of(user));
        List<User> result = userService.findAllUsers();
        assertEquals(List.of(user), result);
    }

    @Test
    void findByEmail() {
        Mockito.when(userRepository.findByEmail(user.getEmail())).thenReturn(Optional.of(user));
        Optional<User> result = userService.findByEmail(user.getEmail());
        assertTrue(result.isPresent());
        assertEquals(user, result.get());
    }

    @Test
    void saveUser() {
        Mockito.when(userRepository.save(user)).thenReturn(user);
        User result = userService.saveUser(user);
        assertEquals(user, result);
    }

    @Test
    void updateById() {
        Mockito.when(userRepository.findById(user.getId())).thenReturn(Optional.of(user));
        Mockito.when(userRepository.save(user)).thenReturn(user);
        User result = userService.updateById(user, user.getId());
        assertEquals(user, result);
    }

    @Test
    void deleteUser() {
        Mockito.when(userRepository.findById(user.getId())).thenReturn(Optional.of(user));
        User result = userService.deleteUser(user.getId());
        assertEquals(user, result);
    }
}