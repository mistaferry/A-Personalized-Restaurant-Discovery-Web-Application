package ua.huryn.elasticsearch.entity.model;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

@Data
@Document(indexName = "ingredient")
public class IngredientModel {
    @Id
    @Field(type = FieldType.Long, name = "ingredient_id")
    private Long ingredientId;
    @Field(type = FieldType.Text, name = "name")
    private String name;
}