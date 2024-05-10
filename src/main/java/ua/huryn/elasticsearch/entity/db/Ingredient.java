package ua.huryn.elasticsearch.entity.db;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Entity
@Table(name="ingredient")
public class Ingredient {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "ingredient_id")
    private Long id;
    @Column(name = "name")
    private String name;
}
