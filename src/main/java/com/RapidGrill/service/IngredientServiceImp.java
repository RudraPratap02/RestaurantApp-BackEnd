package com.RapidGrill.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.RapidGrill.model.Category;
import com.RapidGrill.model.IngredientCategory;
import com.RapidGrill.model.IngredientsItem;
import com.RapidGrill.model.Restaurant;
import com.RapidGrill.repository.IngredientCategoryRepository;
import com.RapidGrill.repository.IngredientItemRepository;

@Service
public class IngredientServiceImp implements IngredientsService {

    @Autowired
    private IngredientItemRepository ingredientItemRepository;

    @Autowired
    private IngredientCategoryRepository ingredientCategoryRepository;

    @Autowired
    private RestaurantService restaurantService;

    @Override
    public IngredientCategory createIngredientCategory(String name, Long restaurantId) throws Exception {

        Restaurant restaurant = restaurantService.findRestaurantById(restaurantId);

        IngredientCategory category = new IngredientCategory();
        category.setName(name);
        category.setRestaurant(restaurant);

        return ingredientCategoryRepository.save(category);
    }

    @Override
    public IngredientCategory findIngredientCategoryById(Long id) throws Exception {
        Optional<IngredientCategory> optionalCategory = ingredientCategoryRepository.findById(id);

        if (optionalCategory.isEmpty()) {
            throw new Exception("Ingredient Category not found");
        }

        return optionalCategory.get();
    }

    @Override
    public List<IngredientCategory> findIngredientCategoryByRestaurantId(Long id) throws Exception {
        restaurantService.findRestaurantById(id);

        return ingredientCategoryRepository.findByRestaurantId(id);
    }

    @Override
    public IngredientsItem createIngredientItem(Long id, String ingredientName, Long categoryId) throws Exception {

        Restaurant restaurant = restaurantService.findRestaurantById(id);
        IngredientCategory category = findIngredientCategoryById(categoryId);

        IngredientsItem item = new IngredientsItem();

        item.setName(ingredientName);
        item.setCategory(category);
        item.setRestaurant(restaurant);

        IngredientsItem ingredient = ingredientItemRepository.save(item);
        category.getIngredients().add(ingredient);
        return ingredient;
    }

    @Override
    public List<IngredientsItem> findRestaurantIngredients(Long id) throws Exception {

        return ingredientItemRepository.findByRestaurantId(id);
    }

    @Override
    public IngredientsItem updateStock(Long id) throws Exception {
        Optional<IngredientsItem> ingredients = ingredientItemRepository.findById(id);

        if (ingredients.isEmpty()) {
            throw new Exception("Ingredient not found");
        }

        IngredientsItem ing = ingredients.get();
        ing.setInStore(!ing.isInStore());
        return ingredientItemRepository.save(ing);
    }
}
