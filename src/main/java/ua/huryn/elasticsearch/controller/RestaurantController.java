//package ua.huryn.elasticsearch.controller;
//
//import com.vaadin.flow.server.auth.AnonymousAllowed;
//import lombok.RequiredArgsConstructor;
//import org.springframework.web.bind.annotation.*;
//import ua.huryn.elasticsearch.entity.dto.RestaurantDTO;
//import ua.huryn.elasticsearch.service.RestaurantService;
//
//import java.util.List;
//
//@RestController
//@RequestMapping("/api/restaurants")
//@RequiredArgsConstructor
//public class RestaurantController {
//    private final RestaurantService restaurantService;
//
//    @GetMapping("/search")
//    @AnonymousAllowed
//    public List<RestaurantDTO> searchRestaurants(
//            @RequestParam("string") String string
//    ) {
//        List<RestaurantDTO> restaurants = restaurantService.getRestaurantsDTOBySearchInEngAndUkr(string, null);
//        System.out.println("Found restaurants: " + restaurants.size());
//        return restaurants;
//    }
//}
