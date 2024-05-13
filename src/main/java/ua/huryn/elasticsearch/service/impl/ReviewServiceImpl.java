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
import ua.huryn.elasticsearch.service.RestaurantService;
import ua.huryn.elasticsearch.service.ReviewService;
import ua.huryn.elasticsearch.utils.Convertor;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j
@RequiredArgsConstructor
public class ReviewServiceImpl implements ReviewService {
    private final RestaurantDbRepository restaurantDbRepository;
    private final ReviewDbRepository reviewDbRepository;
    private final RestaurantService restaurantService;

    @Override
    public List<ReviewDTO> getAll() {
        return Convertor.convertReviewEntityListToDTO(reviewDbRepository.getAll());
    }

    @Override
    public List<ReviewDTO> getReviewsByRestaurant(RestaurantDTO restaurantDTO) {
        Restaurant restaurant = restaurantDbRepository.findById(restaurantDTO.getRestaurantId());
        List<Review> reviews = reviewDbRepository.getReviewByRestaurantId(restaurantDTO.getRestaurantId());
        restaurantService.setAllRestaurantListsData(restaurant);
        for (Review review: reviews){
            review.setRestaurant(restaurant);
        }
        reviews = sortReviewsByTime(reviews);

        return Convertor.convertReviewEntityListToDTO(reviews);
    }

    private List<Review> sortReviewsByTime(List<Review> reviews) {
        return reviews.stream()
                .sorted(Comparator.comparing(Review::getTime).reversed()) // Сортуємо за датою у зворотньому порядку
                .collect(Collectors.toList());
    }

    @Override
    public void saveToDb(Review review){
        reviewDbRepository.save(review);
    }
}
