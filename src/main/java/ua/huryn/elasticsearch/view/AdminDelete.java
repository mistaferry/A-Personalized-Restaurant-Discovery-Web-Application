package ua.huryn.elasticsearch.view;

import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.combobox.ComboBox;
import com.vaadin.flow.component.dependency.CssImport;
import com.vaadin.flow.component.dependency.StyleSheet;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.html.H3;
import com.vaadin.flow.component.html.Image;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.dom.Style;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.server.StreamResource;
import jakarta.annotation.security.PermitAll;
import ua.huryn.elasticsearch.config.GeneralProperties;
import ua.huryn.elasticsearch.entity.dto.DishDTO;
import ua.huryn.elasticsearch.entity.dto.IngredientDTO;
import ua.huryn.elasticsearch.entity.dto.RestaurantDTO;
import ua.huryn.elasticsearch.repository.db.DishDbRepository;
import ua.huryn.elasticsearch.repository.db.IngredientDbRepository;
import ua.huryn.elasticsearch.repository.db.RestaurantDbRepository;
import ua.huryn.elasticsearch.service.DishService;
import ua.huryn.elasticsearch.service.IngredientsService;
import ua.huryn.elasticsearch.service.RestaurantService;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;

import static org.reflections.Reflections.log;

@PageTitle("Delete data")
@Route(value = "/admin/delete", layout = MainView.class)
@CssImport("styles.css")
@PermitAll
@StyleSheet("https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css")
public class AdminDelete extends VerticalLayout {

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
    private Div dishSearchSection = new Div();
    private List<Button> ingredientDeleteButtons;
    private Button dishDeleteButton;
    private final String localDirectory;
    private final GeneralProperties generalProperties;

    public AdminDelete(GeneralProperties generalProperties, IngredientDbRepository ingredientDbRepository, RestaurantService restaurantService, DishService dishService, IngredientsService ingredientsService, DishDbRepository dishDbRepository, RestaurantDbRepository restaurantDbRepository) {
        this.ingredientDbRepository = ingredientDbRepository;
        this.restaurantService = restaurantService;
        this.dishService = dishService;
        this.ingredientsService = ingredientsService;
        this.restaurantsCombobox = new ComboBox<>();
        this.dishesCombobox = new ComboBox<>();
        this.ingredientsCombobox = new ComboBox<>();
        this.dishDeleteButton = new Button("Delete");
        this.allRestaurants = restaurantService.getAll();
        this.allDishes = dishService.getAll();
        this.allIngredients = ingredientsService.getAll();
        this.selectedIngredient = new ArrayList<>();
        this.ingredientDeleteButtons = new ArrayList<>();
        this.generalProperties = generalProperties;
        this.localDirectory=generalProperties.getLocalDirectory();
        this.dishDbRepository = dishDbRepository;
        this.restaurantDbRepository = restaurantDbRepository;

        deleteDataInMenu();
        dishDeleteListener();
        ingredientDeleteListener();
    }

    private void deleteDataInMenu() {
        Div div = new Div();
        div.addClassNames("admin-full-div");

        Div createDiv = new Div();
        createDiv.addClassNames("admin-page-function d-flex");
        createDiv.getStyle().setAlignItems(Style.AlignItems.CENTER);

        H3 h = new H3("Видалити дані з ресторану");
        createDiv.add(h);
        createDiv.add(createSearchByRestaurant());
        restaurantCheckboxListener();
//        createDiv.add(createSearchByDish());
        dishCheckboxListener();

        div.add(createDiv);
        add(div);
    }

    private Div createSearchByRestaurant() {
        dishSearchSection.addClassNames("search-section basic");

        restaurantsCombobox.setPlaceholder("Ресторан");
        restaurantsCombobox.setPrefixComponent(VaadinIcon.SEARCH.create());
        restaurantsCombobox.addClassName("admin-search-field");

        dishSearchSection.add(restaurantsCombobox);

        return dishSearchSection;
    }

    public void restaurantCheckboxListener(){
        restaurantsCombobox.setRequiredIndicatorVisible(true);
        restaurantsCombobox.addValueChangeListener(event -> {
            String input = event.getValue();
            log.info(input);
            if (input != null && !input.isEmpty()) {
                selectedRestaurant = (restaurantService.searchByRestaurantInfo(input));
                log.info("selected rest - {}", selectedRestaurant.getName());
                dishSearchSection.add(createSearchByDish());
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

    private void dishDeleteListener(){
        dishDeleteButton.addClickListener(event -> {
            if(selectedDish != null){
                dishDbRepository.deleteDishFromRestaurant(selectedDish.getDishId(), selectedRestaurant.getRestaurantId());
            }
        });
    }

    private void ingredientDeleteListener(){
        int count = 0;
        for(Button deleteButton : ingredientDeleteButtons){
            Long ingredientId = selectedIngredient.get(count).getIngredientId();
            deleteButton.addClickListener(event -> {
                ingredientDbRepository.deleteIngredientToDish(selectedDish.getDishId(), ingredientId);
            });
            count++;
        }
    }

    private Div createSearchByDish() {
        dishSearchSection = new Div();
        dishSearchSection.addClassNames("search-section basic");

        dishesCombobox.setPlaceholder("Страва");
        dishesCombobox.setPrefixComponent(VaadinIcon.SEARCH.create());
        dishesCombobox.addClassName("admin-search-field");
        Div connectDiv = new Div();
        Image image = getImage();
        image.setHeight("35px");
        dishDeleteButton = new Button();
        dishDeleteButton.addClassName("delete-button");
        dishDeleteButton.getElement().appendChild(image.getElement());
        connectDiv.add(dishesCombobox, dishDeleteButton);
        connectDiv.addClassNames("delete-field");
        dishSearchSection.add(connectDiv);
        return dishSearchSection;
    }

    public void dishCheckboxListener() {
        dishesCombobox.addValueChangeListener(event -> {
            String input = event.getValue();
            if (input != null && !input.isEmpty()) {
                selectedDish = dishService.getByNameAndPrice(input);
                List<IngredientDTO> ingredientDTOS = selectedDish.getIngredients();
                log.info(ingredientDTOS.toString());
                if (!ingredientDTOS.isEmpty()){
                    for (IngredientDTO ingredientDTO : ingredientDTOS) {
                        ComboBox<String> ingredientCombobox = new ComboBox<>();
                        ingredientCombobox.setItems(ingredientDTO.getName());
                        ingredientCombobox.setValue(ingredientDTO.getName());
                        selectedIngredient.add(ingredientDTO);
                        ingredientCombobox.setEnabled(false);
                        ingredientCombobox.setPrefixComponent(VaadinIcon.SEARCH.create());
                        ingredientCombobox.addClassName("admin-search-field");
                        Div connectDiv = new Div();
                        Image image = getImage();
                        image.setHeight("35px");
                        Button delete = new Button();
                        delete.addClassName("delete-button");
                        delete.getElement().appendChild(image.getElement());
                        ingredientDeleteButtons.add(delete);
                        connectDiv.add(ingredientCombobox, delete);
                        connectDiv.addClassNames("delete-field");
                        dishSearchSection.add(connectDiv);
                    }
                }
            }
        });

        dishesCombobox.setItems(query -> {
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

    private Image getImage(){
        String scr = localDirectory + "/db_data/bin.png";

        StreamResource resource = new StreamResource("image.jpg", () -> {
            try {
                return new FileInputStream(scr);
            } catch (FileNotFoundException e) {
                log.error("No image found");
            }
            return null;
        });
        return new Image(resource, "Delete Image");
    }
}
