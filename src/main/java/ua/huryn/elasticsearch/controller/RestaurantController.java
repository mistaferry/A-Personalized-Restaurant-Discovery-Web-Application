package ua.huryn.elasticsearch.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ua.huryn.elasticsearch.model.Item;
import ua.huryn.elasticsearch.model.RestaurantModel;
import ua.huryn.elasticsearch.service.ItemService;
import ua.huryn.elasticsearch.service.RestaurantService;

import java.util.List;

@RestController
@RequestMapping("/restaurants")
@RequiredArgsConstructor
public class RestaurantController {
    private final RestaurantService restaurantService;

    @GetMapping("/{name}")
    public List<RestaurantModel> getItemByName(@PathVariable("name") String name) {
        return restaurantService.findByName(name);
    }

    @GetMapping("/all")
    public List<RestaurantModel> findAll(){
        return restaurantService.getAll();
    }
}
