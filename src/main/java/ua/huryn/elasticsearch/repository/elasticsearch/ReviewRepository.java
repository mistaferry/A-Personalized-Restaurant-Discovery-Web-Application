package ua.huryn.elasticsearch.repository.elasticsearch;

import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.stereotype.Repository;
import ua.huryn.elasticsearch.entity.model.ReviewModel;

@Repository
public interface ReviewRepository extends ElasticsearchRepository<ReviewModel, Long> {
}
