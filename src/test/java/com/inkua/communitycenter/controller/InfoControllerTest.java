package com.inkua.communitycenter.controller;

import com.inkua.communitycenter.entity.Information;
import com.inkua.communitycenter.repository.IInfoRepository;
import com.inkua.communitycenter.service.InfoService;
import com.inkua.communitycenter.service.impl.InfoServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
@ExtendWith(MockitoExtension.class)

class InfoControllerTest {
    @Mock
    private InfoService infoService;

    @InjectMocks
    private InfoController infoController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testFindAll() {
        // Given
        Information info1 = new Information();
        Information info2 = new Information();
        List<Information> infoList = Arrays.asList(info1, info2);
        Mockito.when(infoService.findAll()).thenReturn(infoList);

        // When
        ResponseEntity<List<Information>> responseEntity = infoController.findAll();

        // Then
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals(infoList, responseEntity.getBody());
    }

    @Test
    void testFindByType() {
        // Given
        String type = "example";
        Information info = new Information();
        Mockito.when(infoService.findByType(type)).thenReturn(Optional.of(info));

        // When
        ResponseEntity<Information> responseEntity = infoController.findByType(type);

        // Then
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals(info, responseEntity.getBody());
    }

    @Test
    void testCreateOrUpdate() {
        // Given
        Information info = new Information();
        Mockito.when(infoService.saveOrUpdate(info)).thenReturn(info);

        // When
        ResponseEntity<Information> responseEntity = infoController.createOrUpdate(info);

        // Then
        assertEquals(HttpStatus.CREATED, responseEntity.getStatusCode());
        assertEquals(info, responseEntity.getBody());
    }

    @Test
    void testDeleteInfo() {
        // Given
        Long infoId = 1L;
        Information info = new Information();
        Mockito.when(infoService.delete(infoId)).thenReturn(info);

        // When
        ResponseEntity<Information> responseEntity = infoController.deleteInfo(infoId);

        // Then
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals(info, responseEntity.getBody());
    }
}