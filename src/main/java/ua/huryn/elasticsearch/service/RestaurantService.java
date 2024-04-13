package ua.huryn.elasticsearch.service;

import java.io.IOException;
import java.util.List;

import com.google.maps.errors.ApiException;
import ua.huryn.elasticsearch.model.RestaurantModel;

public interface RestaurantService {
    List<RestaurantModel> findByName(String name);

    List<RestaurantModel> findByRating(int rating);

    List<RestaurantModel> findByLatitudeAndLongitude(double latitude, double longitude);

    void addDataToDb() throws IOException, InterruptedException, ApiException;

    void addApiDataToFile();

    void addDataFromFileToDb();
}
