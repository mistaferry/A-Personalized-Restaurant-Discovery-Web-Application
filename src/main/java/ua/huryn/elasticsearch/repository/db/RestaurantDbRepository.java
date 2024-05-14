package ua.huryn.elasticsearch.repository.db;

import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import ua.huryn.elasticsearch.entity.db.Dish;
import ua.huryn.elasticsearch.entity.db.Ingredient;
import ua.huryn.elasticsearch.entity.db.Restaurant;

import java.util.List;
import java.util.Optional;

@Repository
public interface RestaurantDbRepository extends JpaRepository<Restaurant,Integer> {
    Restaurant findById(Long id);

    List<Restaurant> findByName(String name);

    Restaurant findByPlaceId(String placeId);

    @Query(nativeQuery = true, value = "SELECT * FROM restaurant WHERE rating>=:start and rating<=:end")
    List<Restaurant> findByRating(@Param("start")double start, @Param("end")double end);

    List<Restaurant> findByLatitudeAndLongitude(double latitude, double longitude);

    List<Restaurant> findByPriceLevel(int priceLevel);

    List<Restaurant> findByCuisineType(String cuisineType);

    List<Restaurant> findByRatingAndPriceLevel(double rating, int priceLevel);



    @Query(nativeQuery = true, value = "SELECT r.restaurant_id\n" +
            "    FROM review r\n" +
            "    WHERE r.review_id = :review_id")
    Optional<Restaurant> findRestaurantsByReviewId(@Param("review_id") Long review_id);

    @EntityGraph(attributePaths = {"categories"})
    @Query("SELECT r FROM Restaurant r")
    List<Restaurant> getAll();
}
