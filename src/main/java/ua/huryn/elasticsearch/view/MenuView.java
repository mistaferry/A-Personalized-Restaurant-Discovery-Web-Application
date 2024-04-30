package ua.huryn.elasticsearch.view;

import com.vaadin.flow.component.Text;
import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.checkbox.Checkbox;
import com.vaadin.flow.component.combobox.ComboBox;
import com.vaadin.flow.component.dependency.CssImport;
import com.vaadin.flow.component.dependency.StyleSheet;
import com.vaadin.flow.component.dialog.Dialog;
import com.vaadin.flow.component.html.Anchor;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.html.Image;
import com.vaadin.flow.component.html.Span;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.dialog.Dialog;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.orderedlayout.FlexComponent;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.dom.Style;
import com.vaadin.flow.router.*;
import elemental.json.Json;
import elemental.json.JsonObject;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ua.huryn.elasticsearch.MainView;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.component.button.Button;
import ua.huryn.elasticsearch.entity.dto.RestaurantDTO;
import ua.huryn.elasticsearch.service.DishService;
import ua.huryn.elasticsearch.service.RestaurantService;
import com.vaadin.flow.component.checkbox.CheckboxGroup;
import com.vaadin.flow.component.checkbox.CheckboxGroupVariant;

import java.util.*;
import java.util.List;
import java.util.stream.Collectors;

@PageTitle("Menu")
@Route(value = "", layout = MainView.class)
@CssImport("styles.css")
@StyleSheet("https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css")
public class MenuView extends VerticalLayout implements BeforeEnterObserver {
    private final RestaurantService restaurantService;
    private final DishService dishService;
    CheckboxGroup<Integer> ratingCheckbox = new CheckboxGroup<>();
    CheckboxGroup<Integer> priceLevelCheckbox = new CheckboxGroup<>();
    CheckboxGroup<Integer> routeCheckbox = new CheckboxGroup<>();
    CheckboxGroup<String> cuisineCheckbox = new CheckboxGroup<>();
    CheckboxGroup<String> dishesCheckbox = new CheckboxGroup<>();
    Button dishesSaveButton;
    ComboBox<String> dishComboBox = new ComboBox<>("Dish");
    TextField routesDeparturePoint = new TextField();
    TextField fullTextSearchField = new TextField();
    Button fullTextButton = new Button();
    Div menuDiv;

    @Autowired
    public MenuView(RestaurantService restaurantService, DishService dishService) {
        this.restaurantService = restaurantService;
        this.dishService = dishService;

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

//        routeCheckbox.addValueChangeListener(event -> {
//            System.out.println(routeCheckbox.getSelectedItems());
//            updateMenu();
//        });

        dishesCheckbox.addValueChangeListener(event -> {
            System.out.println(dishesCheckbox.getSelectedItems());
        });

        fullTextButton.addClickListener(event -> {
            updateMenu();
        });

    }

    private void updateMenu() {
        updateUrlWithFilters();
        menuDiv.removeAll();
        menuDiv.add(createMenuDiv());
    }

    private Div createMenuDiv() {
        menuDiv = new Div();
        menuDiv.addClassNames("menu-div");

        List<RestaurantDTO> filteredRestaurantModelList = getFilteredRestaurantModelList();
        Div restaurantsDiv = new Div();
        restaurantsDiv.addClassNames("d-flex gap justify-content-center flex-wrap");

        for (RestaurantDTO restaurant : filteredRestaurantModelList) {
            Div div = createRestaurantItem(restaurant);
            restaurantsDiv.add(div);
        }
        menuDiv.add(restaurantsDiv);
        return menuDiv;
    }

