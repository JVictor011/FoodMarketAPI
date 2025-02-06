package com.jvictor011.FoodMarketAPI.repositories;

import com.jvictor011.FoodMarketAPI.domain.category.Category;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoryRepository extends MongoRepository<Category, String> {
}
