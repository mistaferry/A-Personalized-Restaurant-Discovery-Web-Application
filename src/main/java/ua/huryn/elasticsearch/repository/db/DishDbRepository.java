package ua.huryn.elasticsearch.repository.db;

import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import ua.huryn.elasticsearch.entity.db.Dish;
import ua.huryn.elasticsearch.entity.db.Ingredient;
import ua.huryn.elasticsearch.entity.db.Restaurant;

import java.util.List;
import java.util.Optional;

public interface DishDbRepository extends JpaRepository<Dish,Long> {
    @EntityGraph(attributePaths = {"ingredients", "dishType"})
    @Query("SELECT d FROM Dish d where d.id=:id")
    Optional<Dish> findById(@Param("id") Long id);

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

    @Transactional
    @Modifying
    @Query(nativeQuery = true, value = "INSERT INTO dish_ingredient (dish_id, ingredient_id)\n" +
            "    VALUE (:dish_id, :ingredient_id)")
    void addIngredientToDish(@Param("dish_id") Long dish_id, @Param("ingredient_id") Long ingredient_id);
}
