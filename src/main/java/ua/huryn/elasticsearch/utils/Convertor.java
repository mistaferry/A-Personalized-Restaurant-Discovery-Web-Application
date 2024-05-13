package ua.huryn.elasticsearch.utils;

import ua.huryn.elasticsearch.entity.db.*;
import ua.huryn.elasticsearch.entity.dto.*;

import java.util.ArrayList;
import java.util.List;

public final class Convertor {
    private Convertor() {
    }

    public static Restaurant convertToEntity(RestaurantDTO restaurantDTO) {
        Restaurant restaurant = new Restaurant();
        restaurant.setId(restaurantDTO.getRestaurantId());
        restaurant.setPlaceId(restaurantDTO.getPlaceId());
        restaurant.setName(restaurantDTO.getName());
        restaurant.setAddress(restaurantDTO.getAddress());
        restaurant.setLatitude(restaurantDTO.getLatitude());
        restaurant.setLongitude(restaurantDTO.getLongitude());
        restaurant.setRating(restaurantDTO.getRating());
        restaurant.setPriceLevel(restaurantDTO.getPriceLevel());
        restaurant.setWebsite(restaurantDTO.getWebsite());
        restaurant.setPhotoRef(restaurantDTO.getPhotoRef());
        restaurant.setCuisineType(restaurantDTO.getCuisineType());
        restaurant.setCategories(convertCategoryDTOListToEntity(restaurantDTO.getCategories()));
        restaurant.setDishes(convertDishDTOListToEntity(restaurantDTO.getDishes()));
        return restaurant;
    }

    public static Category convertToEntity(CategoryDTO categoryDTO) {
        Category category = new Category();
        category.setId(categoryDTO.getCategoryId());
        category.setName(categoryDTO.getName());
        return category;
    }

    public static DishType convertToEntity(DishTypeDTO dishTypeDTO) {
        DishType dishType = new DishType();
        dishType.setId(Math.toIntExact(dishTypeDTO.getDishTypeId()));
        dishType.setName(dishTypeDTO.getName());
        return dishType;
    }

    public static Ingredient convertToEntity(IngredientDTO ingredientDTO) {
        Ingredient ingredient = new Ingredient();
        ingredient.setId(ingredientDTO.getIngredientId());
        ingredient.setName(ingredientDTO.getName());
        return ingredient;
    }

    public static Dish convertToEntity(DishDTO dishDTO) {
        Dish dish = new Dish();
        dish.setId(dishDTO.getDishId());
        dish.setName(dishDTO.getName());
        dish.setPrice(dishDTO.getPrice());
        dish.setDishType(convertToEntity(dishDTO.getDishType()));
        dish.setCuisineType(dishDTO.getCuisineType());
        dish.setIngredients(convertIngredientDTOListToEntity(dishDTO.getIngredients()));
        return dish;
    }

    public static RestaurantDTO convertToDTO(Restaurant restaurant) {
        return RestaurantDTO.builder()
                .restaurantId(restaurant.getId())
                .placeId(restaurant.getPlaceId())
                .name(restaurant.getName())
                .address(restaurant.getAddress())
                .latitude(restaurant.getLatitude())
                .longitude(restaurant.getLongitude())
                .rating(restaurant.getRating())
                .priceLevel(restaurant.getPriceLevel())
                .website(restaurant.getWebsite())
                .photoRef(restaurant.getPhotoRef())
                .cuisineType(restaurant.getCuisineType())
                .categories(Convertor.convertCategoryEntityListToDTO(restaurant.getCategories()))
                .dishes(Convertor.convertDishEntityListToDTO(restaurant.getDishes()))
                .build();
    }

    public static CategoryDTO convertToDTO(Category category) {
        return CategoryDTO.builder()
                .categoryId(category.getId())
                .name(category.getName())
                .build();
    }

    public static IngredientDTO convertToDTO(Ingredient ingredient) {
        return IngredientDTO.builder()
                .ingredientId(ingredient.getId())
                .name(ingredient.getName())
                .build();
    }

    public static DishTypeDTO convertToDTO(DishType dishType) {
        return DishTypeDTO.builder()
                .dishTypeId(Long.valueOf(dishType.getId()))
                .name(dishType.getName())
                .build();
    }

    public static UserDTO convertToDTO(User user) {
        return UserDTO.builder()
                .userId(user.getId())
                .username(user.getUsername())
                .email(user.getEmail())
                .picture(user.getPicture())
                .build();
    }

    public static DishDTO convertToDTO(Dish dish) {
        return DishDTO.builder()
                .dishId(dish.getId())
                .name(dish.getName())
                .price(dish.getPrice())
                .dishType(convertToDTO(dish.getDishType()))
                .cuisineType(dish.getCuisineType())
                .ingredients(convertIngredientEntityListToDTO(dish.getIngredients()))
                .build();
    }

    public static ReviewDTO convertToDTO(Review review) {
        return ReviewDTO.builder()
                .reviewId(review.getId())
                .text(review.getText())
                .time(review.getTime())
                .restaurantDTO(convertToDTO(review.getRestaurant()))
                .userDTO(convertToDTO(review.getUser()))
                .build();
    }

    public static List<ReviewDTO> convertReviewEntityListToDTO(List<Review> reviews){
        List<ReviewDTO> reviewDTOS = new ArrayList<>();
        for (Review review: reviews){
            reviewDTOS.add(convertToDTO(review));
        }
        return reviewDTOS;
    }

    public static List<RestaurantDTO> convertRestaurantEntityListToDTO(List<Restaurant> restaurants){
        List<RestaurantDTO> restaurantDTOS = new ArrayList<>();
        for (Restaurant restaurant: restaurants){
            restaurantDTOS.add(convertToDTO(restaurant));
        }
        return restaurantDTOS;
    }

    public static List<DishDTO> convertDishEntityListToDTO(List<Dish> dishes){
        List<DishDTO> dishDTO = new ArrayList<>();
        for (Dish dish: dishes){
            dishDTO.add(convertToDTO(dish));
        }
        return dishDTO;
    }

    public static List<IngredientDTO> convertIngredientEntityListToDTO(List<Ingredient> ingredients){
        List<IngredientDTO> dishDTO = new ArrayList<>();
        for (Ingredient ingredient: ingredients){
            dishDTO.add(convertToDTO(ingredient));
        }
        return dishDTO;
    }

    public static List<CategoryDTO> convertCategoryEntityListToDTO(List<Category> categories){
        List<CategoryDTO> categoryDTOS = new ArrayList<>();
        for (Category category: categories){
            categoryDTOS.add(convertToDTO(category));
        }
        return categoryDTOS;
    }

    public static List<Category> convertCategoryDTOListToEntity(List<CategoryDTO> categoryDTOS){
        List<Category> categories = new ArrayList<>();
        for (CategoryDTO category: categoryDTOS){
            categories.add(convertToEntity(category));
        }
        return categories;
    }

    public static List<Dish> convertDishDTOListToEntity(List<DishDTO> dishDTOS){
        List<Dish> dishes = new ArrayList<>();
        for (DishDTO dish: dishDTOS){
            dishes.add(convertToEntity(dish));
        }
        return dishes;
    }

    public static List<Ingredient> convertIngredientDTOListToEntity(List<IngredientDTO> ingredientDTOS){
        List<Ingredient> ingredients = new ArrayList<>();
        for (IngredientDTO ingredientDTO: ingredientDTOS){
            ingredients.add(convertToEntity(ingredientDTO));
        }
        return ingredients;
    }
}

