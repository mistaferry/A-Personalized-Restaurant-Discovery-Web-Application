package ua.huryn.elasticsearch.repository1;

import org.springframework.data.jpa.repository.JpaRepository;
import ua.huryn.elasticsearch.entity.Category;

public interface CategoryDbRepository extends JpaRepository<Category,Integer> {
    Category findByName(String name);
}