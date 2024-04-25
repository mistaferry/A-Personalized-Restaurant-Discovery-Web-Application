package ua.huryn.elasticsearch.utils;

import ua.huryn.elasticsearch.entity.db.Category;
import ua.huryn.elasticsearch.entity.db.Restaurant;
import ua.huryn.elasticsearch.entity.dto.CategoryDTO;
import ua.huryn.elasticsearch.entity.dto.RestaurantDTO;

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

    public static Category convertToEntity(CategoryDTO categoryDTO) {
        Category category = new Category();
        category.setId(categoryDTO.getCategoryId());
        category.setName(categoryDTO.getName());
        return category;
    }

    public static CategoryDTO convertToDTO(Category category) {
        return CategoryDTO.builder()
                .categoryId(category.getId())
                .name(category.getName())
                .build();
    }

    public static List<RestaurantDTO> convertEntityListToDTO(List<Restaurant> restaurants){
        List<RestaurantDTO> restaurantDTOS = new ArrayList<>();
        for (Restaurant restaurant: restaurants){
            restaurantDTOS.add(convertToDTO(restaurant));
        }
        return restaurantDTOS;
    }
}

