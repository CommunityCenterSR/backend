package com.inkua.communitycenter.service.impl;

import com.inkua.communitycenter.entity.Category;
import com.inkua.communitycenter.exception.NotFoundException;
import com.inkua.communitycenter.repository.ICategoryRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class CategoryServiceImplTest {

    @Mock
    private ICategoryRepository categoryRepository;

    @InjectMocks
    private CategoryServiceImpl categoryService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testFindAll() {
        List<Category> categories = new ArrayList<>();
        Mockito.when(categoryRepository.findAll()).thenReturn(categories);
        List<Category> result = categoryService.findAll();
        assertEquals(categories, result);
    }

    @Test
    void testFindById() {
        Long catId = 1L;
        Category category = new Category();
        Mockito.when(categoryRepository.findById(catId)).thenReturn(Optional.of(category));
        Optional<Category> result = categoryService.findById(catId);
        assertTrue(result.isPresent());
        assertEquals(category, result.get());
    }

    @Test
    void testFindByName() {
        String categoryName = "TestCategory";
        Category category = new Category();
        Mockito.when(categoryRepository.findByName(categoryName)).thenReturn(Optional.of(category));
        Optional<Category> result = categoryService.findByName(categoryName);
        assertTrue(result.isPresent());
        assertEquals(category, result.get());
    }

    @Test
    void testFindByNameNotFound() {
        String categoryName = "NonexistentCategory";
        Mockito.when(categoryRepository.findByName(categoryName)).thenReturn(Optional.empty());
        assertThrows(NotFoundException.class, () -> categoryService.findByName(categoryName));
    }


    @Test
    void testDeleteCategory() {
        Long catId = 1L;
        Category category = new Category();
        Mockito.when(categoryRepository.findById(catId)).thenReturn(Optional.of(category));
        Category result = categoryService.deleteCategory(catId);
        assertEquals(category, result);
        verify(categoryRepository, times(1)).deleteById(catId);
    }

    @Test
    void testDeleteCategoryNotFound() {
        Long catId = 1L;
        Mockito.when(categoryRepository.findById(catId)).thenReturn(Optional.empty());
        assertThrows(NotFoundException.class, () -> categoryService.deleteCategory(catId));
        verify(categoryRepository, never()).deleteById(catId);
    }
}
