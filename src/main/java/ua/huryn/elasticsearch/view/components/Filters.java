package ua.huryn.elasticsearch.view.components;

import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.checkbox.CheckboxGroup;
import com.vaadin.flow.component.combobox.ComboBox;
import com.vaadin.flow.component.checkbox.CheckboxGroupVariant;
import com.vaadin.flow.component.dialog.Dialog;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.data.value.ValueChangeMode;
import lombok.Getter;
import lombok.Setter;
import ua.huryn.elasticsearch.service.DishService;
import ua.huryn.elasticsearch.service.IngredientsService;
import ua.huryn.elasticsearch.service.RestaurantService;
import com.vaadin.flow.component.textfield.TextField;

import java.awt.*;
import java.util.*;
import java.util.List;

@Getter
@Setter
public class Filters {
    private final RestaurantService restaurantService;
    private final DishService dishService;
    private final IngredientsService ingredientsService;
    private final CheckboxGroup<String> cuisineCheckbox;
    private final CheckboxGroup<Integer> ratingCheckbox;
    private final CheckboxGroup<Integer> priceLevelCheckbox;
    private final CheckboxGroup<Integer> routeCheckbox;
    private CheckboxGroup<String> dishesCheckbox;
    private final CheckboxGroup<String> ingredientsCheckbox;
    private final ComboBox<String> dishComboBox;
    Set<String> selectedDishes = new HashSet<>();
    Set<String> selectedIngredients = new HashSet<>();
    private boolean checkBoxInteraction = false;

    private final ComboBox<String> ingredientsComboBox;
    TextField routesDeparturePoint = new TextField();
    private Button dishesSaveButton;
    private Runnable onSave;


    public Filters(RestaurantService restaurantService, DishService dishService, IngredientsService ingredientsService) {
        this.restaurantService = restaurantService;
        this.dishService = dishService;
        this.ingredientsService = ingredientsService;
        this.cuisineCheckbox = new CheckboxGroup<>();
        this.ratingCheckbox = new CheckboxGroup<>();
        this.priceLevelCheckbox = new CheckboxGroup<>();
        this.routeCheckbox = new CheckboxGroup<>();
        this.dishesCheckbox = new CheckboxGroup<>();
        this.ingredientsCheckbox = new CheckboxGroup<>();
        this.dishComboBox =  new ComboBox<>();
        this.ingredientsComboBox =  new ComboBox<>();


        setupDishElementsListeners();
        setupIngredientElementsListeners();
    }


    public Div createFiltersDiv() {
        Div filtersDiv = new Div();
        filtersDiv.addClassNames("filters");
        filtersDiv.add(createCuisineFilter(), createRatingFilter(), createRouteFilter(), createPriceFilter(), createDishesFilter(), createIngredientsFilter());

        return filtersDiv;
    }

    private Div createCuisineFilter() {
        List<String> cuisineTypes = restaurantService.getCuisineType();
        List<String> cuisineTypesLabels = cuisineTypes.stream()
                .map(cuisine -> cuisine + " кухня")
                .toList();

        Div cuisineTypeDiv = new Div();
        cuisineTypeDiv.addClassNames("main-div");

        Div cuisineTypesContainer = new Div();
        cuisineTypesContainer.addClassNames("d-flex flex-wrap justify-content-between");

        cuisineCheckbox.setLabel("Кухня");
        cuisineCheckbox.setItems(cuisineTypesLabels);
        cuisineCheckbox.addThemeVariants(CheckboxGroupVariant.LUMO_VERTICAL);
        cuisineTypesContainer.add(cuisineCheckbox);
        cuisineTypeDiv.add(cuisineTypesContainer);

        return cuisineTypeDiv;
    }

    private Div createRatingFilter() {
        Div ratingDiv = new Div();
        ratingDiv.addClassNames("main-div");

        Div buttonsContainer = new Div();
        buttonsContainer.addClassNames("d-flex flex-wrap justify-content-between");

        Map<Integer, String> checkboxValues = new HashMap<>();
        checkboxValues.put(5, "★★★★★");
        checkboxValues.put(4, "★★★★☆");
        checkboxValues.put(3, "★★★☆☆");
        checkboxValues.put(2, "★★☆☆☆");
        checkboxValues.put(1, "★☆☆☆☆");

        ratingCheckbox.setLabel("Рейтинг");
        ratingCheckbox.setItems(5, 4, 3, 2, 1);
        ratingCheckbox.setItemLabelGenerator(checkboxValues::get);
        ratingCheckbox.addThemeVariants(CheckboxGroupVariant.LUMO_VERTICAL);

        buttonsContainer.add(ratingCheckbox);
        ratingDiv.add(buttonsContainer);

        return ratingDiv;
    }

    private Div createPriceFilter() {
        Div priceDiv = new Div();
        priceDiv.addClassNames("main-div");

        Div buttonsContainer = new Div();
        buttonsContainer.addClassNames("d-flex flex-wrap justify-content-between");

        Map<Integer, String> checkboxValues = new HashMap<>();
        checkboxValues.put(4, "₴₴₴₴₴");
        checkboxValues.put(3, "₴₴₴ - ₴₴₴₴");
        checkboxValues.put(2, "₴₴ - ₴₴₴");
        checkboxValues.put(1, "₴ - ₴₴");
        checkboxValues.put(0, "₴");

        priceLevelCheckbox.setLabel("Рівень цін");
        priceLevelCheckbox.setItems(4, 3, 2, 1, 0);
        priceLevelCheckbox.setItemLabelGenerator(checkboxValues::get);
        priceLevelCheckbox.addThemeVariants(CheckboxGroupVariant.LUMO_VERTICAL);

        buttonsContainer.add(priceLevelCheckbox);
        priceDiv.add(buttonsContainer);

        return priceDiv;
    }

