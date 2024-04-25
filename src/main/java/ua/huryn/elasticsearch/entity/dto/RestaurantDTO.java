package ua.huryn.elasticsearch.entity.dto;

import lombok.Builder;
import lombok.Data;
import ua.huryn.elasticsearch.entity.model.CategoryModel;

import java.io.Serializable;
import java.util.List;

@Builder
@Data
public class RestaurantDTO implements Serializable {
    private static final long serialVersionUID = 1L;
    private Long restaurantId;
    private String placeId;
    private String name;
    private String address;
    private Double latitude;
    private Double longitude;
    private Double rating;
    private int priceLevel;
    private String website;
    private String photoRef;
    private String cuisineType;
    private List<CategoryModel> categories;
    private List<CategoryModel> dishes;
}
