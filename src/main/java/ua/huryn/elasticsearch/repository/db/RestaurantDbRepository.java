package ua.huryn.elasticsearch.repository.db;

import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import ua.huryn.elasticsearch.entity.Restaurant;

import java.util.List;

@Repository
public interface RestaurantDbRepository extends JpaRepository<Restaurant,Integer> {
    @EntityGraph(attributePaths = {"categories"})
    @Query("SELECT r FROM Restaurant r")
    List<Restaurant> getAll();

    Object findByPlaceId(String placeId);
}
