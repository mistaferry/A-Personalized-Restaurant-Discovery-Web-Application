package ua.huryn.elasticsearch.model;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

@Data
@Document(indexName = "dish_type")
public class DishTypeModel {
    @Id
    @Field(type = FieldType.Long, name = "id")
    private int id;
    @Field(type = FieldType.Text, name = "name")
    private String name;
}
