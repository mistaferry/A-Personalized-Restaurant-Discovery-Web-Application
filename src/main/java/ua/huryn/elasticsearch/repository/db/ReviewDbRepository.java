package ua.huryn.elasticsearch.repository.db;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import ua.huryn.elasticsearch.entity.db.Restaurant;
import ua.huryn.elasticsearch.entity.db.Review;

import java.util.List;

public interface ReviewDbRepository extends JpaRepository<Review,Integer> {
    @Query("SELECT r FROM Review r")
    List<Review> getAll();

    List<Review> getReviewByRestaurant(Restaurant restaurant);
}
