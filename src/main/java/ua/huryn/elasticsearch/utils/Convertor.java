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
        return restaurant;
    }

    public static Category convertToEntity(CategoryDTO categoryDTO) {
        Category category = new Category();
        category.setId(categoryDTO.getCategoryId());
        category.setName(categoryDTO.getName());
        return category;
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
                .build();
    }

    public static CategoryDTO convertToDTO(Category category) {
        return CategoryDTO.builder()
                .categoryId(category.getId())
                .name(category.getName())
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
                .build();
    }

    public static DishDTO convertToDTO(Dish dish) {
        return DishDTO.builder()
                .dishId(dish.getId())
                .name(dish.getName())
                .price(dish.getPrice())
                .dishType(convertToDTO(dish.getDishType()))
                .cuisineType(dish.getCuisineType())
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

    public static IngredientDTO convertToDTO(Ingredient ingredient) {
        return IngredientDTO.builder()
                .ingredientId(ingredient.getId())
                .name(ingredient.getName())
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
}

