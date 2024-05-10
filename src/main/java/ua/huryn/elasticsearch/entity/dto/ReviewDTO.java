package ua.huryn.elasticsearch.entity.dto;

import lombok.Builder;
import lombok.Data;

import java.io.Serializable;
import java.sql.Timestamp;

@Builder
@Data
public class ReviewDTO implements Serializable {
    private static final long serialVersionUID = 1L;
    private Long reviewId;
    private String text;
    private Timestamp time;
    private RestaurantDTO restaurantDTO;
    private UserDTO userDTO;
}
