package ua.huryn.elasticsearch.service.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.elasticsearch.index.query.BoolQueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.springframework.data.elasticsearch.core.ElasticsearchOperations;
import org.springframework.data.elasticsearch.core.SearchHit;
import org.springframework.data.elasticsearch.core.SearchHits;
import org.springframework.data.elasticsearch.core.query.StringQuery;
import org.springframework.stereotype.Service;
import ua.huryn.elasticsearch.entity.db.Restaurant;
import ua.huryn.elasticsearch.entity.db.Review;
import ua.huryn.elasticsearch.entity.dto.RestaurantDTO;
import ua.huryn.elasticsearch.entity.dto.ReviewDTO;
import ua.huryn.elasticsearch.entity.model.RestaurantModel;
import ua.huryn.elasticsearch.entity.model.ReviewModel;
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
    private final ElasticsearchOperations elasticsearchOperations;

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
                .sorted(Comparator.comparing(Review::getTime).reversed())
                .collect(Collectors.toList());
    }

    @Override
    public void saveToDb(Review review){
        reviewDbRepository.save(review);
    }

    @Override
    public List<ReviewModel> findReviewsByKeywords(List<String> keywords) {
        try {
            BoolQueryBuilder boolQuery = QueryBuilders.boolQuery();
            for (String keyword : keywords) {
                boolQuery.must(QueryBuilders.wildcardQuery("text", "*" + keyword + "*"));
            }

            String queryString = boolQuery.toString();
            StringQuery stringQuery = new StringQuery(queryString);

            SearchHits<ReviewModel> searchHits = elasticsearchOperations.search(
                    stringQuery, ReviewModel.class);

            return searchHits.stream()
                    .map(SearchHit::getContent)
                    .collect(Collectors.toList());
        } catch (Exception e) {
            log.error("Error during search: " + e.getMessage());
        }
        return null;
    }
}
