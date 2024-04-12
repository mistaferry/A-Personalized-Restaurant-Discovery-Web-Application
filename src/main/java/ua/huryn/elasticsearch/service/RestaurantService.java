package ua.huryn.elasticsearch.service;

import java.util.List;

import ua.huryn.elasticsearch.model.RestaurantModel;

public interface RestaurantService {
    List<RestaurantModel> findByName(String name);

    List<RestaurantModel> findByRating(int rating);

    List<RestaurantModel> findByLatitudeAndLongitude(double latitude, double longitude);

    void addDataToDb();
}
