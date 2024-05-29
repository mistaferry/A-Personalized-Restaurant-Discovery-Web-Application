package ua.huryn.elasticsearch.view;

import com.vaadin.flow.component.combobox.ComboBox;
import com.vaadin.flow.component.dependency.CssImport;
import com.vaadin.flow.component.dependency.StyleSheet;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.html.Input;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.provider.CallbackDataProvider;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import jakarta.annotation.security.PermitAll;
import ua.huryn.elasticsearch.entity.dto.DishDTO;
import ua.huryn.elasticsearch.entity.dto.IngredientDTO;
import ua.huryn.elasticsearch.entity.dto.RestaurantDTO;
import ua.huryn.elasticsearch.service.DishService;
import ua.huryn.elasticsearch.service.IngredientsService;
import ua.huryn.elasticsearch.service.RestaurantService;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@PageTitle("Admin")
@Route(value = "/admin", layout = MainView.class)
@CssImport("styles.css")
@PermitAll
@StyleSheet("https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css")
public class AdminView extends VerticalLayout {
    private final RestaurantService restaurantService;
    private final DishService dishService;
    private final IngredientsService ingredientsService;
    private List<RestaurantDTO> allRestaurants;
    private List<DishDTO> allDishes;
    private List<IngredientDTO> allIngredients;
    private ComboBox<String> restaurantsCombobox;
    private ComboBox<String> dishesCombobox;
    private ComboBox<String> ingredientsCombobox;
    private RestaurantDTO selectedRestaurant;
    private DishDTO selectedDish;

    public AdminView(RestaurantService restaurantService, DishService dishService, IngredientsService ingredientsService) {
        this.restaurantService = restaurantService;
        this.dishService = dishService;
        this.ingredientsService = ingredientsService;
        this.restaurantsCombobox = new ComboBox<>();
        this.dishesCombobox = new ComboBox<>();
        this.ingredientsCombobox = new ComboBox<>();
        this.allRestaurants = restaurantService.getAll();
        this.allDishes = dishService.getAll();
        this.allIngredients = ingredientsService.getAll();



        Div div = new Div();
        div.addClassNames("admin-full-div");

        Div createDiv = new Div();
        createDiv.addClassNames("admin-page-function");

        createDiv.add(createSearchByRestaurant());
        createDiv.add(createSearchByDish());

        createDiv.add(createSearchByIngredient());

        div.add(createDiv);
        add(createDiv);
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
        List<String> list = new ArrayList<>();
        this.restaurantsCombobox.addCustomValueSetListener(event -> {
            String input = event.getDetail();
            List<String> restaurantDetails = restaurantService.getRestaurantsDTOBySearchInEngAndUkr(input, allRestaurants)
                    .stream()
                    .map(restaurant -> restaurant.getName() + ", " + restaurant.getAddress())
                    .toList();
            this.restaurantsCombobox.setItems(restaurantDetails);
        });
        this.restaurantsCombobox.addValueChangeListener(event -> {
            String input = event.getValue();
            this.selectedRestaurant = (restaurantService.getRestaurantsDTOBySearchInEngAndUkr(input, allRestaurants)).get(0);
//            log.info("restaurant - {}", selectedRestaurant.getName() + ", " + selectedRestaurant.getAddress());
        });
        this.restaurantsCombobox.setItems(list);
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
private Div createSearchByDish() {
    Div searchSection = new Div();
    searchSection.addClassNames("search-section basic");

    dishesCombobox.setPlaceholder("Страва");
    dishesCombobox.setPrefixComponent(VaadinIcon.SEARCH.create());
    dishesCombobox.addClassName("admin-search-field");
    searchSection.add(dishesCombobox);

//    // Добавляем слушатель изменения значения
//    dishesCombobox.addValueChangeListener(event -> {
//        String input = event.getValue();
//        if (input != null && !input.isEmpty()) {
//            List<String> dishDetails = dishService.getDishDTOBySearch(input)
//                    .stream()
//                    .map(dish -> dish.getName() + ", " + dish.getPrice())
//                    .toList();
//            dishesCombobox.setItems(dishDetails);
//        } else {
//            // Очистка списка, если ввод пуст
//            dishesCombobox.clear();
//        }
//    });

    return searchSection;
}

    // Метод, добавляющий слушатель для обработки выбора элемента из ComboBox
    public void dishCheckboxListener() {
//        dishesCombobox.setItems(query -> {
//            String filter = query.getFilter().orElse("");
//            return dishService.getDishDTOBySearch(filter)
//                    .stream()
//                    .map(dish -> dish.getName() + ", " + dish.getPrice())
//                    .toList()
//                    .stream();
//        });
//
//        dishesCombobox.addValueChangeListener(event -> {
//            String input = event.getValue();
//            if (input != null && !input.isEmpty()) {
//                selectedDish = dishService.getDishDTOBySearch(input).get(0);
//                // log.info("selectedDish - {}", selectedDish.getName() + ", " + selectedDish.getPrice());
//            }
//        });
        dishesCombobox.addValueChangeListener(event -> {
            String input = event.getValue();
            if (input != null && !input.isEmpty()) {
                selectedDish = dishService.getDishDTOBySearch(input).get(0);
                // log.info("selectedDish - {}", selectedDish.getName() + ", " + selectedDish.getPrice());
            }
        });

        dishesCombobox.setItems(query -> {
            String filter = query.getFilter().orElse("");
            int limit = query.getLimit();
            int offset = query.getOffset(); // отримуємо offset з запиту
            List<String> dishDetails = dishService.getDishDTOBySearch(filter)
                    .stream()
                    .skip(offset) // пропускаємо вже відображені записи
                    .limit(limit)
                    .map(dish -> dish.getName() + ", " + dish.getPrice())
                    .toList();
            return dishDetails.stream();
        });
    }

    private Div createSearchByIngredient() {
        Div searchSection = new Div();
        searchSection.addClassNames("search-section basic");

        ingredientsCombobox.setPlaceholder("Інгредієнт");
        ingredientsCombobox.setPrefixComponent(VaadinIcon.SEARCH.create());
        ingredientsCombobox.addClassName("search-field");

        searchSection.add(ingredientsCombobox);

        return searchSection;
    }

    public void ingredientCheckboxListener(){
        List<String> list = new ArrayList<>();
        this.ingredientsCombobox.addCustomValueSetListener(event -> {
            String input = event.getDetail();
            List<String> restaurantDetails = restaurantService.getRestaurantsDTOBySearchInEngAndUkr(input, allRestaurants)
                    .stream()
                    .map(restaurant -> restaurant.getName() + ", " + restaurant.getAddress())
                    .toList();
            this.ingredientsCombobox.setItems(restaurantDetails);
        });
        this.ingredientsCombobox.addValueChangeListener(event -> {
            String input = event.getValue();
            this.selectedRestaurant = (restaurantService.getRestaurantsDTOBySearchInEngAndUkr(input, allRestaurants)).get(0);
//            log.info("restaurant - {}", selectedRestaurant.getName() + ", " + selectedRestaurant.getAddress());
        });
        this.ingredientsCombobox.setItems(list);
    }

    private void addDataToDb(){

    }
}
