package ua.huryn.elasticsearch.service;

import java.awt.*;
import java.io.IOException;
import java.util.List;

import com.google.maps.errors.ApiException;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import ua.huryn.elasticsearch.entity.db.Restaurant;
import ua.huryn.elasticsearch.entity.db.Review;
import ua.huryn.elasticsearch.entity.dto.RestaurantDTO;
import ua.huryn.elasticsearch.entity.dto.ReviewDTO;
import ua.huryn.elasticsearch.entity.model.RestaurantModel;
import ua.huryn.elasticsearch.entity.model.ReviewModel;

public interface RestaurantService {
    RestaurantDTO findByRestaurantId(Long id);

    List<RestaurantDTO> findByName(String name);

    List<RestaurantDTO> findByRating(double rating);

    List<RestaurantDTO> findByLatitudeAndLongitude(double latitude, double longitude);

    List<RestaurantDTO> findByPrice_level(int priceLevel);

    List<RestaurantDTO> findByCuisineType(String cuisineType);

    List<RestaurantDTO> findByRatingAndPrice_level(double rating, int priceLevel);

//    @Query(nativeQuery = true, value = "SELECT r.*\n" +
//            "from restaurant r\n" +
//            "         right join db.restaurant_dish rd on r.restaurant_id = rd.restaurant_id\n" +
//            "right join db.dish d on d.dish_id = rd.dish_id\n" +
//            "where d.name =:dish_name")
//    List<Restaurant> findRestaurantsByDishName(@Param("dish_name") String dishName);

    List<RestaurantDTO> getAll();

    List<RestaurantDTO> getFiltered(List<String> cuisineTypes, List<Integer> rating, List<Integer> price, List<Integer> routes, List<String> dishes, List<String> ingredients, String routeDeparturePoint, String fullTextSearch, String keywords);

    List<RestaurantDTO> getRestaurantInGivenDistance(String firstPoint, List<Integer> routes, List<RestaurantDTO> filtered);

    Image getRestaurantImage(RestaurantDTO RestaurantDTO);

    List<String> getCuisineType();

    List<ReviewModel> searchByReviewKeywords(List<String> parts);

    List<RestaurantDTO> getRestaurantsDTOBySearchInEngAndUkr(String searchString, List<RestaurantDTO> restaurantList);

    List<RestaurantModel> fullTextSearchInEngAndUkr(String text);

    List<ReviewDTO> getReviewsDTOByKeywords(String searchString);

    void addDataToDb() throws IOException, InterruptedException, ApiException;

    void addApiDataToFile();

    void addDataFromFileToDb();

    void setAllRestaurantListsData(Restaurant restaurant);
}