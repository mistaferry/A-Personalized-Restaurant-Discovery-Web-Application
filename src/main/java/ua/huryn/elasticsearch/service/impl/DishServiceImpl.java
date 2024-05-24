package ua.huryn.elasticsearch.service.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import ua.huryn.elasticsearch.entity.db.Dish;
import ua.huryn.elasticsearch.entity.db.Ingredient;
import ua.huryn.elasticsearch.entity.dto.DishDTO;
import ua.huryn.elasticsearch.repository.db.DishDbRepository;
import ua.huryn.elasticsearch.repository.db.IngredientDbRepository;
import ua.huryn.elasticsearch.service.DishService;
import ua.huryn.elasticsearch.utils.Convertor;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@Slf4j
@RequiredArgsConstructor
public class DishServiceImpl implements DishService {
    private final DishDbRepository dishDbRepository;
    private final IngredientDbRepository ingredientDbRepository;

    @Override
    public Map<Long, String> getAllDishesNames() {
        Map<Long, String> dishesNames = new HashMap<>();
        List<Dish> dishes = dishDbRepository.getAll();
        for(Dish dish: dishes){
            dishesNames.put(dish.getId(), dish.getName());
        }
        return dishesNames;
    }

    @Override
    public void setAllDishListsData(Dish dish) {
        List<Ingredient> ingredients = ingredientDbRepository.findIngredientsByDishId(dish.getId()).orElse(null);
        dish.setIngredients(ingredients);
    }

    @Override
    public List<DishDTO> getAll() {
        List<Dish> dishes = dishDbRepository.getAll();
        return Convertor.convertDishEntityListToDTO(dishes);
    }

}
