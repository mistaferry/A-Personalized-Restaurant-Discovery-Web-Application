package ua.huryn.elasticsearch.repository1;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import ua.huryn.elasticsearch.entity.Restaurant;

@Repository
public interface RestaurantDbRepository extends JpaRepository<Restaurant,Integer> {
    @Query("SELECT r FROM Restaurant r WHERE r.place_id = :placeId")
    Restaurant findByPlace_id(@Param("placeId") String placeId);
}
