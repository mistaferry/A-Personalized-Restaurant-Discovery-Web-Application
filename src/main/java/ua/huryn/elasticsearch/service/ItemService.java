package ua.huryn.elasticsearch.service;

import java.util.List;

import ua.huryn.elasticsearch.model.Item;

public interface ItemService {
    List<Item> findByName(String itemName);

    List<Item> findByCategory(String category);

    List<Item> findByPriceBetween(double low, double high);

    Iterable<Item> findAll();
}
