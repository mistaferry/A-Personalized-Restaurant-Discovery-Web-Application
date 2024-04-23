package ua.huryn.elasticsearch.repository.elasticsearch;

import java.util.ArrayList;
import java.util.List;

import org.jetbrains.annotations.NotNull;
import org.springframework.data.domain.Sort;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import ua.huryn.elasticsearch.entity.Restaurant;
import ua.huryn.elasticsearch.model.RestaurantModel;

@Repository
public interface RestaurantRepository extends ElasticsearchRepository<RestaurantModel, Long>{
    List<RestaurantModel> findAllByRestaurantId(int id);

    List<RestaurantModel> findByName(String name);

    List<RestaurantModel> findByRating(double rating);

    List<RestaurantModel> findByLatitudeAndLongitude(double latitude, double longitude);

    List<RestaurantModel> findByPriceLevel(int price_level);

    List<RestaurantModel> findByCuisineType(String cuisineType);

    List<RestaurantModel> findByRatingAndPriceLevel(double rating, int price_level);

    @NotNull
    @Override
    List<RestaurantModel> findAll();
}
