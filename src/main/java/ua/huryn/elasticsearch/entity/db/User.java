package ua.huryn.elasticsearch.entity.db;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@ToString
@Entity
@Table(name="user")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "user_id")
    private Long id;
    @Column(name = "username")
    private String username;
    @Column(name = "email")
    private String email;
    @Column(name = "picture")
    private String picture;
    @ManyToOne
    @JoinColumn(name = "role", referencedColumnName = "role_id")
    private Role role;
}
