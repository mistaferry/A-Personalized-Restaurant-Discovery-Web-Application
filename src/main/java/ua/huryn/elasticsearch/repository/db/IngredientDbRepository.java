package ua.huryn.elasticsearch.repository.db;

import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import ua.huryn.elasticsearch.entity.db.Dish;
import ua.huryn.elasticsearch.entity.db.Ingredient;

import java.util.List;
import java.util.Optional;

public interface IngredientDbRepository extends JpaRepository<Ingredient, Long> {
    @Query("SELECT i FROM Ingredient i")
    List<Ingredient> getAll();

    @Query(nativeQuery = true, value = "select i.ingredient_id, i.name\n" +
            "from dish d\n" +
            "         join db.dish_ingredient di on d.dish_id = di.dish_id\n" +
            "         join db.ingredient i on di.ingredient_id = i.ingredient_id\n" +
            "where d.dish_id = :dish_id")
    Optional<List<Ingredient>> findIngredientsByDishId(@Param("dish_id") Long dish_id);

    Ingredient findByName(String name);

    @Modifying
    @Transactional
    @Query("UPDATE Ingredient i SET i.name = :name WHERE i.id = :id")
    void updateIngredientById(@Param("id") Long ingredientId, @Param("name") String name);

    @Modifying
    @Transactional
    @Query(nativeQuery = true, value = "DELETE FROM dish_ingredient di WHERE di.dish_id = :dish_id AND di.ingredient_id = :ingredient_id")
    void deleteIngredientToDish(@Param("dish_id") Long dishId, @Param("ingredient_id") Long ingredientId);
}
