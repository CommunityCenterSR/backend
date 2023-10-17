package com.inkua.communitycenter.controller;

import com.inkua.communitycenter.entity.Category;
import com.inkua.communitycenter.service.ICategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/api/v1/categories")
public class CategoryController {

    @Autowired
    private ICategoryService categoryService;

    // ---------------------------------------------

    @GetMapping
    public List<Category> findAll(){
        return categoryService.findAll();
    }

    // ---------------------------------------------

    @PostMapping
    public Category createCategory(@RequestBody Category category){
        return categoryService.createCategory(category);
    }

    // ---------------------------------------------

    @PutMapping("/{catId}")
    public Category updateCategory(@PathVariable Long catId, @RequestBody Category category){
        return categoryService.updateCategory(catId, category);
    }

    // ---------------------------------------------

    @DeleteMapping("/{catId}")
    public void deleteCategory(@PathVariable Long catId){
        categoryService.deleteCategory(catId);
    }

    // ---------------------------------------------


}
