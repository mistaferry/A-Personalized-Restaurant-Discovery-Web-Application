package ua.huryn.elasticsearch.repository;

import java.util.List;

import org.jetbrains.annotations.NotNull;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

import ua.huryn.elasticsearch.model.RestaurantModel;

public interface RestaurantRepository extends ElasticsearchRepository<RestaurantModel, Integer> {
    List<RestaurantModel> findByName(String name);

    List<RestaurantModel> findByRating(int rating);

    List<RestaurantModel> findByLatitudeAndLongitude(double latitude, double longitude);

    @NotNull
    List<RestaurantModel> findAll();
}
