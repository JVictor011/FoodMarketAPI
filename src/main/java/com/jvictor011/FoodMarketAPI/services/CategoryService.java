package com.jvictor011.FoodMarketAPI.services;

import com.jvictor011.FoodMarketAPI.domain.category.Category;
import com.jvictor011.FoodMarketAPI.domain.category.CategoryDTO;
import com.jvictor011.FoodMarketAPI.domain.category.exception.CategoryNotFoundException;
import com.jvictor011.FoodMarketAPI.repositories.CategoryRepository;
import com.jvictor011.FoodMarketAPI.services.aws.AwsSnsService;
import com.jvictor011.FoodMarketAPI.services.aws.MessageDTO;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CategoryService {
    private final CategoryRepository repository;
    private final AwsSnsService awsSnsService;

    public CategoryService(CategoryRepository repository, AwsSnsService awsSnsService){
        this.repository = repository;
        this.awsSnsService = awsSnsService;
    }

    public Category create(CategoryDTO categoryData){
        Category newCategory = new Category(categoryData);
        this.repository.save(newCategory);

        this.awsSnsService.publish(new MessageDTO(newCategory.toString()));

        return newCategory;
    }

    public Optional<Category> getById(String id){
        return this.repository.findById(id);
    }

    public List<Category> getAll(){
        return this.repository.findAll();
    }

    public Category update(String id, CategoryDTO categoryData){
        Category category = this.repository.findById(id)
                .orElseThrow(() -> new CategoryNotFoundException("Category not found with id: " + id));

        if (!categoryData.title().isEmpty()) category.setTitle(categoryData.title());
        if (!categoryData.description().isEmpty()) category.setDescription(categoryData.description());

        this.repository.save(category);

        this.awsSnsService.publish(new MessageDTO(category.toString()));

        return category;
    }

    public void delete(String id){
        Category category = this.repository.findById(id)
                .orElseThrow(() -> new CategoryNotFoundException("Category not found with id: " + id));

        this.repository.delete(category);
    }
}
