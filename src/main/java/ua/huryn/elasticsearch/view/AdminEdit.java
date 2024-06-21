package ua.huryn.elasticsearch.view;

import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.combobox.ComboBox;
import com.vaadin.flow.component.dependency.CssImport;
import com.vaadin.flow.component.dependency.StyleSheet;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.html.H3;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.dom.Style;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import jakarta.annotation.security.PermitAll;
import ua.huryn.elasticsearch.entity.db.Dish;
import ua.huryn.elasticsearch.entity.db.Ingredient;
import ua.huryn.elasticsearch.entity.dto.DishDTO;
import ua.huryn.elasticsearch.entity.dto.IngredientDTO;
import ua.huryn.elasticsearch.entity.dto.RestaurantDTO;
import ua.huryn.elasticsearch.repository.db.DishDbRepository;
import ua.huryn.elasticsearch.repository.db.IngredientDbRepository;
import ua.huryn.elasticsearch.repository.db.RestaurantDbRepository;
import ua.huryn.elasticsearch.service.DishService;
import ua.huryn.elasticsearch.service.IngredientsService;
import ua.huryn.elasticsearch.service.RestaurantService;
import ua.huryn.elasticsearch.utils.Convertor;

import java.util.ArrayList;
import java.util.List;

import static org.reflections.Reflections.log;

@PageTitle("Edit data")
@Route(value = "/admin/edit", layout = MainView.class)
@CssImport("styles.css")
@PermitAll
@StyleSheet("https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css")
public class AdminEdit extends VerticalLayout {

    private final IngredientDbRepository ingredientDbRepository;
    private final RestaurantService restaurantService;
    private final DishService dishService;
    private final IngredientsService ingredientsService;
    private final DishDbRepository dishDbRepository;
    private final RestaurantDbRepository restaurantDbRepository;
    private List<RestaurantDTO> allRestaurants;
    private List<DishDTO> allDishes;
    private List<IngredientDTO> allIngredients;
    private ComboBox<String> restaurantsCombobox;
    private ComboBox<String> dishesCombobox;
    private ComboBox<String> ingredientsCombobox;
    private RestaurantDTO selectedRestaurant;
    private DishDTO selectedDish;
    private IngredientDTO selectedIngredient;
    private TextField dishName;
    private TextField dishPrice;
    private TextField dishType;
    private TextField ingredientName;
    Div dishSearchSection = new Div();

    public AdminEdit(IngredientDbRepository ingredientDbRepository, RestaurantService restaurantService, DishService dishService, IngredientsService ingredientsService, DishDbRepository dishDbRepository, RestaurantDbRepository restaurantDbRepository) {
        this.ingredientDbRepository = ingredientDbRepository;
        this.restaurantService = restaurantService;
        this.dishService = dishService;
        this.ingredientsService = ingredientsService;
        this.restaurantsCombobox = new ComboBox<>();
        this.dishesCombobox = new ComboBox<>();
        this.ingredientsCombobox = new ComboBox<>();
        this.allRestaurants = restaurantService.getAll();
        this.allDishes = dishService.getAll();
        this.allIngredients = ingredientsService.getAll();

        editDataInMenu();
        this.dishDbRepository = dishDbRepository;
        this.restaurantDbRepository = restaurantDbRepository;
    }

    private void editDataInMenu() {
        Div div = new Div();
        div.addClassNames("admin-full-div");

        Div createDiv = new Div();
        createDiv.addClassNames("admin-page-function d-flex");
        createDiv.getStyle().setAlignItems(Style.AlignItems.CENTER);

        H3 h = new H3("Змінити дані в меню");
        createDiv.add(h);
        createDiv.add(createSearchByRestaurant());
        restaurantCheckboxListener();
        createDiv.add(createSearchByDish());
        dishCheckboxListener();

        Button editIngredient = new Button("Зберегти");
        editIngredient.addClickListener(event -> {
            editIngredientInDb();
        });
        createDiv.add(editIngredient);
        div.add(createDiv);
        add(div);

        ingredientCheckboxListener();
    }

    private void editDishInDb(){
        List<DishDTO> dishes = dishService.getByNameAndPrice(dishName.getValue(), Double.valueOf(dishPrice.getValue()));
        if(!dishes.isEmpty()){
            for (DishDTO dishDTO: dishes){
                dishService.addDishToRestaurant(dishDTO.getDishId(), selectedRestaurant.getRestaurantId());
            }
            dishService.deleteDishFromRestaurant(selectedDish.getDishId(), selectedRestaurant.getRestaurantId());
        }else{
            Dish dish = new Dish();
            dish.setName(dishName.getValue());
            dish.setPrice(Double.valueOf(dishPrice.getValue()));
            dish.setDishType(Convertor.convertToEntity(selectedDish.getDishType()));
            dish.setCuisineType(selectedDish.getCuisineType());

            Long id = dishDbRepository.findMaxId()+1;
            log.info("id - {}", id);
            dishService.addDish(dish.getName(), dish.getPrice(), dish.getDishType().getId().longValue(), dish.getCuisineType());

            dishService.addDishToRestaurant(id, selectedRestaurant.getRestaurantId());

            List<Ingredient> ingredients = Convertor.convertIngredientDTOListToEntity(selectedDish.getIngredients());
            for(Ingredient ingredient: ingredients){
                dishService.addIngredientToDish(id, ingredient.getId());
            }
            dishService.deleteDishFromRestaurant(selectedDish.getDishId(), selectedRestaurant.getRestaurantId());

        }
    }

