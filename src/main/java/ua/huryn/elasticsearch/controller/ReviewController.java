package ua.huryn.elasticsearch.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import ua.huryn.elasticsearch.entity.model.ReviewModel;
import ua.huryn.elasticsearch.service.ReviewService;

import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping("/review")
@RequiredArgsConstructor
public class ReviewController {
    private final ReviewService reviewService;

    @GetMapping("/search")
    public List<ReviewModel> searchRestaurants(
            @RequestParam("text") String text
    ) {
        text = text.toLowerCase();
        String[] words = text.split("\\s+");
        List<String> wordList = Arrays.asList(words);
        List<ReviewModel> reviews = reviewService.findReviewsByKeywords(wordList);
        System.out.println("Found reviews: " + reviews.size());
        return reviews;
    }
}
