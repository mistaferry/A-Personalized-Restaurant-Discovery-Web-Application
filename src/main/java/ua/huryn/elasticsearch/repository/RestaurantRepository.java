package ua.huryn.elasticsearch.repository;

import java.util.List;

import org.jetbrains.annotations.NotNull;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

import ua.huryn.elasticsearch.model.Restaurant;

public interface RestaurantRepository extends ElasticsearchRepository<Restaurant, Integer> {
    List<Restaurant> findByName(String name);

    List<Restaurant> findByRating(int rating);

    List<Restaurant> findByLatitudeAndLongitude(double latitude, double longitude);

    @NotNull
    List<Restaurant> findAll();
}
