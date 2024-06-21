package ua.huryn.elasticsearch.repository.elasticsearch;

import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.stereotype.Repository;
import ua.huryn.elasticsearch.entity.model.DishModel;

@Repository
public interface DishRepository extends ElasticsearchRepository<DishModel, Long> {
}
