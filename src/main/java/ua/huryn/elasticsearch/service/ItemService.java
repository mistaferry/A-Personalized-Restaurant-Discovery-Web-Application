package ua.huryn.elasticsearch.service;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import ua.huryn.elasticsearch.entity.model.Item;

public interface ItemService {
    List<Item> findByName(String str);
}
