package ua.huryn.elasticsearch.entity.dto;

import lombok.Builder;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

@Builder
@Data
public class DishDTO implements Serializable {
    private static final long serialVersionUID = 1L;
    private Long dishId;
    private String name;
    private Double price;
    private DishTypeDTO dishType;
    private String cuisineType;
    private List<IngredientDTO> ingredients;
}
