package com.inkua.communitycenter.repository;

import com.inkua.communitycenter.entity.User;
import com.inkua.communitycenter.service.impl.UserServiceImpl;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class IUserRepositoryTest {
    @Mock
    private IUserRepository iUserRepository;
    @InjectMocks
    private UserServiceImpl userService;

    @Test
    void findByEmail() {
        String userEmail = "franguillechahla@example.com";
        User user = new User();
        user.setEmail(userEmail);

        when(iUserRepository.findByEmail(userEmail)).thenReturn(Optional.of(user));
        Optional<User> result = userService.findByEmail(userEmail);
        assertEquals(user, result.orElse(null));
    }
}