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
    @Field(type = FieldType.Text, name = "place_id")
    private String placeId;
    @Field(type = FieldType.Text, name = "name")
    private String name;
    @Field(type = FieldType.Text, name = "address")
    private String address;
    @Field(type = FieldType.Double, name = "latitude")
    private Double latitude;
    @Field(type = FieldType.Double, name = "longitude")
    private Double longitude;
    @Field(type = FieldType.Double, name = "rating")
    private Double rating;
    @Field(type = FieldType.Integer, name = "price_level")
    private int priceLevel;
    @Field(type = FieldType.Text, name = "website")
    private String website;
    @Field(type = FieldType.Text, name = "photo_ref")
    private String photoRef;
    @Field(type = FieldType.Text, name = "cuisine_type")
    private String cuisineType;
    @Field(type = FieldType.Nested, name = "categories")
    private List<CategoryModel> categories;
    @Field(type = FieldType.Nested, name = "dishes")
    private List<CategoryModel> dishes;
}

