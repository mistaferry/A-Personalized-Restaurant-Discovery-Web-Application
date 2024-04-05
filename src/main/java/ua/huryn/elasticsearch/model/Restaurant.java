package ua.huryn.elasticsearch.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

import lombok.Data;

@Data
@Document(indexName = "restaurant")
public class Restaurant {
    @Id
    private int id;
    @Field(type = FieldType.Text, name = "name")
    private String name;
    @Field(type = FieldType.Double, name = "latitude")
    private Double latitude;
    @Field(type = FieldType.Double, name = "longitude")
    private Double longitude;
    @Field(type = FieldType.Integer, name = "rating")
    private int rating;
//    @Field(type = FieldType.Keyword, name = "keywords")
//    private String keywords;
}

