package ua.huryn.elasticsearch.view;

import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.combobox.ComboBox;
import com.vaadin.flow.component.dependency.CssImport;
import com.vaadin.flow.component.dependency.StyleSheet;
import com.vaadin.flow.component.html.Anchor;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.html.Input;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.provider.CallbackDataProvider;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import jakarta.annotation.security.PermitAll;
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

import java.awt.*;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import static org.reflections.Reflections.log;

@PageTitle("Admin")
@Route(value = "/admin", layout = MainView.class)
@CssImport("styles.css")
@PermitAll
@StyleSheet("https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css")
public class AdminView extends VerticalLayout {
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
    private List<IngredientDTO> selectedIngredient;
    Div searchSection = new Div();
//    private Anchor addIngredientButton;

    public AdminView(IngredientDbRepository ingredientDbRepository, RestaurantService restaurantService, DishService dishService, IngredientsService ingredientsService, DishDbRepository dishDbRepository, RestaurantDbRepository restaurantDbRepository) {
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
        this.selectedIngredient = new ArrayList<>();
//        this.addIngredientButton = new Anchor("+");

        Div div = new Div();
        div.addClassNames("admin-full-div");

        Div createDiv = new Div();
        createDiv.addClassNames("admin-page-function");

        createDiv.add(createSearchByRestaurant());
        createDiv.add(createSearchByDish());

        createDiv.add(createSearchByIngredient());

        div.add(createDiv);
        Button saveButton = new Button("Зберегти");
        saveButton.addClickListener(event -> {
          addDataToDb();
        });
        add(createDiv, saveButton);
        restaurantCheckboxListener();
        dishCheckboxListener();
        ingredientCheckboxListener();
        this.dishDbRepository = dishDbRepository;
        this.restaurantDbRepository = restaurantDbRepository;
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
        Div searchSection = new Div();
        searchSection.addClassNames("search-section basic");

        dishesCombobox.setPlaceholder("Страва");
        dishesCombobox.setPrefixComponent(VaadinIcon.SEARCH.create());
        dishesCombobox.addClassName("admin-search-field");
        searchSection.add(dishesCombobox);

        return searchSection;
    }

    public void dishCheckboxListener() {
        dishesCombobox.addValueChangeListener(event -> {
            String input = event.getValue();
            if (input != null && !input.isEmpty()) {
                searchSection.removeAll();
                createSearchByIngredient();
                selectedDish = dishService.getByNameAndPrice(input);
                List<IngredientDTO> ingredientDTOS = selectedDish.getIngredients();
                for (IngredientDTO ingredientDTO : ingredientDTOS) {
                    ComboBox<String> ingredientCombobox = new ComboBox<>();
                    ingredientCombobox.setItems(ingredientDTO.getName());
                    ingredientCombobox.setValue(ingredientDTO.getName());
                    selectedIngredient.add(ingredientsService.getByName(ingredientDTO.getName()));
                    ingredientCombobox.setEnabled(false);
                    ingredientCombobox.setPrefixComponent(VaadinIcon.SEARCH.create());
                    ingredientCombobox.addClassName("admin-search-field");
                    searchSection.add(ingredientCombobox);
                }
            }
        });

        dishesCombobox.setItems(query -> {
            String filter = query.getFilter().orElse("");
            int limit = query.getLimit();
            int offset = query.getOffset();
            List<String> dishDetails = dishService.getDishDTOBySearch(filter)
                    .stream()
                    .skip(offset)
                    .limit(limit)
                    .map(dish -> dish.getName() + " " + dish.getPrice() + " грн.")
                    .toList();
            return dishDetails.stream();
        });
    }

    private ComboBox<String> createIngredientsCombobox() {
        ComboBox<String> newCombobox = new ComboBox<>();
        newCombobox.setPlaceholder("Інгредієнт");
        newCombobox.setPrefixComponent(VaadinIcon.SEARCH.create());
        newCombobox.addClassName("admin-search-field");

        newCombobox.addValueChangeListener(event -> {
            String input = event.getValue();
            if (input != null && !input.isEmpty()) {
                selectedIngredient.add(ingredientsService.getByName(input));
            }
        });

        newCombobox.setItems(query -> {
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

        return newCombobox;
    }

    private Div createSearchByIngredient() {
        searchSection.addClassNames("search-section basic");

        ingredientsCombobox.setPlaceholder("Інгредієнт");
        ingredientsCombobox.setPrefixComponent(VaadinIcon.SEARCH.create());
        ingredientsCombobox.addClassName("admin-search-field");

        com.vaadin.flow.component.button.Button addIngredientButton = new Button("Додати інгредієнт");

        addIngredientButton.addClickListener(event -> {
            ComboBox<String> newCombobox = createIngredientsCombobox();
            searchSection.add(newCombobox);
        });

        // Add the button to the section
        searchSection.add(addIngredientButton);
        return searchSection;
    }
        //    Div dishInfo = new Div();
//        dishInfo.addClassNames("admin-dish-info");
//    TextField name = new TextField();
//        name.setValue("qwert");
//    TextField price = new TextField();
//        name.setValue("ghjkl");
//    TextField dishType = new TextField();
//        dishType.setValue("dvavra");
//    TextField cuisine = new TextField();
//        cuisine.setValue("awfawevrg");
//        dishInfo.add(name, price, dishType, cuisine);
//        searchSection.add(dishInfo);


    public void ingredientCheckboxListener(){
        ingredientsCombobox.addValueChangeListener(event -> {
            String input = event.getValue();
            if (input != null && !input.isEmpty()) {
                selectedIngredient.add(ingredientsService.getByName(input));
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

    private void addDataToDb(){
        List<DishDTO> dishes = selectedRestaurant.getDishes();
        if(!dishes.contains(selectedDish)){
            restaurantDbRepository.addDishToRestaurant(selectedRestaurant.getRestaurantId(), selectedDish.getDishId());
        }
        List<IngredientDTO> ingredients = selectedDish.getIngredients();
        for (IngredientDTO selectIngredient: selectedIngredient){
            if (!ingredients.contains(selectIngredient)) {
                dishDbRepository.addIngredientToDish(selectedDish.getDishId(), selectIngredient.getIngredientId());
            }
        }
    }
}
