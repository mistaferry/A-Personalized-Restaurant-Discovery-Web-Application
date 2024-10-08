package ua.huryn.elasticsearch.repository.elasticsearch;

import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.stereotype.Repository;
import ua.huryn.elasticsearch.entity.model.RestaurantModel;

@Repository
public interface RestaurantRepository extends ElasticsearchRepository<RestaurantModel, Long>{
}
