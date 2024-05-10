package ua.huryn.elasticsearch.entity.db;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Entity
@Table(name = "dish_type")
public class DishType {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "type_id")
    private Integer id;

    @Column(name = "name")
    private String name;
}
