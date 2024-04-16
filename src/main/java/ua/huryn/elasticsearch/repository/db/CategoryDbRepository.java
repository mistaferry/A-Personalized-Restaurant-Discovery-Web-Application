package ua.huryn.elasticsearch.repository.db;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ua.huryn.elasticsearch.entity.Category;

@Repository
public interface CategoryDbRepository extends JpaRepository<Category,Integer> {
    Category findByName(String name);
}