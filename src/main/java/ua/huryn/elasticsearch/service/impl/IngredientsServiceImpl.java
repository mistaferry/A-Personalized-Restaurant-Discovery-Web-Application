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
import ua.huryn.elasticsearch.entity.dto.IngredientDTO;
import ua.huryn.elasticsearch.entity.model.DishModel;
import ua.huryn.elasticsearch.entity.model.IngredientModel;
import ua.huryn.elasticsearch.repository.db.DishDbRepository;
import ua.huryn.elasticsearch.repository.db.IngredientDbRepository;
import ua.huryn.elasticsearch.service.IngredientsService;
import ua.huryn.elasticsearch.utils.Convertor;

import java.util.*;
import java.util.stream.Collectors;

@Service
@Slf4j
//@RequiredArgsConstructor
public class IngredientsServiceImpl implements IngredientsService {
    private final IngredientDbRepository ingredientDbRepository;
    private final GeneralProperties generalProperties;
    private final ElasticsearchOperations elasticsearchOperations;
    private final String localDirectory;

    public IngredientsServiceImpl(IngredientDbRepository ingredientDbRepository, GeneralProperties generalProperties, ElasticsearchOperations elasticsearchOperations) {
        this.ingredientDbRepository = ingredientDbRepository;
        this.generalProperties = generalProperties;
        this.localDirectory = generalProperties.getLocalDirectory();
        this.elasticsearchOperations = elasticsearchOperations;
    }

    @Override
    public Map<Long, String> getAllIngredients() {
        Map<Long, String> ingredients = new HashMap<>();
        List<Ingredient> dishes = ingredientDbRepository.getAll();
        for(Ingredient ingredient: dishes){
            ingredients.put(ingredient.getId(), ingredient.getName());
        }
        return ingredients;
    }

    @Override
    public IngredientDTO getByName(String name) {
        Ingredient ingredient = ingredientDbRepository.findByName(name);
        log.info("ingredient - {}", ingredient);
        return Convertor.convertToDTO(ingredient);
    }

    @Override
    public List<IngredientDTO> getAll() {
        List<Ingredient> ingredients = ingredientDbRepository.getAll();
        return Convertor.convertIngredientEntityListToDTO(ingredients);
    }

    @Override
    public List<IngredientDTO> getIngredientDTOBySearch(String searchString) {
        searchString = searchString.toLowerCase();
        String[] words = searchString.split("\\s+");
        List<String> wordList = Arrays.asList(words);

        java.util.List<IngredientModel> list = searchByIngredientInfo(wordList);
        Set<Long> dishesId = new HashSet<>();
        for (IngredientModel ingredientModel : list) {
            dishesId.add(ingredientModel.getIngredientId());
        }
        List<Ingredient> entityList = new ArrayList<>();
        for (Long id : dishesId) {
            entityList.add(ingredientDbRepository.findById(id).orElse(null));
        }
        return Convertor.convertIngredientEntityListToDTO(entityList);
    }

    @Override
    public List<IngredientModel> searchByIngredientInfo(List<String> parts) {
        BoolQueryBuilder boolQuery = QueryBuilders.boolQuery();

        if (parts != null && !parts.isEmpty()) {
            for (String part : parts) {
                if (!part.isBlank()) {
                    boolQuery.should(QueryBuilders.wildcardQuery("name", "*" + part + "*"));
                }
            }
        }

        String queryString = boolQuery.toString();
        StringQuery stringQuery = new StringQuery(queryString);

        stringQuery.setPageable(PageRequest.of(0, 10));

        SearchHits<IngredientModel> searchHits = elasticsearchOperations.search(
                stringQuery, IngredientModel.class);


        return searchHits.stream()
                .map(SearchHit::getContent)
                .collect(Collectors.toList());
    }
}
