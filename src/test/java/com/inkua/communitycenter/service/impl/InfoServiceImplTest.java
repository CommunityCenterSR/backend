package com.inkua.communitycenter.service.impl;

import com.inkua.communitycenter.entity.Information;
import com.inkua.communitycenter.exception.NotFoundException;
import com.inkua.communitycenter.repository.IInfoRepository;
import com.inkua.communitycenter.service.InfoService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class InfoServiceImplTest {

    @Mock
    private IInfoRepository infoRepository;

    @InjectMocks
    private InfoServiceImpl infoService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testFindAll() {
        // Arrange
        when(infoRepository.findAll()).thenReturn(Arrays.asList(new Information(), new Information()));

        // Act
        List<Information> result = infoService.findAll();

        // Assert
        assertEquals(2, result.size());
    }

    @Test
    void testFindByType() {
        // Arrange
        String type = "someType";
        when(infoRepository.findByType(type)).thenReturn(Optional.of(new Information()));

        // Act
        Optional<Information> result = infoService.findByType(type);

        // Assert
        assertTrue(result.isPresent());
    }

    @Test
    void testSaveOrUpdate() {
        // Arrange
        Information information = new Information();
        information.setType("someType");

        when(infoRepository.findByType(information.getType())).thenReturn(Optional.empty());
        when(infoRepository.save(information)).thenReturn(information);

        // Act
        Information result = infoService.saveOrUpdate(information);

        // Assert
        assertNotNull(result);
        assertEquals("someType", result.getType());
    }

    @Test
    void testSaveOrUpdateUpdateExistingInfo() {
        // Arrange
        Information information = new Information();
        information.setId(1L);
        information.setType("someType");

        when(infoRepository.findByType(information.getType())).thenReturn(Optional.of(information));
        when(infoRepository.save(information)).thenReturn(information);

        // Act
        Information result = infoService.saveOrUpdate(information);

        // Assert
        assertNotNull(result);
        assertEquals("someType", result.getType());
    }

    @Test
    void testDelete() {
        // Arrange
        Long id = 1L;
        Information information = new Information();
        information.setId(id);

        when(infoRepository.findById(id)).thenReturn(Optional.of(information));

        // Act
        Information result = infoService.delete(id);

        // Assert
        assertNotNull(result);
        assertEquals(id, result.getId());
        verify(infoRepository, times(1)).deleteById(id);
    }

    @Test
    void testDeleteNotFound() {
        // Arrange
        Long id = 1L;

        when(infoRepository.findById(id)).thenReturn(Optional.empty());

        // Act and Assert
        assertThrows(NotFoundException.class, () -> infoService.delete(id));
    }
}
