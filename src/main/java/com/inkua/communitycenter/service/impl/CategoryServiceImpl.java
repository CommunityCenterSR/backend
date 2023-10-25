package com.inkua.communitycenter.service.impl;

import com.inkua.communitycenter.entity.Category;
import com.inkua.communitycenter.exception.NotFoundException;
import com.inkua.communitycenter.repository.ICategoryRepository;
import com.inkua.communitycenter.service.ICategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CategoryServiceImpl implements ICategoryService {

    @Autowired
    private ICategoryRepository categoryRepository;

    @Override
    public List<Category> findAll() {
        return categoryRepository.findAll();
    }

    @Override
    public Optional<Category> findById(Long id) {
        return categoryRepository.findById(id);
    }

    @Override
    public Optional<Category> findByName(String name) {
        Optional<Category> category = categoryRepository.findByName(name);

        if (category.isEmpty())
            throw new NotFoundException("No se encuentra una categoría con el nombre: " + name);
        else return category;
    }

    @Override
    public Category saveCategory(Category category) {
        Optional<Category> categoryExists = categoryRepository.findByName(category.getName());

        if (categoryExists.isPresent())
            return categoryExists.get();

        return categoryRepository.save(category);
    }

    @Override
    public Category updateCategory(Long catId, Category category) {
        Optional<Category> categoryExists = categoryRepository.findById(catId);

        if (categoryExists.isEmpty())
            throw new NotFoundException("No se encuentra una categoría con ID: " + catId);

        category.setId(categoryExists.get().getId());
        return categoryRepository.save(category);
    }

    @Override
    public Category deleteCategory(Long catId) {
        Optional<Category> categoryDeleted = categoryRepository.findById(catId);

        if (categoryDeleted.isEmpty())
            throw new NotFoundException("No se encontró la categoría con ID: " + catId);

        categoryRepository.deleteById(catId);
        return categoryDeleted.get();
    }
}
