package ua.huryn.elasticsearch.model;

import jakarta.persistence.Column;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

import lombok.Data;
import ua.huryn.elasticsearch.entity.Category;

import java.util.List;

@Data
@Document(indexName = "restaurant")
public class RestaurantModel {
    @Id
    @Field(type = FieldType.Long, name = "restaurant_id")
    private int restaurant_id;
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
    private List<CategoryModel> categories;
}

