package ua.huryn.elasticsearch.service.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import ua.huryn.elasticsearch.entity.db.Dish;
import ua.huryn.elasticsearch.repository.db.DishDbRepository;
import ua.huryn.elasticsearch.service.DishService;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@Slf4j
@RequiredArgsConstructor
public class DishServiceImpl implements DishService {
    private final DishDbRepository dishDbRepository;

    @Override
    public Map<Long, String> getAllDishesNames() {
        Map<Long, String> dishesNames = new HashMap<>();
        List<Dish> dishes = dishDbRepository.getAll();
        for(Dish dish: dishes){
            dishesNames.put(dish.getId(), dish.getName());
        }
        return dishesNames;
    }
}
