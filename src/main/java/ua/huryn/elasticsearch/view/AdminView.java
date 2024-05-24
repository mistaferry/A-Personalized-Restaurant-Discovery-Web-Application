package ua.huryn.elasticsearch.view;

import com.vaadin.flow.component.combobox.ComboBox;
import com.vaadin.flow.component.dependency.CssImport;
import com.vaadin.flow.component.dependency.StyleSheet;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import jakarta.annotation.security.PermitAll;
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
    private ComboBox<String> restaurants;

    public AdminView(RestaurantService restaurantService, DishService dishService, IngredientsService ingredientsService) {
        this.restaurantService = restaurantService;
        this.dishService = dishService;
        this.ingredientsService = ingredientsService;
        this.restaurants = new ComboBox<>();
        this.allRestaurants = restaurantService.getAll();

        add(addRestaurantInfo());

        restaurantCheckboxListener();
    }

    private Div addRestaurantInfo(){
        Div div = new Div();
        div.addClassNames("admin-full-div");

        Div createDiv = new Div();
        createDiv.addClassNames("admin-page-function");

        createDiv.add(createSearchByRestaurant());

        div.add(createDiv);
        return div;
    }

    private Div createSearchByRestaurant() {
        Div searchSection = new Div();
        searchSection.addClassNames("search-section basic");

        restaurants.setPlaceholder("Ресторан");
        restaurants.setPrefixComponent(VaadinIcon.SEARCH.create());
        restaurants.addClassName("search-field");

        searchSection.add(restaurants);

        return searchSection;
    }

    public void restaurantCheckboxListener(){
        List<String> list = new ArrayList<>();
        this.restaurants.addCustomValueSetListener(event -> {
            String input = event.getDetail();
            List<String> restaurantDetails = restaurantService.getRestaurantsDTOBySearchInEngAndUkr(input, allRestaurants)
                    .stream()
                    .map(restaurant -> restaurant.getName() + ", " + restaurant.getAddress())
                    .toList();
            this.restaurants.setItems(restaurantDetails);
        });
        this.restaurants.setItems(list);
    }
}