    private void editIngredientInDb(){
        selectedIngredient.setName(ingredientName.getValue());
        ingredientDbRepository.updateIngredientById(selectedIngredient.getIngredientId(), selectedIngredient.getName());
    }

    private Div createSearchByRestaurant() {
        Div searchSection = new Div();
        searchSection.addClassNames("search-section basic");

        restaurantsCombobox.setPlaceholder("Ресторан");
        restaurantsCombobox.setPrefixComponent(VaadinIcon.SEARCH.create());
        restaurantsCombobox.addClassName("admin-search-field");

        searchSection.add(restaurantsCombobox);

        return searchSection;
    }

    public void restaurantCheckboxListener(){
        restaurantsCombobox.isRequired();
        restaurantsCombobox.addValueChangeListener(event -> {
            String input = event.getValue();
            log.info(input);
            if (input != null && !input.isEmpty()) {
                selectedRestaurant = (restaurantService.searchByRestaurantInfo(input));
                log.info("selected rest - {}", selectedRestaurant.getName());
            }
        });

        restaurantsCombobox.setItems(query -> {
            String filter = query.getFilter().orElse("");
            int limit = query.getLimit();
            int offset = query.getOffset();
            List<String> restaurantDetails = restaurantService.getRestaurantsDTOBySearchInEngAndUkr(filter, allRestaurants)
                    .stream()
                    .skip(offset)
                    .limit(limit)
                    .map(restaurant -> restaurant.getName() + " " + restaurant.getAddress())
                    .toList();
            return restaurantDetails.stream();
        });
    }

    private Div createSearchByDish() {
        dishSearchSection = new Div();
        dishSearchSection.addClassNames("search-section basic");

        dishesCombobox.setPlaceholder("Страва");
        dishesCombobox.setPrefixComponent(VaadinIcon.SEARCH.create());
        dishesCombobox.addClassName("admin-search-field");
        dishSearchSection.add(dishesCombobox);
        return dishSearchSection;
    }

    public void dishCheckboxListener() {
        dishesCombobox.addValueChangeListener(event -> {
            String input = event.getValue();
            if (input != null && !input.isEmpty()) {
                selectedDish = dishService.getByNameAndPrice(input);
                Div dishInfo = new Div();
                dishInfo.addClassNames("admin-dish-info");
                dishName = new TextField("Назва");
                dishName.setValue(selectedDish.getName());
                dishPrice = new TextField("Ціна");
                dishPrice.setPattern("[0-9.]+");
                dishPrice.setValue(String.valueOf(selectedDish.getPrice()));
                dishType = new TextField("Тип страви");
                dishType.setValue(selectedDish.getDishType().getName());
                dishType.setEnabled(false);
                TextField cuisine = new TextField("Кухня");
                cuisine.setValue(selectedRestaurant.getCuisineType());
                cuisine.setEnabled(false);
                dishInfo.add(dishName, dishPrice, dishType, cuisine);
                dishSearchSection.add(dishInfo);
                Button editButton = new Button("Змінити страву");
                editButton.addClickListener(e -> {
                    editDishInDb();
                });
                dishSearchSection.add(editButton);
                dishSearchSection.add(createSearchByIngredient());
            }
        });

        dishesCombobox.setItems(query -> {
            String filter = query.getFilter().orElse("");
            int limit = query.getLimit();
            int offset = query.getOffset();
            List<String> dishDetails = new ArrayList<>(selectedRestaurant.getDishes()
                    .stream()
                    .skip(offset)
                    .limit(limit)
                    .map(dish -> dish.getName() + " " + dish.getPrice() + " грн.")
                    .toList());
            return dishDetails.stream();
        });
    }

    private Div createSearchByIngredient() {
        dishSearchSection = new Div();
        dishSearchSection.addClassNames("search-section basic");

        ingredientsCombobox.setPlaceholder("Інгредієнт");
        ingredientsCombobox.setPrefixComponent(VaadinIcon.SEARCH.create());
        ingredientsCombobox.addClassName("admin-search-field");
        dishSearchSection.add(ingredientsCombobox);

        return dishSearchSection;
    }

    public void ingredientCheckboxListener(){
        ingredientsCombobox.addValueChangeListener(event -> {
            String input = event.getValue();
            if (input != null && !input.isEmpty()){
                selectedIngredient = ingredientsService.getByName(input);
                Div ingredientInfo = new Div();
                ingredientInfo.addClassNames("admin-dish-info");
                ingredientName = new TextField("Назва");
                ingredientName.setValue(selectedIngredient.getName());
                ingredientInfo.add(ingredientName);
                dishSearchSection.add(ingredientInfo);
            }
        });

        ingredientsCombobox.setItems(query -> {
            String filter = query.getFilter().orElse("");
            int limit = query.getLimit();
            int offset = query.getOffset();
            List<String> ingredientDetails = ingredientsService.getIngredientDTOBySearch(filter)
                    .stream()
                    .skip(offset)
                    .limit(limit)
                    .map(IngredientDTO::getName)
                    .toList();
            return ingredientDetails.stream();
        });
    }
}
