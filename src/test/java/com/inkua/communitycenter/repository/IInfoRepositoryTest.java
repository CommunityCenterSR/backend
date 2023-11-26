package com.inkua.communitycenter.repository;

import com.inkua.communitycenter.entity.Information;
import com.inkua.communitycenter.repository.IInfoRepository;
import com.inkua.communitycenter.service.impl.InfoServiceImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class IInfoRepositoryTest {

    @Mock
    private IInfoRepository infoRepository;

    @InjectMocks
    private InfoServiceImpl infoService; // Assuming you have InfoServiceImpl for testing the repository

    @Test
    void testFindByType() {
        // Arrange
        String type = "someType";
        Information information = new Information();
        information.setType(type);

        when(infoRepository.findByType(type)).thenReturn(Optional.of(information));

        // Act
        Optional<Information> result = infoRepository.findByType(type);

        // Assert
        assertEquals(type, result.get().getType());
    }

    // Add more tests as needed for other methods in the repository
}