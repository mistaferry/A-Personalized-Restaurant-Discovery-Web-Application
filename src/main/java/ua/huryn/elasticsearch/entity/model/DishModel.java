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
    private Long dishId;
    @Field(type = FieldType.Text, name = "info")
    private String info;
}
