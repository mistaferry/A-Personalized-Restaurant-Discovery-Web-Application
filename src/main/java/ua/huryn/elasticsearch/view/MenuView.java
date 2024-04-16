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
import jakarta.persistence.criteria.CriteriaBuilder;
import org.jetbrains.annotations.NotNull;
import org.springframework.data.domain.Sort;
import ua.huryn.elasticsearch.MainView;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.component.button.Button;
import ua.huryn.elasticsearch.model.Item;
import ua.huryn.elasticsearch.model.RestaurantModel;
import ua.huryn.elasticsearch.service.ItemService;
import ua.huryn.elasticsearch.service.RestaurantService;
import com.vaadin.flow.component.checkbox.CheckboxGroup;
import com.vaadin.flow.component.checkbox.CheckboxGroupVariant;

import java.awt.*;
import java.util.*;
import java.util.List;

@PageTitle("Menu")
@Route(value = "", layout = MainView.class)
@CssImport("styles.css")
@StyleSheet("https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css")
public class MenuView extends VerticalLayout {
    private final RestaurantService restaurantService;
    private final ItemService itemService;

    public MenuView(RestaurantService restaurantService, ItemService itemService) {
        this.restaurantService = restaurantService;
        this.itemService = itemService;

        add(createSearchSection());
        add(createDownSection());

    }

    private Div createSearchSection() {
        Div searchSection = new Div();
        searchSection.addClassNames("search-section basic");
//        searchSection.addClassNames("w-100 p-2 mx-1 d-flex justify-content-center align-items-center");

        TextField searchField = new TextField();
        searchField.setPlaceholder("Search...");
        searchField.setPrefixComponent(VaadinIcon.SEARCH.create());
        searchField.addClassName("search-field");

//        searchField.addClassNames("p-2", "col-5");
//        searchField.setMaxLength(50);

        Button searchButton = new Button("Find");
        searchButton.addClassNames("search-button");
//        searchButton.addClassNames("bg-primary text-light col-1");
        searchSection.add(searchField, searchButton);

        return searchSection;
    }

    private Div createDownSection() {
        Div downSection = new Div();
        downSection.addClassNames("down-section");
//        downSection.addClassNames("d-flex flex-row", "w-100");

        Div menuDiv = createMenuDiv(null);
        Div filtersDiv = createFilters(menuDiv);

        downSection.add(filtersDiv, menuDiv);
        return downSection;
    }

    private Div createFilters(Div menuDiv) {
        Div filtersDiv = new Div();
        filtersDiv.addClassNames("filters");

//        filtersDiv.addClassNames("w-25 p-2 mx-1");
        String[] cuisineTypes = {"Ukrainian", "American", "Czech", "Polish", "German", "Greek", "Italian", "Spanish", "French", "Asian"};

        Div category = categoryFilter(cuisineTypes);
        Div rating = ratingFilter(menuDiv);
        filtersDiv.add(category, rating);

        return filtersDiv;
    }

    @NotNull
    private Div ratingFilter(Div menuDiv){
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

        CheckboxGroup<Integer> checkboxGroup = new CheckboxGroup<>();
        checkboxGroup.setLabel("Rating");
        checkboxGroup.setItems(5, 4, 3, 2, 1);
        checkboxGroup.setItemLabelGenerator(checkboxValues::get); // Use string representations for display
        checkboxGroup.addThemeVariants(CheckboxGroupVariant.LUMO_VERTICAL);

        checkboxListener(menuDiv, checkboxGroup);

        buttonsContainer.add(checkboxGroup);
        ratingDiv.add(buttonsContainer);


        Set<Integer> selectedItems = checkboxGroup.getValue();
        System.out.println(selectedItems);

        return ratingDiv;
    }

    private void checkboxListener(Div menuDiv, CheckboxGroup<Integer> checkboxGroup) {
        checkboxGroup.addValueChangeListener(event -> {
            Set<Integer> selectedItems = event.getValue();
            System.out.println(selectedItems);
            if (selectedItems != null && !selectedItems.isEmpty()) {
                menuDiv.removeAll();
                menuDiv.add(createMenuDiv(selectedItems));
            }else{
                menuDiv.removeAll();
                menuDiv.add(createMenuDiv(null));
            }
        });
    }

    @NotNull
    private static Div categoryFilter(String[] cuisineTypes) {
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

    private Div createMenuDiv(Set<Integer> selectedRatings) {
        Div menuDiv = new Div();
        menuDiv.addClassNames("w-75 flex-wrap basic main-div gap");

        List<RestaurantModel> filteredRestaurantModelList = new ArrayList<>();

        filteredRestaurantModelList = getFilteredRestaurantModelList(selectedRatings, filteredRestaurantModelList);

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

            Text ratingValue = new Text(" " + restaurant.getRating());

            rate.add(starIcon, ratingValue);
            info.add(name, rate);
            div.add(imageContainer, info);
            menuDiv.add(div);
        }

        return menuDiv;
    }

    private List<RestaurantModel> getFilteredRestaurantModelList(Set<Integer> selectedRatings, List<RestaurantModel> filteredRestaurantModelList) {
        if (selectedRatings == null) {
            filteredRestaurantModelList = restaurantService.findByRating(4.5);
        }else{
            List<Integer> selected = setToArray(selectedRatings);
            for(Integer rate: selected){
                filteredRestaurantModelList.addAll(restaurantService.findByRating(rate));
            }
        }
        return filteredRestaurantModelList;
    }

    private List<Integer> setToArray(Set<Integer> set){
        return new ArrayList<>(set);
    }
}
