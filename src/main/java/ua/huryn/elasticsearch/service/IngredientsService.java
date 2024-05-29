package ua.huryn.elasticsearch.service;

import ua.huryn.elasticsearch.entity.dto.DishDTO;
import ua.huryn.elasticsearch.entity.dto.IngredientDTO;
import ua.huryn.elasticsearch.entity.model.DishModel;
import ua.huryn.elasticsearch.entity.model.IngredientModel;

import java.util.List;
import java.util.Map;

public interface IngredientsService {
    Map<Long, String> getAllIngredients();

    IngredientDTO getByName(String name);

    List<IngredientDTO> getAll();

    List<IngredientDTO> getIngredientDTOBySearch(String searchString);

    List<IngredientModel> searchByIngredientInfo(List<String> parts);
}
