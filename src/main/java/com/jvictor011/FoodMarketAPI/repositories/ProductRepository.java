package com.jvictor011.FoodMarketAPI.repositories;

import com.jvictor011.FoodMarketAPI.domain.product.Product;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository extends MongoRepository<Product, String> {
}
