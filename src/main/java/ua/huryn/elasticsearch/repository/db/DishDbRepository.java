package ua.huryn.elasticsearch.repository.db;

import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import ua.huryn.elasticsearch.entity.db.Dish;
import ua.huryn.elasticsearch.entity.db.Restaurant;

import java.util.List;

public interface DishDbRepository extends JpaRepository<Dish,Integer> {
    Dish findById(Long id);

    List<Dish> findByName(String name);

    List<Dish> findByCuisineType(String cuisineType);

    @EntityGraph(attributePaths = {"ingredients", "dishType"})
    @Query("SELECT d FROM Dish d")
    List<Dish> getAll();
}
