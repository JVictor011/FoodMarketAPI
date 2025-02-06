package com.jvictor011.FoodMarketAPI.services;

import com.jvictor011.FoodMarketAPI.domain.category.Category;
import com.jvictor011.FoodMarketAPI.domain.category.CategoryDTO;
import com.jvictor011.FoodMarketAPI.domain.category.exception.CategoryNotFoundException;
import com.jvictor011.FoodMarketAPI.domain.product.Product;
import com.jvictor011.FoodMarketAPI.domain.product.ProductDTO;
import com.jvictor011.FoodMarketAPI.domain.product.exception.ProductNotFoundException;
import com.jvictor011.FoodMarketAPI.repositories.ProductRepository;
import com.jvictor011.FoodMarketAPI.services.aws.AwsSnsService;
import com.jvictor011.FoodMarketAPI.services.aws.MessageDTO;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductService {

    private final ProductRepository productRepository;
    private final CategoryService categoryService;
    private final AwsSnsService awsSnsService;

    public ProductService(ProductRepository productRepository, CategoryService categoryService, AwsSnsService awsSnsService){
        this.productRepository = productRepository;
        this.categoryService = categoryService;
        this.awsSnsService = awsSnsService;
    }

    public Product create(ProductDTO productData){
        Category category = this.categoryService.getById(productData.categoryId())
                .orElseThrow(() -> new CategoryNotFoundException("Category not found with id: " + productData.categoryId()));

        Product newProduct = new Product(productData);
        newProduct.setCategory(category);
        this.productRepository.save(newProduct);

        this.awsSnsService.publish(new MessageDTO(newProduct.getOwnerId()));

        return newProduct;
    }

    public List<Product> getAll(){
        return this.productRepository.findAll();
    }

    public Product update(String id, ProductDTO productData){
        Product product = this.productRepository.findById(id)
                .orElseThrow(() -> new ProductNotFoundException("Product not found with id: " + id));

        if(productData.categoryId() != null){
            this.categoryService.getById(productData.categoryId())
                    .ifPresent(product::setCategory);
        }

        if (!productData.title().isEmpty()) product.setTitle(productData.title());
        if (!productData.description().isEmpty()) product.setDescription(productData.description());
        if (!(productData.price() == null)) product.setPrice(productData.price());

        this.productRepository.save(product);

        this.awsSnsService.publish(new MessageDTO(product.getOwnerId()));

        return product;
    }

    public void delete(String id){
        Product product = this.productRepository.findById(id)
                .orElseThrow(() -> new ProductNotFoundException("Product not found with id: " + id));

        this.productRepository.delete(product);
    }
}
