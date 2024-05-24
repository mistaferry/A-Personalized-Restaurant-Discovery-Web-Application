package ua.huryn.elasticsearch.service.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import ua.huryn.elasticsearch.entity.db.Dish;
import ua.huryn.elasticsearch.entity.db.Ingredient;
import ua.huryn.elasticsearch.entity.dto.IngredientDTO;
import ua.huryn.elasticsearch.repository.db.IngredientDbRepository;
import ua.huryn.elasticsearch.service.IngredientsService;
import ua.huryn.elasticsearch.utils.Convertor;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@Slf4j
@RequiredArgsConstructor
public class IngredientsServiceImpl implements IngredientsService {
    private final IngredientDbRepository ingredientDbRepository;

    @Override
    public Map<Long, String> getAllIngredients() {
        Map<Long, String> ingredients = new HashMap<>();
        List<Ingredient> dishes = ingredientDbRepository.getAll();
        for(Ingredient ingredient: dishes){
            ingredients.put(ingredient.getId(), ingredient.getName());
        }
        return ingredients;
    }

    @Override
    public List<IngredientDTO> getAll() {
        List<Ingredient> ingredients = ingredientDbRepository.getAll();
        return Convertor.convertIngredientEntityListToDTO(ingredients);
    }
}
