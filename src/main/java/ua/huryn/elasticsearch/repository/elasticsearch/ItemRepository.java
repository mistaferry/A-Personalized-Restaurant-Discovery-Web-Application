package ua.huryn.elasticsearch.repository.elasticsearch;

import java.util.List;

import org.springframework.data.elasticsearch.annotations.Query;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import ua.huryn.elasticsearch.entity.model.Item;

public interface ItemRepository extends ElasticsearchRepository<Item, Long> {

    @Query("{\"wildcard\": {\"name\": \"*?0*\"}}")
    List<Item> findByName(String name);

}
