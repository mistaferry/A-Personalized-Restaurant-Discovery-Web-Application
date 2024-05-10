package ua.huryn.elasticsearch.entity.db;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@ToString
@Entity
@Table(name = "restaurant_info_en")
public class RestaurantInfoEn {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long infoId;

    @Column(name = "restaurant_id")
    private Long restaurantId;

    @Column(name = "name_en")
    private String nameEn;

    @Column(name = "address_en")
    private String addressEn;
}