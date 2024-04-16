package ua.huryn.elasticsearch.service;

import java.io.IOException;
import java.util.List;

import com.google.maps.errors.ApiException;
import ua.huryn.elasticsearch.entity.Category;
import ua.huryn.elasticsearch.model.RestaurantModel;

public interface RestaurantService {
    List<RestaurantModel> findByName(String name);

    List<RestaurantModel> findByRating(double rating);

    List<RestaurantModel> findByLatitudeAndLongitude(double latitude, double longitude);

    List<RestaurantModel> getAll();

//    List<RestaurantModel> findByCategory(Category category);

    void addDataToDb() throws IOException, InterruptedException, ApiException;

    void addApiDataToFile();

    void addDataFromFileToDb();
}
