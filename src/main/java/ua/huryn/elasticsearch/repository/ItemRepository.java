package ua.huryn.elasticsearch.repository;

import java.util.List;

import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

import ua.huryn.elasticsearch.model.Item;

public interface ItemRepository extends ElasticsearchRepository<Item, Integer> {
    List<Item> findByName(String name);

    List<Item> findByCategory(String category);

    List<Item> findByPriceBetween(double low, double high);
}
