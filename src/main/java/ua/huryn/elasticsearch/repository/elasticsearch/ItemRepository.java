package ua.huryn.elasticsearch.repository.elasticsearch;

import java.util.List;

import org.jetbrains.annotations.NotNull;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import ua.huryn.elasticsearch.entity.model.Item;

public interface ItemRepository extends ElasticsearchRepository<Item, Long> {
    List<Item> findByName(String name);

    List<Item> findByCategory(String category);

    List<Item> findByPriceBetween(double low, double high);

    @NotNull
    Iterable<Item> findAll();
}
