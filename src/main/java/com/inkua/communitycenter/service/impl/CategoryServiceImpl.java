package com.inkua.communitycenter.service.impl;

import com.inkua.communitycenter.entity.Category;
import com.inkua.communitycenter.exception.NotFoundException;
import com.inkua.communitycenter.repository.ICategoryRepository;
import com.inkua.communitycenter.service.ICategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryServiceImpl implements ICategoryService {

    @Autowired
    private ICategoryRepository categoryRepository;

    @Override
    public List<Category> findAll() {
        return categoryRepository.findAll();
    }

    @Override
    public Category createCategory(Category category) {
        return categoryRepository.save(category);
    }

    @Override
    public Category updateCategory(Long catId, Category category) {

        Category categoryExists = categoryRepository.findById(catId).orElseThrow(
                ()-> new NotFoundException("No se encontró una categoría con ID " + catId));

        category.setId(catId);
        return categoryRepository.save(category);
    }

    @Override
    public void deleteCategory(Long catId) {
        categoryRepository.deleteById(catId);
    }
}
