package ua.huryn.elasticsearch.view;

import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.dependency.CssImport;
import com.vaadin.flow.component.dependency.StyleSheet;
import com.vaadin.flow.component.html.*;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.router.*;
import elemental.json.Json;
import elemental.json.JsonObject;
import jakarta.annotation.security.PermitAll;
import jakarta.annotation.security.RolesAllowed;
import org.springframework.beans.factory.annotation.Autowired;
import ua.huryn.elasticsearch.MainView;
import ua.huryn.elasticsearch.config.GeneralProperties;
import ua.huryn.elasticsearch.entity.dto.RestaurantDTO;
import ua.huryn.elasticsearch.service.DishService;
import ua.huryn.elasticsearch.service.IngredientsService;
import ua.huryn.elasticsearch.service.RestaurantService;
import ua.huryn.elasticsearch.view.components.Filters;
import ua.huryn.elasticsearch.view.components.RestaurantItem;

import java.util.*;
import java.util.List;

import static ua.huryn.elasticsearch.utils.QueryParameter.getIntegerSetFromQueryParameters;
import static ua.huryn.elasticsearch.utils.QueryParameter.getStringSetFromQueryParameters;

@PageTitle("Menu")
@Route(value = "", layout = MainView.class)
@CssImport("styles.css")
@PermitAll
@StyleSheet("https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css")
public class MenuView extends VerticalLayout implements BeforeEnterObserver {
    private final Filters filters;
    private final RestaurantItem restaurantItem;
    private final RestaurantService restaurantService;
    private final DishService dishService;
    private final IngredientsService ingredientsService;
    private final GeneralProperties generalProperties;
    private int currentPage = 0;
    private int pageSize = 10;

    private final Button fullTextButton = new Button();
    TextField routesDeparturePoint;
    TextField fullTextSearchField = new TextField();
    Div menuDiv;

    @Autowired
    public MenuView(RestaurantService restaurantService, DishService dishService, IngredientsService ingredientsService, GeneralProperties generalProperties) {
        this.restaurantService = restaurantService;
        this.dishService = dishService;
        this.ingredientsService = ingredientsService;
        this.generalProperties = generalProperties;
        this.filters = new Filters(restaurantService, dishService, ingredientsService);
        this.restaurantItem = new RestaurantItem(generalProperties);

        addListeners(this::updateMenu);

        fullTextButton.addClickListener(event -> {
            updateMenu();
        });

        add(createSearchSection(), createDownSection());
    }

    public void addListeners(Runnable listener) {
        filters.getCuisineCheckbox().addValueChangeListener(e -> listener.run());
        filters.getRatingCheckbox().addValueChangeListener(e -> listener.run());
        filters.getPriceLevelCheckbox().addValueChangeListener(e -> listener.run());
        filters.getRouteCheckbox().addValueChangeListener(e -> listener.run());
        filters.getDishesCheckbox().addValueChangeListener(e -> listener.run());
        filters.getIngredientsCheckbox().addValueChangeListener(e -> listener.run());
    }

    private void updateMenu() {
        this.currentPage = 0;
        updateUrlWithFilters();
        menuDiv.removeAll();
        menuDiv.add(createMenuDiv());
    }

    public Div createMenuDiv() {
        menuDiv = new Div();
        menuDiv.addClassNames("menu-div");

        Div restaurantsDiv = new Div();
        restaurantsDiv.addClassNames("d-flex gap justify-content-center flex-wrap");
        List<RestaurantDTO> filteredRestaurantModelList = getFilteredRestaurantModelList();

        if (!filteredRestaurantModelList.isEmpty()) {
            int totalItems = filteredRestaurantModelList.size();
            int totalPages = (int) Math.ceil((double) totalItems / pageSize);
            int start = currentPage * pageSize;
            int end = Math.min(start + pageSize, totalItems);
            List<RestaurantDTO> currentRestaurants = filteredRestaurantModelList.subList(start, end);


            for (RestaurantDTO restaurant : currentRestaurants) {
                restaurantsDiv.add(restaurantItem.create(restaurant));
            }

            Div paginationDiv = new Div();
            paginationDiv.addClassNames("pagination-controls");

            if (currentPage > 0) {
                Button previous = new Button("", event -> {
                    currentPage--;
                    menuDiv.removeAll();
                    menuDiv.add(createMenuDiv());
                });
                previous.setIcon(VaadinIcon.ANGLE_LEFT.create());
                paginationDiv.add(previous);
            }

            Div pageNum = new Div(new Button(" " + (currentPage + 1) + " "));
            paginationDiv.add(pageNum);

            if (currentPage < totalPages - 1) {
                Button next = new Button("", event -> {
                    currentPage++;
                    menuDiv.removeAll();
                    menuDiv.add(createMenuDiv());
                });
                next.setIcon(VaadinIcon.ANGLE_RIGHT.create());
                paginationDiv.add(next);
            }

            menuDiv.add(paginationDiv);
        } else {
            H4 noData = new H4("Немає ресторанів, що задовільняють дані фільтри.");
            restaurantsDiv.add(noData);
        }
        menuDiv.add(restaurantsDiv);


        return menuDiv;
    }

