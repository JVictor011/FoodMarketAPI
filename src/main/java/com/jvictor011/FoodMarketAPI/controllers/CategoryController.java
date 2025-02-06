package com.jvictor011.FoodMarketAPI.controllers;

import com.jvictor011.FoodMarketAPI.domain.category.Category;
import com.jvictor011.FoodMarketAPI.domain.category.CategoryDTO;
import com.jvictor011.FoodMarketAPI.domain.category.exception.CategoryCreationException;
import com.jvictor011.FoodMarketAPI.domain.category.exception.CategoryNotFoundException;
import com.jvictor011.FoodMarketAPI.services.CategoryService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/category")
public class CategoryController {

    private CategoryService categoryService;

    public CategoryController(CategoryService categoryService){
        this.categoryService = categoryService;
    }

    @PostMapping
    public ResponseEntity<Category> insert(@RequestBody CategoryDTO categoryData, BindingResult result){
        if (result.hasErrors()) {
            return ResponseEntity.badRequest().build();
        }

        try {
            Category newCategory = this.categoryService.create(categoryData);
            return ResponseEntity.ok().body(newCategory);
        } catch (CategoryCreationException e) {
            return ResponseEntity.status(HttpStatus.CONFLICT).build();
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @GetMapping
    public ResponseEntity<List<Category>> getAll(){
        try {
            List<Category> categoryList = this.categoryService.getAll();
            if (categoryList.isEmpty()) {
                return ResponseEntity.noContent().build();
            }

            return ResponseEntity.ok().body(categoryList);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @PutMapping(path = "{id}")
    public ResponseEntity<Category> update(@PathVariable("id") String id, @RequestBody CategoryDTO categoryData){
        try {
            Category updatedCategory = this.categoryService.update(id, categoryData);
            return ResponseEntity.ok().body(updatedCategory);
        } catch (CategoryNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @DeleteMapping(path = "{id}")
    public ResponseEntity<Category> delete(@PathVariable("id") String id){
        try {
            this.categoryService.delete(id);
            return ResponseEntity.noContent().build();
        } catch (CategoryNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
}
