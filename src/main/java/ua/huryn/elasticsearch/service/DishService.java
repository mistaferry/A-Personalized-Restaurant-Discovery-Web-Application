package ua.huryn.elasticsearch.service;

import ua.huryn.elasticsearch.entity.db.Dish;
import ua.huryn.elasticsearch.entity.db.Restaurant;

import java.util.Map;

public interface DishService {
    Map<Long, String> getAllDishesNames();

    void setAllDishListsData(Dish dish);

}
