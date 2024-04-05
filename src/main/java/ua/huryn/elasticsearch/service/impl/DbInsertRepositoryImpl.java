package ua.huryn.elasticsearch.service.impl;

import com.google.maps.errors.ApiException;
import com.google.maps.model.PlacesSearchResponse;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import ua.huryn.elasticsearch.model.Restaurant;
import ua.huryn.elasticsearch.service.DbInsertRepository;

import java.io.IOException;

public abstract class DbInsertRepositoryImpl implements DbInsertRepository {
    RestaurantServiceImpl restaurantService;
    @Override
    public void insert(Restaurant restaurant) throws IOException, InterruptedException, ApiException {

    }
}
