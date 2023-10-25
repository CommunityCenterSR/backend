package com.inkua.communitycenter.controller;

import com.inkua.communitycenter.entity.Category;
import com.inkua.communitycenter.service.ICategoryService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.*;

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

    @GetMapping("/category")
    public ResponseEntity<Optional<Category>> findByName(@RequestParam(name = "name") String name){
        Optional<Category> category = categoryService.findByName(name);
        return new ResponseEntity<>(category, HttpStatus.OK);
    }

    // ---------------------------------------------

    @PostMapping
    public ResponseEntity<Category> createCategory(@Valid @RequestBody Category category){
        Category newCategory = categoryService.saveCategory(category);
        return new ResponseEntity<>(newCategory, HttpStatus.CREATED);
    }


    // ---------------------------------------------

    @PutMapping("/{catId}")
    public ResponseEntity<Category> updateCategory(@PathVariable Long catId,
                                                   @Valid @RequestBody Category category){
        Category categoryUpdated = categoryService.updateCategory(catId, category);
        return new ResponseEntity<>(categoryUpdated, HttpStatus.OK);
    }

    // ---------------------------------------------

    @DeleteMapping("/{catId}")
    public ResponseEntity<Category> deleteCategory(@PathVariable Long catId){
        Category categoryDeleted = categoryService.deleteCategory(catId);
        return new ResponseEntity<>(categoryDeleted, HttpStatus.OK);
    }

    // ---------------------------------------------


}
