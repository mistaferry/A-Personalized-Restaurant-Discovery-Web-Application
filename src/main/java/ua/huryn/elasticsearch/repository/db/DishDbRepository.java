package ua.huryn.elasticsearch.repository.db;

import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import ua.huryn.elasticsearch.entity.db.Dish;

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

    @Query(nativeQuery = true, value = "SELECT MAX(dish_id) FROM dish")
    Long findMaxId();

    @Transactional
    @Modifying
    @Query(nativeQuery = true, value = "INSERT INTO dish (name, price, dish_type, cuisine_type)\n" +
            "    VALUE (:name, :price, :dish_type, :cuisine_type)")
    void addDish(@Param("name") String name, @Param("price") Double price, @Param("dish_type") Long dishType, @Param("cuisine_type") String cuisineType);

    @EntityGraph(attributePaths = {"ingredients", "dishType"})
    @Query("SELECT d FROM Dish d where d.name=:name and d.price=:price")
    List<Dish> findByNameAndPrice(@Param("name") String name, @Param("price") Double price);

    @Modifying
    @Transactional
    @Query("UPDATE Dish d SET d.name = :name, d.price = :price WHERE d.id = :id")
    void updateDishById(@Param("id") Long dishId, @Param("name") String name, @Param("price") Double price);

    @Transactional
    @Modifying
    @Query(nativeQuery = true, value = "INSERT INTO dish_ingredient (dish_id, ingredient_id)\n" +
            "    VALUE (:dish_id, :ingredient_id)")
    void addIngredientToDish(@Param("dish_id") Long dish_id, @Param("ingredient_id") Long ingredient_id);

    @Transactional
    @Modifying
    @Query(nativeQuery = true, value = "INSERT INTO restaurant_dish (dish_id, restaurant_id)\n" +
            "    VALUE (:dish_id, :restaurant_id)")
    void addDishToRestaurant(@Param("dish_id") Long dish_id, @Param("restaurant_id") Long restaurant_id);

    @Transactional
    @Modifying
    @Query(nativeQuery = true, value = "DELETE FROM restaurant_dish rd WHERE rd.dish_id = :dish_id AND rd.restaurant_id = :restaurant_id")
    void deleteDishFromRestaurant(@Param("dish_id") Long dishId, @Param("restaurant_id") Long restaurantId);
}
