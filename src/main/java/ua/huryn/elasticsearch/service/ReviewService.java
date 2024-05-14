package ua.huryn.elasticsearch.service;

import ua.huryn.elasticsearch.entity.db.Review;
import ua.huryn.elasticsearch.entity.dto.RestaurantDTO;
import ua.huryn.elasticsearch.entity.dto.ReviewDTO;
import ua.huryn.elasticsearch.entity.model.RestaurantModel;
import ua.huryn.elasticsearch.entity.model.ReviewModel;

import java.util.List;

public interface ReviewService {
    List<ReviewDTO> getAll();

    List<ReviewDTO> getReviewsByRestaurant(RestaurantDTO restaurantDTO);

    void saveToDb(Review review);

    List<ReviewModel> findReviewsByKeywords(List<String> keywords);
}
