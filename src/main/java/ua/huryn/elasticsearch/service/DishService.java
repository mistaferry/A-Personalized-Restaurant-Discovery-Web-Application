package ua.huryn.elasticsearch.service;

import ua.huryn.elasticsearch.entity.db.Dish;

import java.util.Map;

public interface DishService {
    Map<Long, String> getAllDishesNames();

    void setAllDishListsData(Dish dish);

}
