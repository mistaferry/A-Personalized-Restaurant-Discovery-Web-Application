package ua.huryn.elasticsearch.service;

import com.google.maps.errors.ApiException;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import ua.huryn.elasticsearch.model.Restaurant;

import java.io.IOException;

public interface DbInsertRepository extends JpaRepository<Restaurant,Long> {
    @Modifying
    @Query(value = "INSERT INTO restaurant VALUES " +
            "(:#{#restaurant.id}, :#{#restaurant.name}, :#{#restaurant.latitude}, :#{#restaurant.longitude}, , :#{#restaurant.rating})", nativeQuery = true)
    public abstract void insert(@Param("restaurant") Restaurant restaurant) throws IOException, InterruptedException, ApiException;
}
