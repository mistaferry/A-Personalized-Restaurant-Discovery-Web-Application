package ua.huryn.elasticsearch.view;

import com.google.apps.card.v1.TextInput;
import com.google.maps.model.TravelMode;
import com.google.maps.routing.v2.RouteTravelMode;
import com.vaadin.flow.component.Text;
import com.vaadin.flow.component.dependency.CssImport;
import com.vaadin.flow.component.dependency.StyleSheet;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.html.Image;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.data.value.ValueChangeMode;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import org.jetbrains.annotations.NotNull;
import ua.huryn.elasticsearch.MainView;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.component.button.Button;
import ua.huryn.elasticsearch.model.RestaurantModel;
import ua.huryn.elasticsearch.service.ItemService;
import ua.huryn.elasticsearch.service.RestaurantService;
import com.vaadin.flow.component.checkbox.CheckboxGroup;
import com.vaadin.flow.component.checkbox.CheckboxGroupVariant;

import java.util.*;
import java.util.List;

@PageTitle("Menu")
@Route(value = "", layout = MainView.class)
@CssImport("styles.css")
@StyleSheet("https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css")
public class MenuView extends VerticalLayout {
    private final ItemService itemService;
    private final RestaurantService restaurantService;
    CheckboxGroup<Integer> ratingCheckbox = new CheckboxGroup<>();
    CheckboxGroup<Integer> priceLevelCheckbox = new CheckboxGroup<>();
    CheckboxGroup<Integer> routeCheckbox = new CheckboxGroup<>();
    CheckboxGroup<String> cuisineCheckbox = new CheckboxGroup<>();
    TextField firstPoint = new TextField();
    Div menuDiv;

    public MenuView(RestaurantService restaurantService, ItemService itemService) {
        this.restaurantService = restaurantService;
        this.itemService = itemService;
//        menuDiv = createMenuDiv();
        add(createSearchSection());
        add(createDownSection());

        cuisineCheckbox.addValueChangeListener(event -> {
            updateMenu();
        });

        ratingCheckbox.addValueChangeListener(event -> {
            updateMenu();
        });

        priceLevelCheckbox.addValueChangeListener(event -> {
            updateMenu();
        });

        routeCheckbox.addValueChangeListener(event -> {
            System.out.println(routeCheckbox.getSelectedItems());
            updateMenu();
        });
    }

    private void updateMenu() {
        menuDiv.removeAll();
        menuDiv.add(createMenuDiv());
    }

    private Div createSearchSection() {
        Div searchSection = new Div();
        searchSection.addClassNames("search-section basic");

        TextField searchField = new TextField();
        searchField.setPlaceholder("Search...");
        searchField.setPrefixComponent(VaadinIcon.SEARCH.create());
        searchField.addClassName("search-field");

        Button searchButton = new Button("Find");
        searchButton.addClassNames("search-button");

        searchSection.add(searchField, searchButton);

        return searchSection;
    }

    private Div createDownSection() {
        Div downSection = new Div();
        downSection.addClassNames("down-section");

        Div menuDiv = createMenuDiv();
        Div filtersDiv = createFilters();

        downSection.add(filtersDiv, menuDiv);
        return downSection;
    }

    private Div createMenuDiv() {
        menuDiv = new Div();
        menuDiv.addClassNames("menu-div");

        List<RestaurantModel> filteredRestaurantModelList = getFilteredRestaurantModelList();
//        System.out.println("filtered-size - "+ filteredRestaurantModelList.size());
        Div restaurantsDiv = new Div();
        restaurantsDiv.addClassNames("d-flex justify-content-evenly flex-wrap");

        for(RestaurantModel restaurant: filteredRestaurantModelList){
            Div div = new Div();
            div.addClassNames("item_div");

            Div imageContainer = new Div();
            imageContainer.addClassNames("d-flex justify-content-center");

            Image image = new Image();
            image.setSrc("https://pianavyshnia.com/wp-content/uploads/2022/10/logo.png");
            image.addClassNames("image");
            imageContainer.add(image);

            Div info = new Div();
            info.addClassNames("d-flex flex-row justify-content-between");

//            Div name = new Div();
//            name.addClassNames("info width-60");
//            Text resName = new Text(restaurant.getName());
//            Text location = new Text(restaurant.getAddress());
//            name.add(resName, location);
//            Div rate = new Div();
//            rate.addClassNames("info width-30");
//
//            Icon starIcon = VaadinIcon.STAR.create();
//            starIcon.addClassNames("width-18");

            String formattedRating = String.format("  %.1f\n", restaurant.getRating());
            Text ratingValue = new Text(formattedRating);

            Text priceValue = new Text("" + restaurant.getPriceLevel());

//            rate.add(starIcon, ratingValue, priceValue);
//            info.add(name, rate);
            div.add(imageContainer, info);
            restaurantsDiv.add(div);
        }
        menuDiv.add(restaurantsDiv);
        return menuDiv;
    }

    private Div createFilters() {
        Div filtersDiv = new Div();
        filtersDiv.addClassNames("filters");

        Div cuisineType = cuisineTypeFilter();
        Div rating = ratingFilter();
        Div price = priceFilter();
        Div route = routeFilter();

        filtersDiv.add(cuisineType, rating, price, route);

        return filtersDiv;
    }

