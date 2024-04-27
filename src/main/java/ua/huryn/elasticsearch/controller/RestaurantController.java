package ua.huryn.elasticsearch.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ua.huryn.elasticsearch.entity.dto.RestaurantDTO;
import ua.huryn.elasticsearch.service.RestaurantService;

import java.util.List;

@RestController
@RequestMapping("/restaurants")
@RequiredArgsConstructor
public class RestaurantController {
    private final RestaurantService restaurantService;

//    @GetMapping("/{name}")
//    public List<RestaurantDTO> getItemByName(@PathVariable("name") String name) {
//        return restaurantService.searchElasticByNameAndAddress(name);
//    }

    @GetMapping("/all")
    public List<RestaurantDTO> findAll(){
        return restaurantService.getAll();
    }
}
