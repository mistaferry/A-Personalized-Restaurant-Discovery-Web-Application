package ua.huryn.elasticsearch.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

import lombok.Data;

@Data
@Document(indexName = "product")
public class Item {
    @Id
    @Field(type = FieldType.Long, name = "product_id")
    private Long id;
    @Field(type = FieldType.Text, name = "name")
    private String name;
    @Field(type = FieldType.Double, name = "price")
    private Double price;
    @Field(type = FieldType.Keyword, name = "brand")
    private String brand;
    @Field(type = FieldType.Keyword, name = "category")
    private String category;
}
