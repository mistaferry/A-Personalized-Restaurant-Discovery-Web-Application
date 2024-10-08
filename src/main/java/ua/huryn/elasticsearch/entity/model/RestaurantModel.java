package ua.huryn.elasticsearch.entity.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

import lombok.Data;

import java.util.List;

@Data
@Document(indexName = "restaurant")
public class RestaurantModel {
    @Id
    @Field(type = FieldType.Long, name = "restaurant_id")
    private Long restaurantId;
    @Field(type = FieldType.Text, name = "search_string_ukr", analyzer = "ukrainian")
    private String searchFieldUa;
    @Field(type = FieldType.Text, name = "search_string_eng", analyzer = "english")
    private String searchFieldEn;
}

