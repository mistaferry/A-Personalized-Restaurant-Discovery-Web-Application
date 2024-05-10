package ua.huryn.elasticsearch.controller;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import ua.huryn.elasticsearch.entity.model.Item;
import ua.huryn.elasticsearch.repository.elasticsearch.ItemRepository;
import ua.huryn.elasticsearch.repository.elasticsearch.RestaurantRepository;
import ua.huryn.elasticsearch.service.ItemService;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/v1/items")
@RequiredArgsConstructor
public class ItemController {
    private final ItemService itemService;

    @GetMapping("{name}")
    public List<Item> getItemByName(@PathVariable("name") String name) {
        return itemService.findByName(name);
    }
}
