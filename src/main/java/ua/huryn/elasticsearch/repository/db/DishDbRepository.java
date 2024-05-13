package ua.huryn.elasticsearch.repository.db;

import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import ua.huryn.elasticsearch.entity.db.Dish;
import ua.huryn.elasticsearch.entity.db.Ingredient;
import ua.huryn.elasticsearch.entity.db.Restaurant;

import java.util.List;
import java.util.Optional;

public interface DishDbRepository extends JpaRepository<Dish,Long> {
    Optional<Dish> findById(Long id);

    List<Dish> findByCuisineType(String cuisineType);

    @EntityGraph(attributePaths = {"ingredients", "dishType"})
    @Query("SELECT d FROM Dish d")
    List<Dish> getAll();

    @Query(nativeQuery = true, value = "select d.*\n" +
            "from restaurant r\n" +
            "         join db.restaurant_dish re on r.restaurant_id = re.restaurant_id\n" +
            "         join db.dish d on re.dish_id = d.dish_id\n" +
            "where r.restaurant_id = :restaurant_id")
    List<Dish> findByRestaurantId(@Param("restaurant_id") Long restaurantId);
}
