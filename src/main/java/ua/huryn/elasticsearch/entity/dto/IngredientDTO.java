package ua.huryn.elasticsearch.entity.dto;

import lombok.Builder;
import lombok.Data;

import java.io.Serializable;

@Builder
@Data
public class IngredientDTO implements Serializable {
    private static final long serialVersionUID = 1L;
    private Long ingredientId;
    private String name;
}
