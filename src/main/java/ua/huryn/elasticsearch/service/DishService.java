package ua.huryn.elasticsearch.service;

import ua.huryn.elasticsearch.entity.db.Dish;
import ua.huryn.elasticsearch.entity.dto.DishDTO;
import ua.huryn.elasticsearch.entity.model.DishModel;

import java.util.List;
import java.util.Map;

public interface DishService {
    Map<Long, String> getAllDishesNames();

    void setAllDishListsData(Dish dish);

    List<DishDTO> getAll();

    DishDTO getByNameAndPrice(String info);

    List<DishDTO> getDishDTOBySearch(String searchString);

    List<DishModel> searchByDishInfo(List<String> parts);
}
