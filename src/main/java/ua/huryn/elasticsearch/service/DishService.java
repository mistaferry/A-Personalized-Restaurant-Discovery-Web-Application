package ua.huryn.elasticsearch.service;

import org.springframework.data.repository.query.Param;
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

    List<DishDTO> getByNameAndPrice(String name, Double price);

    List<DishDTO> getDishDTOBySearch(String searchString);

    List<DishModel> searchByDishInfo(List<String> parts);

    void addDishToRestaurant(Long dish_id, Long restaurant_id);

    void deleteDishFromRestaurant(Long dish_id, Long restaurant_id);

    void addIngredientToDish(Long dish_id, Long ingredient_id);

    void addDish(String name, Double price, Long dishType, String cuisineType);


}
