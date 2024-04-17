package ua.huryn.elasticsearch.view;

import com.vaadin.flow.component.Text;
import com.vaadin.flow.component.dependency.CssImport;
import com.vaadin.flow.component.dependency.StyleSheet;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.html.Image;
import com.vaadin.flow.component.icon.Icon;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
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
    private final RestaurantService restaurantService;
    CheckboxGroup<Integer> ratingCheckbox = new CheckboxGroup<>();
    CheckboxGroup<Integer> priceLevelCheckbox = new CheckboxGroup<>();
    Div menuDiv;

    public MenuView(RestaurantService restaurantService) {
        this.restaurantService = restaurantService;
        menuDiv = createMenuDiv();
        add(createSearchSection());
        add(createDownSection());

        ratingCheckbox.addValueChangeListener(event -> {
            updateMenu();
        });

        priceLevelCheckbox.addValueChangeListener(event -> {
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
        Div filtersDiv = createFilters(menuDiv);

        downSection.add(filtersDiv, menuDiv);
        return downSection;
    }

    private Div createMenuDiv() {
        menuDiv = new Div();
        menuDiv.addClassNames("w-75 flex-wrap basic main-div gap");

        List<RestaurantModel> filteredRestaurantModelList = getFilteredRestaurantModelList();

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

            Div name = new Div();
            name.addClassNames("info width-60");
            Text resName = new Text(restaurant.getName());
            Text location = new Text(restaurant.getAddress());
            name.add(resName, location);
            Div rate = new Div();
            rate.addClassNames("info width-30");

            Icon starIcon = VaadinIcon.STAR.create();
            starIcon.addClassNames("width-18");

            String formattedRating = String.format("  %.1f\n", restaurant.getRating());
            Text ratingValue = new Text(formattedRating);

            Text priceValue = new Text("" + restaurant.getPriceLevel());

            rate.add(starIcon, ratingValue, priceValue);
            info.add(name, rate);
            div.add(imageContainer, info);
            menuDiv.add(div);
        }

        return menuDiv;
    }

    private Div createFilters(Div menuDiv) {
        Div filtersDiv = new Div();
        filtersDiv.addClassNames("filters");

        String[] cuisineTypes = {"Ukrainian", "American", "Czech", "Polish", "German", "Greek", "Italian", "Spanish", "French", "Asian"};
        Div category = categoryFilter(cuisineTypes);
        Div rating = ratingFilter();
        Div price = priceFilter();

        filtersDiv.add(category, rating, price);

        return filtersDiv;
    }

    @NotNull
    private Div categoryFilter(String[] cuisineTypes) {
        Div categoryDiv = new Div();
        categoryDiv.addClassNames("main-div");

        Div categoryButtonsContainer = new Div();
        categoryButtonsContainer.addClassNames("category-container");

        ArrayList<Button> buttonList = new ArrayList<>();
        for (int i = 0; i < cuisineTypes.length; i++) {
            Button button = new Button(cuisineTypes[i]);
            buttonList.add(button);
        }

        for (Button button : buttonList) {
            button.addClassName("checkbox-btn");
            button.addClickListener(e -> {
                if (button.hasClassName("active")) {
                    button.removeClassName("active");
                } else {
                    button.addClassName("active");
                }
            });
            categoryButtonsContainer.add(button);
        }
        categoryDiv.add(categoryButtonsContainer);

        return categoryDiv;
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

    private List<RestaurantModel> getFilteredRestaurantModelList() {
        List<Integer> selectedPrices = priceCheckboxListener();
        List<Integer> selectedRating = ratingCheckboxListener();

        return restaurantService.getFiltered(selectedRating, selectedPrices);
    }
}
