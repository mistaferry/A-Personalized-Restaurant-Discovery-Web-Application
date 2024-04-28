package ua.huryn.elasticsearch.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import ua.huryn.elasticsearch.entity.model.Item;
import ua.huryn.elasticsearch.repository.elasticsearch.ItemRepository;
import ua.huryn.elasticsearch.service.ItemService;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ItemServiceImpl implements ItemService {
    private final ItemRepository itemRepository;
    @Override
    public List<Item> findByName(String itemName) {
        return itemRepository.findByName(itemName);
    }
}
