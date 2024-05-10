package ua.huryn.elasticsearch.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import ua.huryn.elasticsearch.entity.db.Restaurant;
import ua.huryn.elasticsearch.entity.dto.RestaurantDTO;
import ua.huryn.elasticsearch.entity.model.RestaurantModel;
import ua.huryn.elasticsearch.service.RestaurantService;

import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping("/api/restaurants")
@RequiredArgsConstructor
public class RestaurantController {
    private final RestaurantService restaurantService;

    @GetMapping("/search")
    public List<RestaurantDTO> searchRestaurants(
            @RequestParam("string") String string
    ) {
        List<RestaurantDTO> restaurants = restaurantService.getRestaurantsDTOBySearchString(string, null);
        System.out.println("Found restaurants: " + restaurants.size());
        return restaurants;
    }
}
