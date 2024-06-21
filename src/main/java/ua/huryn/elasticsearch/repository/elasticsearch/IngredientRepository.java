package ua.huryn.elasticsearch.repository.elasticsearch;

import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.stereotype.Repository;
import ua.huryn.elasticsearch.entity.model.IngredientModel;
import ua.huryn.elasticsearch.entity.model.RestaurantModel;

@Repository
public interface IngredientRepository extends ElasticsearchRepository<IngredientModel, Long> {
}
