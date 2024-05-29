package ua.huryn.elasticsearch.repository.db;

import org.springframework.data.jpa.repository.JpaRepository;
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
}
