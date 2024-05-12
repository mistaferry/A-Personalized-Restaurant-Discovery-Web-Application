package ua.huryn.elasticsearch.repository.db;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import ua.huryn.elasticsearch.entity.db.Category;

import java.util.List;

@Repository
public interface CategoryDbRepository extends JpaRepository<Category,Integer> {
    Category findByName(String name);

    @Query(nativeQuery = true, value = "select c.*\n" +
            "from restaurant r\n" +
            "         join db.restaurant_category rc on r.restaurant_id = rc.restaurant_id\n" +
            "         join db.category c on rc.category_id = c.category_id\n" +
            "where r.restaurant_id = :restaurant_id")
    List<Category> findByRestaurantId(@Param("restaurant_id") Long restaurantId);
}