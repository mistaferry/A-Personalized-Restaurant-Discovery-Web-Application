package ua.huryn.elasticsearch.service;

import com.google.maps.errors.ApiException;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import ua.huryn.elasticsearch.model.Restaurant;

import java.io.IOException;

public interface DbInsertService {
    @Modifying
    @Query(value = "INSERT INTO restaurant VALUES " +
            "(:#{#restaurant.id}, :#{#restaurant.name}, :#{#restaurant.latitude}, :#{#restaurant.longitude}, , :#{#restaurant.rating})", nativeQuery = true)
    public void insert(@Param("restaurant") Restaurant restaurant) throws IOException, InterruptedException, ApiException;
}