    @NotNull
    private Div cuisineTypeFilter() {
        List<String> cuisineTypes = restaurantService.getCuisineTypeFromJson();

        Div cuisineTypeDiv = new Div();
        cuisineTypeDiv.addClassNames("main-div");

        Div cuisineTypesContainer = new Div();
        cuisineTypesContainer.addClassNames("d-flex flex-wrap justify-content-between border-top border-bottom");

        cuisineCheckbox.setLabel("Cuisine type");
        cuisineCheckbox.setItems(cuisineTypes);
        cuisineCheckbox.addThemeVariants(CheckboxGroupVariant.LUMO_VERTICAL);
        cuisineTypesContainer.add(cuisineCheckbox);
        cuisineTypeDiv.add(cuisineTypesContainer);

        return cuisineTypeDiv;
    }

    @NotNull
    private Div ratingFilter(){
        Div ratingDiv = new Div();
        ratingDiv.addClassNames("main-div");

        Div buttonsContainer = new Div();
        buttonsContainer.addClassNames("d-flex flex-wrap justify-content-between border-top border-bottom");

        Map<Integer, String> checkboxValues = new HashMap<>();
        checkboxValues.put(5, "★★★★★");
        checkboxValues.put(4, "★★★★☆");
        checkboxValues.put(3, "★★★☆☆");
        checkboxValues.put(2, "★★☆☆☆");
        checkboxValues.put(1, "★☆☆☆☆");

        ratingCheckbox.setLabel("Rating");
        ratingCheckbox.setItems(5, 4, 3, 2, 1);
        ratingCheckbox.setItemLabelGenerator(checkboxValues::get);
        ratingCheckbox.addThemeVariants(CheckboxGroupVariant.LUMO_VERTICAL);

        buttonsContainer.add(ratingCheckbox);
        ratingDiv.add(buttonsContainer);

        return ratingDiv;
    }

    @NotNull
    private Div priceFilter(){
        Div priceDiv = new Div();
        priceDiv.addClassNames("main-div");

        Div buttonsContainer = new Div();
        buttonsContainer.addClassNames("d-flex flex-wrap justify-content-between border-top border-bottom");

        Map<Integer, String> checkboxValues = new HashMap<>();
        checkboxValues.put(4, "₴₴₴₴₴");
        checkboxValues.put(3, "₴₴₴ - ₴₴₴₴");
        checkboxValues.put(2, "₴₴ - ₴₴₴");
        checkboxValues.put(1, "₴ - ₴₴");
        checkboxValues.put(0, "₴");

        priceLevelCheckbox.setLabel("Price Level");
        priceLevelCheckbox.setItems(4, 3, 2, 1, 0);
        priceLevelCheckbox.setItemLabelGenerator(checkboxValues::get);
        priceLevelCheckbox.addThemeVariants(CheckboxGroupVariant.LUMO_VERTICAL);

        buttonsContainer.add(priceLevelCheckbox);
        priceDiv.add(buttonsContainer);

        return priceDiv;
    }

    @NotNull
    private Div routeFilter(){
        Div routeDiv = new Div();
        routeDiv.addClassNames("main-div");

        Div checkboxContainer = new Div();
        checkboxContainer.addClassNames("d-flex flex-wrap justify-content-between border-top border-bottom pd-2");

        Map<Integer, String> checkboxValues = new HashMap<>();
        checkboxValues.put(2, "Пішки");
        checkboxValues.put(1, "Машина");
        checkboxValues.put(0, "Географічна віддаленість");

        routeCheckbox.setLabel("Type of transport");
        routeCheckbox.setItems(2, 1, 0);
        routeCheckbox.setItemLabelGenerator(checkboxValues::get);
        routeCheckbox.addThemeVariants(CheckboxGroupVariant.LUMO_VERTICAL);

        String regexPattern = "^[a-zA-Zа-яА-ЯїЇіІєЄ0-9 .]+, [0-9]{1,3}$";
        firstPoint.setPattern(regexPattern);
        firstPoint.setErrorMessage("Invalid format. Should be 'address, number' or 'coordinates, number'.");

        firstPoint.setValueChangeMode(ValueChangeMode.EAGER);

        firstPoint.addValueChangeListener(event -> {
            if (firstPoint.isInvalid()) {
                System.out.println("Invalid input");
            }
        });

        checkboxContainer.add(firstPoint, routeCheckbox);
        routeDiv.add(checkboxContainer);

        return routeDiv;
    }

    private List<Integer> ratingCheckboxListener() {
        Set<Integer> selectedItems = ratingCheckbox.getValue();
//        System.out.println("rating - " + selectedItems);
        return new ArrayList<>(selectedItems);
    }

    private List<Integer> priceCheckboxListener() {
        Set<Integer> selectedItems = priceLevelCheckbox.getValue();
//        System.out.println("priceLevel - " + selectedItems);
        return new ArrayList<>(selectedItems);
    }

    private List<String> cuisineCheckboxListener() {
        Set<String> selectedItems = cuisineCheckbox.getValue();
//        System.out.println("cuisine - " + selectedItems);
        return new ArrayList<>(selectedItems);
    }

    private List<Integer> routeCheckboxListener() {
        Set<Integer> selectedItems = routeCheckbox.getValue();
//        System.out.println("route - " + selectedItems);
        return new ArrayList<>(selectedItems);
    }

    private List<RestaurantModel> getFilteredRestaurantModelList() {
        List<String> selectedCuisine = cuisineCheckboxListener();
        List<Integer> selectedPrices = priceCheckboxListener();
        List<Integer> selectedRating = ratingCheckboxListener();
        List<Integer> selectedRoutes = routeCheckboxListener();

        return restaurantService.getFiltered(selectedCuisine, selectedRating, selectedPrices, selectedRoutes, firstPoint.getValue());
    }
}
