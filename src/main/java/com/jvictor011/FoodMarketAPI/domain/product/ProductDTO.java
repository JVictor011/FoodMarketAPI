package com.jvictor011.FoodMarketAPI.domain.product;

import com.jvictor011.FoodMarketAPI.domain.category.Category;

public record ProductDTO(String title, String description, String ownerId, Integer price, String categoryId) {
}
