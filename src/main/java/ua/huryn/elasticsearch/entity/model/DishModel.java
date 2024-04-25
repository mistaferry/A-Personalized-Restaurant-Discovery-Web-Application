package ua.huryn.elasticsearch.entity.model;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

@Data
@Document(indexName = "dish")
public class DishModel {
    @Id
    @Field(type = FieldType.Long, name = "dish_id")
    private int dish_id;
    @Field(type = FieldType.Text, name = "name")
    private String name;
    @Field(type = FieldType.Text, name = "description")
    private String description;
    @Field(type = FieldType.Double, name = "price")
    private Double price;
    @Field(type = FieldType.Text, name = "cuisine_type")
    private String cuisine_type;
    @Field(type = FieldType.Nested, name = "dish_type")
    private DishTypeModel dish_type;
}
