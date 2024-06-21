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
import ua.huryn.elasticsearch.entity.dto.DishDTO;
import ua.huryn.elasticsearch.entity.dto.IngredientDTO;
import ua.huryn.elasticsearch.entity.dto.RestaurantDTO;
import ua.huryn.elasticsearch.repository.db.DishDbRepository;
import ua.huryn.elasticsearch.repository.db.IngredientDbRepository;
import ua.huryn.elasticsearch.repository.db.RestaurantDbRepository;
import ua.huryn.elasticsearch.service.DishService;
import ua.huryn.elasticsearch.service.IngredientsService;
import ua.huryn.elasticsearch.service.RestaurantService;

import java.util.ArrayList;
import java.util.List;

import static org.reflections.Reflections.log;

@PageTitle("Add data")
@Route(value = "/admin/add", layout = MainView.class)
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
    private RestaurantDTO selectedAddRestaurant;
    private DishDTO selectedAddDish;
    private List<IngredientDTO> selectedAddIngredient;
    Div searchSection = new Div();

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
        this.selectedAddIngredient = new ArrayList<>();

        addDataIntoMenu();
        this.dishDbRepository = dishDbRepository;
        this.restaurantDbRepository = restaurantDbRepository;
    }

    private void addDataIntoMenu() {
        Div div = new Div();
        div.addClassNames("admin-full-div");

        Div createDiv = new Div();
        createDiv.addClassNames("admin-page-function d-flex");
        createDiv.getStyle().setAlignItems(Style.AlignItems.CENTER);

        H3 h = new H3("Додати дані до ресторану");
        createDiv.add(h);
        createDiv.add(createSearchByRestaurant());
        createDiv.add(createSearchByDish());

        createDiv.add(createSearchByIngredient());
        Button saveButton = new Button("Зберегти");
        saveButton.addClickListener(event -> {
            addDataToDb();
        });
        createDiv.add(saveButton);
        div.add(createDiv);
        add(div);
        restaurantCheckboxListener();
        dishCheckboxListener();
        ingredientCheckboxListener();
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
                selectedAddRestaurant = (restaurantService.searchByRestaurantInfo(input));
                log.info("selected rest - {}", selectedAddRestaurant.getName());
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
                if(input.equals("Нова страва")) {
                    log.info("new dish");
                    Div dishInfo = new Div();
                    dishInfo.addClassNames("admin-dish-info");
                    TextField name = new TextField("Назва");
                    TextField price = new TextField("Ціна");
                    TextField dishType = new TextField("Тип страви");
                    TextField cuisine = new TextField("Кухня");
                    cuisine.setValue(selectedAddRestaurant.getCuisineType());
                    cuisine.setEnabled(false);
                    dishInfo.add(name, price, dishType, cuisine);
                    searchSection.add(dishInfo);
                    createSearchByIngredient();
                }else{
                    selectedAddDish = dishService.getByNameAndPrice(input);
                    createSearchByIngredient();
                    List<IngredientDTO> ingredientDTOS = selectedAddDish.getIngredients();
                    for (IngredientDTO ingredientDTO : ingredientDTOS) {
                        ComboBox<String> ingredientCombobox = new ComboBox<>();
                        ingredientCombobox.setItems(ingredientDTO.getName());
                        ingredientCombobox.setValue(ingredientDTO.getName());
                        selectedAddIngredient.add(ingredientsService.getByName(ingredientDTO.getName()));
                        ingredientCombobox.setEnabled(false);
                        ingredientCombobox.setPrefixComponent(VaadinIcon.SEARCH.create());
                        ingredientCombobox.addClassName("admin-search-field");
                        searchSection.add(ingredientCombobox);
                    }
                }
            }
        });

        dishesCombobox.setItems(query -> {
            String filter = query.getFilter().orElse("");
            int limit = query.getLimit();
            int offset = query.getOffset();
            List<String> dishDetails = new ArrayList<>();
            dishDetails.add("Нова страва");
            dishDetails.addAll(dishService.getDishDTOBySearch(filter)
                    .stream()
                    .skip(offset)
                    .limit(limit)
                    .map(dish -> dish.getName() + " " + dish.getPrice() + " грн.")
                    .toList());
            return dishDetails.stream();
        });
    }

    private ComboBox<String> createIngredientsCombobox() {
        ComboBox<String> ingredientsCombobox = new ComboBox<>();
        ingredientsCombobox.setPlaceholder("Інгредієнт");
        ingredientsCombobox.setPrefixComponent(VaadinIcon.SEARCH.create());
        ingredientsCombobox.addClassName("admin-search-field");

        ingredientsCombobox.addValueChangeListener(event -> {
            String input = event.getValue();
            if (input != null && !input.isEmpty()) {
                if(input.equals("Новий інгредієнт")){
                    Div ingredientInfo = new Div();
                    ingredientInfo.addClassNames("admin-dish-info");
                    TextField name = new TextField("Назва");
                    ingredientInfo.add(name);
                    searchSection.add(ingredientInfo);
                }else {
                    selectedAddIngredient.add(ingredientsService.getByName(input));
                }
            }
        });

        ingredientsCombobox.setItems(query -> {
            String filter = query.getFilter().orElse("");
            int limit = query.getLimit();
            int offset = query.getOffset();
            List<String> ingredientDetails = new ArrayList<>();
            ingredientDetails.add("Новий інгредієнт");
            ingredientDetails.addAll(ingredientsService.getIngredientDTOBySearch(filter)
                    .stream()
                    .skip(offset)
                    .limit(limit)
                    .map(IngredientDTO::getName)
                    .toList());
            return ingredientDetails.stream();
        });

        return ingredientsCombobox;
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

        searchSection.add(addIngredientButton);
        return searchSection;
    }

    public void ingredientCheckboxListener(){
        ingredientsCombobox.addValueChangeListener(event -> {
            String input = event.getValue();
            if (input != null && !input.isEmpty()) {
                selectedAddIngredient.add(ingredientsService.getByName(input));
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
        List<DishDTO> dishes = selectedAddRestaurant.getDishes();
        if(!dishes.contains(selectedAddDish)){
            restaurantDbRepository.addDishToRestaurant(selectedAddRestaurant.getRestaurantId(), selectedAddDish.getDishId());
        }
        List<IngredientDTO> ingredients = selectedAddDish.getIngredients();
        for (IngredientDTO selectIngredient: selectedAddIngredient){
            if (!ingredients.contains(selectIngredient)) {
                dishDbRepository.addIngredientToDish(selectedAddDish.getDishId(), selectIngredient.getIngredientId());
            }
        }
    }
}
