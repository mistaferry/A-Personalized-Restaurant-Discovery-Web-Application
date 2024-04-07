package ua.huryn.elasticsearch.service;

import java.io.IOException;
import java.util.List;

import com.google.maps.errors.ApiException;
import com.google.maps.model.PlacesSearchResponse;
import ua.huryn.elasticsearch.model.Restaurant;

public interface RestaurantService {
    List<Restaurant> findByName(String name);

    List<Restaurant> findByRating(int rating);

    List<Restaurant> findByLatitudeAndLongitude(double latitude, double longitude);

    PlacesSearchResponse getAllRestaurantsFromApi() throws IOException, InterruptedException, ApiException;

    List<Restaurant> getAll() throws IOException, InterruptedException, ApiException;

    void addRestaurantsToDb() throws IOException, InterruptedException, ApiException;
}
