package ua.huryn.elasticsearch.entity.db;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

@Setter
@Getter
@ToString
@Entity
@Table(name="restaurant")
public class Restaurant {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "restaurant_id")
    private Long id;
    @Column(name = "place_id")
    private String placeId;
    @Column(name = "name")
    private String name;
    @Column(name = "address")
    private String address;
    @Column(name = "latitude")
    private Double latitude;
    @Column(name = "longitude")
    private Double longitude;
    @Column(name = "rating")
    private Double rating;
    @Column(name = "price_level")
    private int priceLevel;
    @Column(name = "website")
    private String website;
    @Column(name = "photo_ref")
    private String photoRef;
    @Column(name = "cuisine_type")
    private String cuisineType;
    @OneToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "restaurant_category",
            joinColumns = @JoinColumn(name = "restaurant_id"),
            inverseJoinColumns = @JoinColumn(name = "category_id"))
    @ToString.Exclude
    List<Category> categories;
    @OneToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "restaurant_dish",
            joinColumns = @JoinColumn(name = "restaurant_id"),
            inverseJoinColumns = @JoinColumn(name = "dish_id"))
    @ToString.Exclude
    List<Dish> dishes;
}