    private Div createSearchSection() {
        Div searchSection = new Div();
        searchSection.addClassNames("search-section basic");

        fullTextSearchField.setPlaceholder("Search...");
        fullTextSearchField.setPrefixComponent(VaadinIcon.SEARCH.create());
        fullTextSearchField.addClassName("search-field");

        fullTextButton.setText("Find");
        fullTextButton.addClassNames("search-button");

        searchSection.add(fullTextSearchField, fullTextButton);

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

    @NotNull
    private Div createRestaurantItem(RestaurantDTO restaurant) {
        Div div = new Div();
        div.addClassNames("item_div");

        Div imageContainer = new Div();
        imageContainer.addClassNames("d-flex justify-content-center");

//        String scr = restaurant.getPhotoRef();
//        if(scr == null){
//            scr = "src/main/resources/db_data/restaurant_images/stock.jpg";
//        }
//        final String path = scr;
//        StreamResource imageResource = new StreamResource(restaurant.getName().toLowerCase() + "_image.jpg",
//                () -> getClass().getResourceAsStream(path));

        Image image = new Image();
        image.setSrc("https://pianavyshnia.com/wp-content/uploads/2022/10/logo.png");
        image.addClassNames("image");
        imageContainer.add(image);

        Div info = new Div();
        info.addClassNames("d-flex flex-row justify-content-around");

        Div infoDiv = new Div();

        infoDiv.addClassNames("info flex-column width-60");
        int end = Math.min(24, restaurant.getName().length());
        String route = RouteConfiguration.forSessionScope()
                .getUrl(RestaurantView.class, restaurant.getRestaurantId());
        Anchor restaurantName = new Anchor(route, restaurant.getName().substring(0, end));
        restaurantName.getStyle().setTextDecoration("none").setColor("000000FF");
        Div resNameDiv = new Div();
        resNameDiv.add(restaurantName);

        String cuisine = restaurant.getCuisineType();
        Span cuisineType = null;
        if (!cuisine.isBlank()) {
            cuisine = cuisine.substring(0, 1).toUpperCase() + cuisine.substring(1);
            cuisineType = new Span(cuisine + " cuisine");
        } else {
            cuisineType = new Span("");
        }
        cuisineType.getStyle().setColor("#0074D9").setFontSize("smaller").setFontWeight(Style.FontWeight.BOLD);
        Div cuisineDiv = new Div(cuisineType);
        Map<Integer, String> checkboxValues = new HashMap<>();
        checkboxValues.put(4, "₴₴₴₴₴");
        checkboxValues.put(3, "₴₴₴ - ₴₴₴₴");
        checkboxValues.put(2, "₴₴ - ₴₴₴");
        checkboxValues.put(1, "₴ - ₴₴");
        checkboxValues.put(0, "₴");
        Div priceDiv = new Div(new Span(checkboxValues.get(restaurant.getPriceLevel())));
        infoDiv.add(resNameDiv, cuisineDiv, priceDiv);


        Div rate = new Div();
        rate.addClassNames("info width-30");

        String formattedRating = String.format("⭐ " + Math.round(restaurant.getRating()));
        Text ratingValue = new Text(formattedRating);

        rate.add(ratingValue);
        info.add(infoDiv, rate);
        div.add(imageContainer, info);
        return div;
    }

    private Div createFilters() {
        Div filtersDiv = new Div();
        filtersDiv.addClassNames("filters");

        Div cuisineType = cuisineTypeFilter();
        Div rating = ratingFilter();
        Div price = priceFilter();
//        Div route = routeFilter();
        Div dishes = dishesFilter();

        filtersDiv.add(cuisineType, rating, price, dishes);

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
    private Div ratingFilter() {
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
    private Div priceFilter() {
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

//    @NotNull
//    private Div routeFilter(){
//        Div routeDiv = new Div();
//        routeDiv.addClassNames("main-div");
//
//        Div checkboxContainer = new Div();
//        checkboxContainer.addClassNames("d-flex flex-wrap justify-content-between border-top border-bottom pd-2");
//
//        Map<Integer, String> checkboxValues = new HashMap<>();
//        checkboxValues.put(2, "Пішки");
//        checkboxValues.put(1, "Машина");
//        checkboxValues.put(0, "Географічна віддаленість");
//
//        routeCheckbox.setLabel("Type of transport");
//        routeCheckbox.setItems(2, 1, 0);
//        routeCheckbox.setItemLabelGenerator(checkboxValues::get);
//        routeCheckbox.addThemeVariants(CheckboxGroupVariant.LUMO_VERTICAL);
//
//        String regexPattern = "^[a-zA-Zа-яА-ЯїЇіІєЄ0-9 .]+, [0-9]{1,3}$";
//        firstPoint.setPattern(regexPattern);
//        firstPoint.setErrorMessage("Invalid format. Should be 'address, number' or 'coordinates, number'.");
//
//        firstPoint.setValueChangeMode(ValueChangeMode.EAGER);
//
//        firstPoint.addValueChangeListener(event -> {
//            if (firstPoint.isInvalid()) {
//                System.out.println("Invalid input");
//            }
//        });
//
//        checkboxContainer.add(firstPoint, routeCheckbox);
//        routeDiv.add(checkboxContainer);
//
//        return routeDiv;
//    }

    @NotNull
    private Div dishesFilter() {
        Div dishDiv = new Div();
        dishDiv.addClassNames("main-div");

        Div dishesContainer = new Div();
        dishesContainer.addClassNames("d-flex flex-wrap justify-content-between border-top border-bottom");

        Map<Long, String> checkboxValues = dishService.getAllDishesNames();
        dishesCheckbox.setItems(checkboxValues.values());
        dishesCheckbox.addThemeVariants(CheckboxGroupVariant.LUMO_VERTICAL);


//        dishDiv.add();

        Dialog dialog = addDialogElement();
        Button button = new Button("Show all", e -> dialog.open());
        dishDiv.add(dialog, button);

        return dishDiv;
    }

    private Dialog addDialogElement(){
        Dialog dialog = new Dialog();
        dialog.setHeaderTitle("Select dishes");

        VerticalLayout dialogLayout = createDialogLayout();
        dialog.add(dialogLayout);

        dishesSaveButton = createSaveButton(dialog);

        Button cancelButton = new Button("Cancel", e -> dialog.close());
        dialog.getFooter().add(cancelButton, dishesSaveButton);

        return dialog;
    }

    private VerticalLayout createDialogLayout() {
//        int columns = 3;
//        VerticalLayout gridLayout = new VerticalLayout();
//        gridLayout.setSpacing(false);
//
//        List<Checkbox> checkboxes = dishesCheckbox.getChildren()
//                .filter(component -> component instanceof Checkbox)
//                .map(component -> (Checkbox) component)
//                .toList();
//
//        int size = checkboxes.size();
//        int currentIndex = 0;
//
//        while (currentIndex < size) {
//            HorizontalLayout row = new HorizontalLayout();
//            row.setSpacing(true);
//            row.getStyle().setJustifyContent(Style.JustifyContent.SPACE_BETWEEN);
//            row.getStyle().setWidth("100%");
//            for (int i = 0; i < columns; i++) {
//                if (currentIndex < size) {
//                    Checkbox checkbox = dishesCheckbox.getChildren().;
//                    checkbox.getStyle().setWidth("30%");
//
//                    row.add(checkbox);
//                    currentIndex++;
//                }
//            }
//            gridLayout.add(row);
//        }
//        return gridLayout;


        VerticalLayout dialogLayout = new VerticalLayout();

        dishComboBox.setItems(dishService.getAllDishesNames().values());
        dishComboBox.setItemLabelGenerator(String::toString);

        dialogLayout.add(dishComboBox, dishesCheckbox);
        return dialogLayout;
    }

    private Button createSaveButton(Dialog dialog) {
        Button button = new Button("Save", e -> {
            System.out.println(dishesCheckbox.getSelectedItems());
            updateMenu();
            dialog.close();
        });

        button.addThemeVariants(ButtonVariant.LUMO_PRIMARY);
        return button;
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

    private void updateUrlWithFilters() {
        List<String> selectedCuisine = cuisineCheckboxListener();
        List<Integer> selectedPrices = priceCheckboxListener();
        List<Integer> selectedRating = ratingCheckboxListener();
        List<Integer> selectedRoutes = routeCheckboxListener();

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
        // Create the query parameter for cuisines and price
        String query = "route=" + routesQuery +
                "&price=" + priceQuery + "&rating=" + ratingQuery + "&cuisine=" + cuisinesQuery;
        JsonObject stateData = Json.createObject();

        // Update the URL with the new query parameters without reloading the page
        UI.getCurrent().getPage().getHistory().replaceState(stateData, "?" + query);
    }

    @Override
    public void beforeEnter(BeforeEnterEvent event) {
        var queryParameters = event.getLocation().getQueryParameters().getParameters();

        Set<String> cuisineSet = getStringSetFromQueryParameters(queryParameters, "cuisine");
        if (!cuisineSet.isEmpty()) {
            cuisineCheckbox.setValue(cuisineSet);
        }

        Set<Integer> ratingSet = getIntegerSetFromQueryParameters(queryParameters, "rating");
        if (!ratingSet.isEmpty()) {
            ratingCheckbox.setValue(ratingSet);
        }

        Set<Integer> priceLevelSet = getIntegerSetFromQueryParameters(queryParameters, "price");
        if (!priceLevelSet.isEmpty()) {
            priceLevelCheckbox.setValue(priceLevelSet);
        }

        Set<Integer> routeSet = getIntegerSetFromQueryParameters(queryParameters, "route");
        if (!routeSet.isEmpty()) {
            routeCheckbox.setValue(routeSet);
        }
    }

    Set<String> getStringSetFromQueryParameters(Map<String, List<String>> queryParams, String key) {
        if (queryParams.containsKey(key) && !queryParams.get(key).isEmpty()) {
            String param = queryParams.get(key).get(0);
            if (param != null && !param.trim().isEmpty()) {
                return Set.of(param.trim().split(",\\s*"));
            }
        }
        return Collections.emptySet();
    }

    Set<Integer> getIntegerSetFromQueryParameters(Map<String, List<String>> queryParams, String key) {
        Set<String> stringSet = getStringSetFromQueryParameters(queryParams, key);
        if (stringSet.isEmpty()) {
            return Collections.emptySet();
        }

        return stringSet.stream()
                .filter(s -> s != null && !s.trim().isEmpty())
                .map(s -> {
                    try {
                        return Integer.parseInt(s.trim());
                    } catch (NumberFormatException e) {
                        return null;
                    }
                })
                .filter(Objects::nonNull)
                .collect(Collectors.toSet());
    }

    private List<RestaurantDTO> getFilteredRestaurantModelList() {
        List<String> selectedCuisine = cuisineCheckboxListener();
        List<Integer> selectedPrices = priceCheckboxListener();
        List<Integer> selectedRating = ratingCheckboxListener();
        List<Integer> selectedRoutes = routeCheckboxListener();
        String routeDeparturePointValue = routesDeparturePoint.getValue();
        String fullTextSearch = fullTextSearchField.getValue();

        return restaurantService.getFiltered(selectedCuisine, selectedRating, selectedPrices, selectedRoutes, routeDeparturePointValue, fullTextSearch);
    }
}
