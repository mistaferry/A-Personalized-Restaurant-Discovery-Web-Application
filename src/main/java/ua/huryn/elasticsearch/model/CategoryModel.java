package ua.huryn.elasticsearch.model;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

@Data
@Document(indexName = "category")
public class CategoryModel {
    @Id
    @Field(type = FieldType.Long, name = "category_id")
    private int category_id;
    @Field(type = FieldType.Text, name = "name")
    private String name;
}
