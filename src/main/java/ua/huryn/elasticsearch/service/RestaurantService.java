package ua.huryn.elasticsearch.service;

import java.awt.*;
import java.io.IOException;
import java.util.List;

import com.google.maps.errors.ApiException;
import ua.huryn.elasticsearch.model.RestaurantModel;

public interface RestaurantService {
    List<RestaurantModel> findAllByRestaurantId(int id);

    List<RestaurantModel> findByName(String name);

    List<RestaurantModel> findByRating(double rating);

    List<RestaurantModel> findByLatitudeAndLongitude(double latitude, double longitude);

    List<RestaurantModel> findByPrice_level(int priceLevel);

    List<RestaurantModel> findByCuisineType(String cuisineType);

    List<RestaurantModel> findByRatingAndPrice_level(double rating, int priceLevel);

//    List<RestaurantModel> getAll();

    List<RestaurantModel> getFiltered(List<String> cuisineTypes, List<Integer> rating, List<Integer> price, List<Integer> routes, String firstPoint);

    List<RestaurantModel> getRestaurantInGivenDistance(String firstPoint, List<Integer> routes, List<RestaurantModel> filtered);

    Image getRestaurantImage(RestaurantModel restaurantModel);


//    List<RestaurantModel> findByCategory(Category category);

    List<String> getCuisineTypeFromJson();

    void addDataToDb() throws IOException, InterruptedException, ApiException;

    void addApiDataToFile();

    void addDataFromFileToDb();
}
