package ua.huryn.elasticsearch.controller;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import ua.huryn.elasticsearch.model.Item;
import ua.huryn.elasticsearch.service.ItemService;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/v1/items")
@RequiredArgsConstructor
public class ItemController {
    private final ItemService itemService;

    @GetMapping("/all")
    public Iterable<Item> getAll(){
        return itemService.findAll();
    }

    @GetMapping("/{name}")
    public List<Item> getItemByName(@PathVariable("name") String name) {
        return itemService.findByName(name);
    }

    @GetMapping("/category/{category}")
    public List<Item> getItemsByCategory(@PathVariable("category") String category) {
        return itemService.findByCategory(category);
    }

    @GetMapping("/prices/{low}/{high}")
    public List<Item> getItemsByPriceRange(@PathVariable("low") double low,
            @PathVariable("high") double high) {
        return itemService.findByPriceBetween(low, high);
    }
}
