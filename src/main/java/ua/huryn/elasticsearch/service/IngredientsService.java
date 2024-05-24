package ua.huryn.elasticsearch.service;

import ua.huryn.elasticsearch.entity.dto.IngredientDTO;

import java.util.List;
import java.util.Map;

public interface IngredientsService {
    Map<Long, String> getAllIngredients();

    List<IngredientDTO> getAll();
}
