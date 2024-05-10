package ua.huryn.elasticsearch.service.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import ua.huryn.elasticsearch.entity.db.Restaurant;
import ua.huryn.elasticsearch.entity.db.Review;
import ua.huryn.elasticsearch.entity.dto.RestaurantDTO;
import ua.huryn.elasticsearch.entity.dto.ReviewDTO;
import ua.huryn.elasticsearch.repository.db.RestaurantDbRepository;
import ua.huryn.elasticsearch.repository.db.ReviewDbRepository;
import ua.huryn.elasticsearch.service.ReviewService;
import ua.huryn.elasticsearch.utils.Convertor;

import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class ReviewServiceImpl implements ReviewService {
    private final RestaurantDbRepository restaurantDbRepository;
    private final ReviewDbRepository reviewDbRepository;

    @Override
    public List<ReviewDTO> getAll() {
        return Convertor.convertReviewEntityListToDTO(reviewDbRepository.getAll());
    }

    @Override
    public List<ReviewDTO> getReviewsByRestaurant(RestaurantDTO restaurantDTO) {
        Restaurant restaurant = restaurantDbRepository.findById(restaurantDTO.getRestaurantId());
        List<Review> reviews = reviewDbRepository.getReviewByRestaurant(restaurant);
        return Convertor.convertReviewEntityListToDTO(reviews);
    }

    @Override
    public void saveToDb(Review review){
        reviewDbRepository.save(review);
    }
}
