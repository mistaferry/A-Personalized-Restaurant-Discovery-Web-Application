package ua.huryn.elasticsearch.entity.model;

import lombok.Data;
import lombok.experimental.Accessors;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

@Data
@Accessors(chain = true)
@Document(indexName = "category")
public class CategoryModel {
    @Id
    @Field(type = FieldType.Long, name = "category_id")
    private Long category_id;
    @Field(type = FieldType.Text, name = "name")
    private String name;
}
