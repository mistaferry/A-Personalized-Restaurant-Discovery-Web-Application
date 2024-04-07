package ua.huryn.elasticsearch.service.impl;

import com.google.maps.errors.ApiException;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ua.huryn.elasticsearch.model.Restaurant;
import ua.huryn.elasticsearch.service.DbInsertService;

import java.io.IOException;

public abstract class DbInsertServiceImpl implements DbInsertService {
    RestaurantServiceImpl restaurantService;
    @Override
    public void insert(Restaurant restaurant) throws IOException, InterruptedException, ApiException {

    }
}
