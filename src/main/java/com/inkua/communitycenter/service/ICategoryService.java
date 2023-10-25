package com.inkua.communitycenter.service;

import com.inkua.communitycenter.entity.Category;

import java.util.List;
import java.util.Optional;

public interface ICategoryService {

    List<Category> findAll();

    Optional<Category> findById(Long id);

    Optional<Category> findByName(String name);

    Category saveCategory(Category category);

    Category updateCategory(Long catId, Category category);

    Category deleteCategory(Long catId);

}
