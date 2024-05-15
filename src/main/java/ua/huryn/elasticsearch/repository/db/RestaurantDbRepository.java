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

    @Query(nativeQuery = true, value = "SELECT r.*\n" +
            "    from restaurant r\n" +
            "    right join db.restaurant_dish rd on r.restaurant_id = rd.restaurant_id\n" +
            "    right join db.dish d on d.dish_id = rd.dish_id\n" +
            "    where d.name =:dish_name")
    List<Restaurant> findByDishName(@Param("dish_name") String dishName);

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

    @Query(nativeQuery = true, value = "SELECT DISTINCT r.*\n" +
            "FROM restaurant r\n" +
            "         RIGHT JOIN db.restaurant_dish rd ON r.restaurant_id = rd.restaurant_id\n" +
            "         RIGHT JOIN db.dish d ON d.dish_id = rd.dish_id\n" +
            "         RIGHT JOIN db.dish_ingredient di ON d.dish_id = di.dish_id\n" +
            "         RIGHT JOIN db.ingredient i ON i.ingredient_id = di.ingredient_id\n" +
            "WHERE i.name =:ingredient_name")
    Optional<List<Restaurant>> findRestaurantByIngredientName(@Param("ingredient_name") String ingredientName);
}
