package com.jvictor011.FoodMarketAPI.controllers;

import com.jvictor011.FoodMarketAPI.domain.product.Product;
import com.jvictor011.FoodMarketAPI.domain.product.ProductDTO;
import com.jvictor011.FoodMarketAPI.domain.product.exception.ProductCreationException;
import com.jvictor011.FoodMarketAPI.services.ProductService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/product")
public class ProductController {
    private ProductService productService;

    public ProductController(ProductService productService){
        this.productService = productService;
    }

    @PostMapping
    public ResponseEntity<Product> insert(@RequestBody ProductDTO productData, BindingResult result){
        if (result.hasErrors()) {
            return ResponseEntity.badRequest().build();
        }

        try {
            Product newProduct = this.productService.create(productData);
            return ResponseEntity.ok().body(newProduct);
        } catch (ProductCreationException e) {
            return ResponseEntity.status(HttpStatus.CONFLICT).build();
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @GetMapping
    public ResponseEntity<List<Product>> getAll(){
        try {
            List<Product> productList = this.productService.getAll();
            if (productList.isEmpty()) {
                return ResponseEntity.noContent().build();
            }

            return ResponseEntity.ok().body(productList);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @PutMapping(path = "{id}")
    public ResponseEntity<Product> update(@PathVariable("id") String id, @RequestBody ProductDTO productData){
        try {
            Product updatedProduct = this.productService.update(id, productData);
            return ResponseEntity.ok().body(updatedProduct);
        } catch (ProductCreationException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @DeleteMapping(path = "{id}")
    public ResponseEntity<Product> delete(@PathVariable("id") String id){
        try {
            this.productService.delete(id);
            return ResponseEntity.noContent().build();
        } catch (ProductCreationException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
}
