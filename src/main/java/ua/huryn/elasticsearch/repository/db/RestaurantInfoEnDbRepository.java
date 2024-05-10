package ua.huryn.elasticsearch.repository.db;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ua.huryn.elasticsearch.entity.db.RestaurantInfoEn;

@Repository
public interface RestaurantInfoEnDbRepository extends JpaRepository<RestaurantInfoEn,Integer> {
}
