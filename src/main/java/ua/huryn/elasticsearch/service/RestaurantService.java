package ua.huryn.elasticsearch.service;

import java.awt.*;
import java.io.IOException;
import java.util.List;
import java.util.Map;

import com.google.maps.errors.ApiException;
import ua.huryn.elasticsearch.entity.db.Restaurant;
import ua.huryn.elasticsearch.entity.dto.RestaurantDTO;
import ua.huryn.elasticsearch.entity.model.RestaurantModel;

public interface RestaurantService {
    RestaurantDTO findByRestaurantId(Long id);

    List<RestaurantDTO> findByName(String name);

    List<RestaurantDTO> findByRating(double rating);

    List<RestaurantDTO> findByLatitudeAndLongitude(double latitude, double longitude);

    List<RestaurantDTO> findByPrice_level(int priceLevel);

    List<RestaurantDTO> findByCuisineType(String cuisineType);

    List<RestaurantDTO> findByRatingAndPrice_level(double rating, int priceLevel);

    List<RestaurantDTO> getAll();

    List<RestaurantDTO> getFiltered(List<String> cuisineTypes, List<Integer> rating, List<Integer> price, List<Integer> routes, List<String> dishes, List<String> ingredients, String routeDeparturePoint, String fullTextSearch);

    List<RestaurantDTO> getRestaurantInGivenDistance(String firstPoint, List<Integer> routes, List<RestaurantDTO> filtered);

    Image getRestaurantImage(RestaurantDTO RestaurantDTO);

    List<String> getCuisineType();

    List<RestaurantModel> findRestaurantsBySearchString(List<String> parts);

    List<RestaurantDTO> getRestaurantsDTOBySearchString(String searchString, List<RestaurantDTO> restaurantList);

    void addDataToDb() throws IOException, InterruptedException, ApiException;

    void addApiDataToFile();

    void addDataFromFileToDb();

    void setAllRestaurantListsData(Restaurant restaurant);
}