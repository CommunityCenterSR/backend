package com.inkua.communitycenter.service;

import com.inkua.communitycenter.entity.Category;

import java.util.List;

public interface ICategoryService {

    List<Category> findAll();

    Category createCategory(Category category);

    Category updateCategory(Category category);

    void deleteCategory(Long catId);

}
