package com.RapidGrill.service;

import java.util.List;

import com.RapidGrill.model.Category;
import com.RapidGrill.model.Food;
import com.RapidGrill.model.Restaurant;
import com.RapidGrill.request.CreateFoodRequest;

public interface FoodService {
    public Food createFood(CreateFoodRequest req, Category category, Restaurant restaurant);

    public void deleteFood(Long foodId) throws Exception;

    public List<Food> getRestaurantFood(Long restaurantId, boolean isVegetarian, boolean isSeasonal,
            boolean isNonVeg, String foodCategory);

    public List<Food> searchFood(String keyword);

    public Food findFoodById(Long foodId) throws Exception;

    public Food updateAvailabilityStatus(Long foodId) throws Exception;
}
