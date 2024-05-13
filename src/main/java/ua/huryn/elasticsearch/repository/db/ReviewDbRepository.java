package ua.huryn.elasticsearch.repository.db;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import ua.huryn.elasticsearch.entity.db.Restaurant;
import ua.huryn.elasticsearch.entity.db.Review;

import java.util.List;

public interface ReviewDbRepository extends JpaRepository<Review,Integer> {
    @Query("SELECT r FROM Review r")
    List<Review> getAll();

    @Query(nativeQuery = true, value = "SELECT rev.*" +
            "FROM review rev\n" +
//            "JOIN restaurant res ON rev.restaurant_id = res.restaurant_id\n" +
//            "JOIN user usr ON rev.user_id = usr.user_id\n" +
            "WHERE rev.restaurant_id = :restaurant_id")
    List<Review> getReviewByRestaurantId(@Param("restaurant_id") Long restaurantId);
}
