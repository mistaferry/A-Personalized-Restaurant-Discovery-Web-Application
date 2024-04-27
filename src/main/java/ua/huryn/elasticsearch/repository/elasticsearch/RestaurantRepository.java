package ua.huryn.elasticsearch.repository.elasticsearch;

import java.util.List;

import org.jetbrains.annotations.NotNull;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.stereotype.Repository;
import ua.huryn.elasticsearch.entity.model.RestaurantModel;

@Repository
public interface RestaurantRepository extends ElasticsearchRepository<RestaurantModel, Long>{
    RestaurantModel findByRestaurantId(Long id);

//    List<RestaurantModel> findBySearchField(String search_field);

    List<RestaurantModel> findByPlaceId(String placeId);

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
