package ua.huryn.elasticsearch.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import ua.huryn.elasticsearch.model.Item;
import ua.huryn.elasticsearch.repository.ItemRepository;
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

    @Override
    public List<Item> findByCategory(String category) {
        return itemRepository.findByCategory(category);
    }

    @Override
    public List<Item> findByPriceBetween(double low, double high) {
        return itemRepository.findByPriceBetween(low, high);
    }
}
