package ua.huryn.elasticsearch.service.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.elasticsearch.index.query.BoolQueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.elasticsearch.core.ElasticsearchOperations;
import org.springframework.data.elasticsearch.core.SearchHit;
import org.springframework.data.elasticsearch.core.SearchHits;
import org.springframework.data.elasticsearch.core.query.StringQuery;
import org.springframework.stereotype.Service;
import ua.huryn.elasticsearch.config.GeneralProperties;
import ua.huryn.elasticsearch.entity.db.Dish;
import ua.huryn.elasticsearch.entity.db.Ingredient;
import ua.huryn.elasticsearch.entity.dto.DishDTO;
import ua.huryn.elasticsearch.entity.model.DishModel;
import ua.huryn.elasticsearch.repository.db.*;
import ua.huryn.elasticsearch.service.DishService;
import ua.huryn.elasticsearch.utils.Convertor;

import java.util.*;
import java.util.stream.Collectors;

@Service
@Slf4j
//@RequiredArgsConstructor
public class DishServiceImpl implements DishService {
    private final DishDbRepository dishDbRepository;
    private final IngredientDbRepository ingredientDbRepository;
    private final GeneralProperties generalProperties;
    private final ElasticsearchOperations elasticsearchOperations;
    private final String localDirectory;

    public DishServiceImpl(DishDbRepository dishDbRepository, IngredientDbRepository ingredientDbRepository, GeneralProperties generalProperties, ElasticsearchOperations elasticsearchOperations) {
        this.dishDbRepository = dishDbRepository;
        this.ingredientDbRepository = ingredientDbRepository;
        this.generalProperties = generalProperties;
        this.localDirectory = generalProperties.getLocalDirectory();
        this.elasticsearchOperations = elasticsearchOperations;
    }

    @Override
    public Map<Long, String> getAllDishesNames() {
        Map<Long, String> dishesNames = new HashMap<>();
        List<Dish> dishes = dishDbRepository.getAll();
        for (Dish dish : dishes) {
            dishesNames.put(dish.getId(), dish.getName());
        }
        return dishesNames;
    }

    @Override
    public void setAllDishListsData(Dish dish) {
        List<Ingredient> ingredients = ingredientDbRepository.findIngredientsByDishId(dish.getId()).orElse(null);
        dish.setIngredients(ingredients);
    }

    @Override
    public List<DishDTO> getAll() {
        List<Dish> dishes = dishDbRepository.getAll();
        return Convertor.convertDishEntityListToDTO(dishes);
    }

    @Override
    public List<DishDTO> getDishDTOBySearch(String searchString) {
        searchString = searchString.toLowerCase();
        String[] words = searchString.split("\\s+");
        List<String> wordList = Arrays.asList(words);

        java.util.List<DishModel> list = searchByDishInfo(wordList);
        Set<Long> dishesId = new HashSet<>();
        for (DishModel dishModel : list) {
            dishesId.add(dishModel.getDishId());
        }
        List<Dish> entityList = new ArrayList<>();
        for (Long id : dishesId) {
            entityList.add(dishDbRepository.findById(id).orElse(null));
        }
        return Convertor.convertDishEntityListToDTO(entityList);
    }

    @Override
    public List<DishModel> searchByDishInfo(List<String> parts) {
        BoolQueryBuilder boolQuery = QueryBuilders.boolQuery();

        if (parts != null && !parts.isEmpty()) {
            for (String part : parts) {
                if (!part.isBlank()) {
                    boolQuery.should(QueryBuilders.wildcardQuery("info", "*" + part + "*"));
                }
            }
        }

        String queryString = boolQuery.toString();
        StringQuery stringQuery = new StringQuery(queryString);

        stringQuery.setPageable(PageRequest.of(0, 10));

        SearchHits<DishModel> searchHits = elasticsearchOperations.search(
                stringQuery, DishModel.class);


        return searchHits.stream()
                .map(SearchHit::getContent)
                .collect(Collectors.toList());
    }

    @Override
    public DishDTO getByNameAndPrice(String info){
        List<DishDTO> dishDTOS = getAll();
        for (DishDTO dishDTO: dishDTOS){
            String str = dishDTO.getName() + " " + dishDTO.getPrice() + " грн.";
            if(info.equals(str)){
                return dishDTO;
            }
        }
        return null;
    }

}
