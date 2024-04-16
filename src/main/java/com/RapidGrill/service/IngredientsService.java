package com.RapidGrill.service;

import java.util.List;

import com.RapidGrill.model.IngredientCategory;
import com.RapidGrill.model.IngredientsItem;

public interface IngredientsService {
    public IngredientCategory createIngredientCategory(String name, Long restaurantId) throws Exception;

    public IngredientCategory findIngredientCategoryById(Long id) throws Exception;

    public List<IngredientCategory> findIngredientCategoryByRestaurantId(Long id) throws Exception;

    public IngredientsItem createIngredientItem(Long id, String ingredientName, Long categoryId) throws Exception;

    public List<IngredientsItem> findRestaurantIngredients(Long id) throws Exception;

    public IngredientsItem updateStock(Long id) throws Exception;
}