    private Div createRouteFilter() {
        Div routeDiv = new Div();
        routeDiv.addClassNames("main-div");

        Div checkboxContainer = new Div();
        checkboxContainer.addClassNames("d-flex flex-wrap justify-content-between pd-2");

        routesDeparturePoint.setLabel("Відстань");
        String regexPattern = "^[a-zA-Zа-яА-ЯїЇіІєЄ0-9 .]+, [0-9]{1,3}$";
        routesDeparturePoint.setPattern(regexPattern);
        routesDeparturePoint.setErrorMessage("Invalid format. Should be 'address, number' or 'coordinates, number'.");

        routesDeparturePoint.setValueChangeMode(ValueChangeMode.EAGER);

        routesDeparturePoint.addValueChangeListener(event -> {
            if (routesDeparturePoint.isInvalid()) {
                System.out.println("Invalid input");
            }
        });

        Map<Integer, String> checkboxValues = new HashMap<>();
        checkboxValues.put(2, "Пішки");
        checkboxValues.put(1, "Машина");
        checkboxValues.put(0, "Географічна віддаленість");

        routeCheckbox.setItems(2, 1, 0);
        routeCheckbox.setItemLabelGenerator(checkboxValues::get);
        routeCheckbox.addThemeVariants(CheckboxGroupVariant.LUMO_VERTICAL);


        checkboxContainer.add(routesDeparturePoint, routeCheckbox);
        routeDiv.add(checkboxContainer);

        return routeDiv;
    }

    private Div createDishesFilter() {
        Div dishDiv = new Div();
        dishDiv.addClassNames("main-div");

        Div dishesContainer = new Div();
        dishesContainer.addClassNames("d-flex flex-wrap justify-content-between");

        Map<Long, String> allDishes = dishService.getAllDishesNames();
        dishesCheckbox.setLabel("Страви");
        dishesCheckbox.addThemeVariants(CheckboxGroupVariant.LUMO_VERTICAL);
        dishesCheckbox.setItems(allDishes.values());
        dishComboBox.setItems(allDishes.values());


        Dialog dialog = addDishesDialogElement();
        Button button = new Button("Show all", e -> dialog.open());
        dishDiv.add(dialog, button);

        return dishDiv;
    }

    public Dialog addDishesDialogElement(){
        Dialog dialog = new Dialog();
        dialog.setHeaderTitle("Оберіть страви");

        VerticalLayout dialogLayout = new VerticalLayout();
        dialogLayout.add(dishComboBox, dishesCheckbox);
        dialog.add(dialogLayout);

        Button closeButton = new Button("Close", e -> dialog.close());
        dialog.getFooter().add(closeButton);

        return dialog;
    }

    private void setupDishElementsListeners() {
        dishComboBox.addValueChangeListener(event -> {
            String selectedDish = event.getValue();
            if (selectedDish != null) {
                selectedDishes.add(selectedDish);
                dishesCheckbox.setValue(selectedDishes);
            }
        });

        dishesCheckbox.addValueChangeListener(event -> {
            selectedDishes.clear();
            selectedDishes.addAll(event.getValue());
//            List<String> sortedItems = new ArrayList<>(dishesCheckbox.getListDataView().getItems().toList());
//            sortedItems.sort(Comparator.comparingInt(item -> selectedDishes.contains(item) ? 0 : 1));
//            dishesCheckbox = new CheckboxGroup<>();
//            dishesCheckbox.setItems(sortedItems);
            dishesCheckbox.setValue(selectedDishes);
        });
    }

    private Div createIngredientsFilter() {
        Div ingredientsDiv = new Div();
        ingredientsDiv.addClassNames("main-div");

        Div ingredientsContainer = new Div();
        ingredientsContainer.addClassNames("d-flex flex-wrap justify-content-between");

        Map<Long, String> checkboxValues = ingredientsService.getAllIngredients();
        ingredientsCheckbox.setLabel("Інгредієнти");
        ingredientsCheckbox.addClassNames("custom-checkbox");
        ingredientsCheckbox.setItems(checkboxValues.values());
        ingredientsCheckbox.addThemeVariants(CheckboxGroupVariant.LUMO_VERTICAL);

        Dialog dialog = addIngredientsDialogElement();
        Button button = new Button("Show all", e -> dialog.open());
        ingredientsDiv.add(dialog, button);

        return ingredientsDiv;
    }

    public Dialog addIngredientsDialogElement(){
        Dialog dialog = new Dialog();
        dialog.setHeaderTitle("Оберіть інгредієнти для виключеня");

        VerticalLayout dialogLayout = new VerticalLayout();
        ingredientsComboBox.setItems(ingredientsService.getAllIngredients().values());

        dialogLayout.add(ingredientsComboBox, ingredientsCheckbox);
        dialog.add(dialogLayout);

        Button closeButton = new Button("Закрити", e -> dialog.close());
        dialog.getFooter().add(closeButton);

        return dialog;
    }

    private void setupIngredientElementsListeners() {
        ingredientsComboBox.addValueChangeListener(event -> {
            String selectedIngredient = event.getValue();
            if (selectedIngredient != null) {
                selectedIngredients.add(selectedIngredient);
                ingredientsCheckbox.setValue(selectedIngredients);
            }
        });

        ingredientsCheckbox.addValueChangeListener(event -> {
            selectedIngredients.clear();
            selectedIngredients.addAll(event.getValue());
            ingredientsCheckbox.setValue(selectedIngredients);
        });
    }
}