    private Div createSearchSection() {
        Div searchSection = new Div();
        searchSection.addClassNames("search-section basic");

        fullTextSearchField.setPlaceholder("Пошук...");
        fullTextSearchField.setPrefixComponent(VaadinIcon.SEARCH.create());
        fullTextSearchField.addClassName("search-field");

        fullTextButton.setText("Знайти");
        fullTextButton.addClassNames("search-button");

        searchSection.add(fullTextSearchField, fullTextButton);

        return searchSection;
    }

    private Div createDownSection() {
        Div downSection = new Div();
        downSection.addClassNames("down-section");

        Div menuDiv = createMenuDiv();
        Div filtersDiv = filters.createFiltersDiv();

        downSection.add(filtersDiv, menuDiv);
        return downSection;
    }

    private void updateUrlWithFilters() {
        List<String> selectedCuisine = filters.getCuisineCheckbox().getSelectedItems().stream().toList();
        List<Integer> selectedPrices = filters.getPriceLevelCheckbox().getSelectedItems().stream().toList();
        List<Integer> selectedRating = filters.getRatingCheckbox().getSelectedItems().stream().toList();
        List<Integer> selectedRoutes = filters.getRouteCheckbox().getSelectedItems().stream().toList();
        List<String> selectedDishes = filters.getDishesCheckbox().getSelectedItems().stream().toList();
        List<String> selectedIngredients = filters.getIngredientsCheckbox().getSelectedItems().stream().toList();

        List<String> prices = new ArrayList<>();
        for (int i = 0; i < selectedPrices.size(); i++) {
            prices.add(selectedPrices.get(i).toString());
        }
        List<String> rating = new ArrayList<>();
        for (int i = 0; i < selectedRating.size(); i++) {
            rating.add(selectedRating.get(i).toString());
        }
        List<String> routes = new ArrayList<>();
        for (int i = 0; i < selectedRoutes.size(); i++) {
            routes.add(selectedRoutes.get(i).toString());
        }

        String cuisinesQuery = String.join(",", selectedCuisine);
        String priceQuery = String.join(",", prices);
        String ratingQuery = String.join(",", rating);
        String routesQuery = String.join(",", routes);
        String dishesQuery = String.join(",", selectedDishes);
        String ingredientsQuery = String.join(",", selectedIngredients);
        // Create the query parameter for cuisines and price
        String query = "route=" + routesQuery +
                "&price=" + priceQuery + "&rating=" + ratingQuery + "&cuisine=" + cuisinesQuery + "&dish=" + dishesQuery + "&ingredient=" + ingredientsQuery;
        JsonObject stateData = Json.createObject();

        UI.getCurrent().getPage().getHistory().replaceState(stateData, "?" + query);
    }

    public List<RestaurantDTO> getFilteredRestaurantModelList() {
        List<String> selectedCuisine = filters.getCuisineCheckbox().getSelectedItems().stream().toList();
        List<Integer> selectedPrices = filters.getPriceLevelCheckbox().getSelectedItems().stream().toList();
        List<Integer> selectedRating = filters.getRatingCheckbox().getSelectedItems().stream().toList();
        List<Integer> selectedRoutes = filters.getRouteCheckbox().getSelectedItems().stream().toList();
        List<String> selectedDishes = filters.getDishesCheckbox().getSelectedItems().stream().toList();
        List<String> selectedIngredients = filters.getIngredientsCheckbox().getSelectedItems().stream().toList();
//        String routeDeparturePointValue = routesDeparturePoint.getValue();
        String fullTextSearch = fullTextSearchField.getValue();

        String routeDeparturePointValue = null;
        return restaurantService.getFiltered(selectedCuisine, selectedRating, selectedPrices, selectedRoutes, selectedDishes, selectedIngredients, routeDeparturePointValue, fullTextSearch);
    }

    @Override
    public void beforeEnter(BeforeEnterEvent event) {
        var queryParameters = event.getLocation().getQueryParameters().getParameters();

        Set<String> cuisineSet = getStringSetFromQueryParameters(queryParameters, "cuisine");
        if (!cuisineSet.isEmpty()) {
            filters.getCuisineCheckbox().setValue(cuisineSet);
        }

        Set<Integer> ratingSet = getIntegerSetFromQueryParameters(queryParameters, "rating");
        if (!ratingSet.isEmpty()) {
            filters.getRatingCheckbox().setValue(ratingSet);
        }

        Set<Integer> priceLevelSet = getIntegerSetFromQueryParameters(queryParameters, "price");
        if (!priceLevelSet.isEmpty()) {
            filters.getPriceLevelCheckbox().setValue(priceLevelSet);
        }

        Set<Integer> routeSet = getIntegerSetFromQueryParameters(queryParameters, "route");
        if (!routeSet.isEmpty()) {
            filters.getRouteCheckbox().setValue(routeSet);
        }
    }
}
