package ua.huryn.elasticsearch.repository.db;

import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import ua.huryn.elasticsearch.entity.db.Dish;
import ua.huryn.elasticsearch.entity.db.Restaurant;

import java.util.List;

@Repository
public interface RestaurantDbRepository extends JpaRepository<Restaurant,Integer> {
    Restaurant findById(Long id);
    List<Restaurant> findByName(String name);

    Restaurant findByPlaceId(String placeId);

    List<Restaurant> findByRating(double rating);

    List<Restaurant> findByLatitudeAndLongitude(double latitude, double longitude);

    List<Restaurant> findByPriceLevel(int priceLevel);

    List<Restaurant> findByCuisineType(String cuisineType);

    List<Restaurant> findByRatingAndPriceLevel(double rating, int priceLevel);

    @EntityGraph(attributePaths = {"categories"})
    @Query("SELECT r FROM Restaurant r")
    List<Restaurant> getAll();

//    void updateRestaurantByDishes(List<Dish> dishes);
//    Object findByPlaceId(String placeId);
}
