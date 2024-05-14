package ua.huryn.elasticsearch.view.components;

import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.checkbox.CheckboxGroup;
import com.vaadin.flow.component.combobox.ComboBox;
import com.vaadin.flow.component.checkbox.CheckboxGroupVariant;
import com.vaadin.flow.component.dialog.Dialog;
import com.vaadin.flow.component.html.Anchor;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.data.value.ValueChangeMode;
import lombok.Getter;
import lombok.Setter;
import ua.huryn.elasticsearch.service.DishService;
import ua.huryn.elasticsearch.service.IngredientsService;
import ua.huryn.elasticsearch.service.RestaurantService;
import com.vaadin.flow.component.textfield.TextField;

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
    private final CheckboxGroup<String> dishesCheckbox;
    private final CheckboxGroup<String> ingredientsCheckbox;
    private final CheckboxGroup<String> partOfIngredientCheckbox;
    private final CheckboxGroup<String> partOfDishCheckbox;
    private final ComboBox<String> dishComboBox;
    Set<String> selectedDishes = new HashSet<>();
    Set<String> selectedIngredients = new HashSet<>();
    private boolean checkBoxInteraction = false;

    private final ComboBox<String> ingredientsComboBox;
    TextField routesDeparturePoint = new TextField();
    private Button dishesSaveButton;
    private Runnable onSave;
    private Button reviewSearchButton;
    private TextField reviewKeywordInput;


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
        this.partOfIngredientCheckbox = new CheckboxGroup<>();
        this.partOfDishCheckbox = new CheckboxGroup<>();
        this.reviewSearchButton = new Button("Пошук");
        this.reviewKeywordInput = new TextField();

        setupDishElementsListeners();
        setupIngredientElementsListeners();
    }


    public Div createFiltersDiv() {
        Div filtersDiv = new Div();
        filtersDiv.addClassNames("filters");
        filtersDiv.add(addMapComponent(), createCuisineFilter(), createRatingFilter(), createRouteFilter(), createPriceFilter(), createDishesFilter(), createIngredientsFilter(), createReviewFilter());

        return filtersDiv;
    }

    private Div addMapComponent() {
        Div cuisineTypeDiv = new Div();

        Button button = new Button("Переглянути карту");
        button.setWidth("100%");
        Anchor anchor = new Anchor("http://maps.googleapis.com/maps/api/js?v=3.exp&callback=initialize", "Текст, який відображатиметься на посиланні");
        anchor.setTarget("_blank");
        button.addClickListener(event -> {
            anchor.getElement().callJsFunction("click");
        });

        return cuisineTypeDiv;
    }

    private Div createCuisineFilter() {
        List<String> cuisineTypes = restaurantService.getCuisineType();

        Div cuisineTypeDiv = new Div();
        cuisineTypeDiv.addClassNames("main-div");

        Div cuisineTypesContainer = new Div();
        cuisineTypesContainer.addClassNames("d-flex flex-wrap justify-content-between");

        cuisineCheckbox.setLabel("Кухня");
        cuisineCheckbox.setItems(cuisineTypes);
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
        dishesContainer.addClassNames("d-flex");

        Map<Long, String> allDishes = dishService.getAllDishesNames();
        configureDishesCheckbox(allDishes);

        List<String> partOfValues = Arrays.asList(Arrays.copyOfRange(allDishes.values().toArray(new String[0]), 0, 4));
        partOfDishCheckbox.setLabel("Страви");
        partOfDishCheckbox.setItems(partOfValues);
        partOfDishCheckbox.addClassName("flex-column");
        dishesContainer.add(partOfDishCheckbox);
        dishDiv.add(dishesContainer);

        Dialog dialog = addDishesDialogElement();
        Button button = new Button("Показати всі", e -> dialog.open());
        button.addThemeVariants(ButtonVariant.LUMO_TERTIARY_INLINE);

        dishDiv.add(dialog, button);
        return dishDiv;
    }

    private void configureDishesCheckbox(Map<Long, String> allDishes) {
        dishesCheckbox.setLabel("Страви");
        dishesCheckbox.addThemeVariants(CheckboxGroupVariant.LUMO_VERTICAL);
        dishesCheckbox.setItems(allDishes.values());
        dishComboBox.setItems(allDishes.values());
    }

    public Dialog addDishesDialogElement(){
        Dialog dialog = new Dialog();
        dialog.setHeaderTitle("Оберіть страви");

        VerticalLayout dialogLayout = new VerticalLayout();
        dialogLayout.add(dishComboBox, dishesCheckbox);
        dialog.add(dialogLayout);

        Button closeButton = new Button("Закрити", e -> dialog.close());
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

        partOfDishCheckbox.addValueChangeListener(event -> {
            Set<String> selected = partOfDishCheckbox.getSelectedItems();
            dishesCheckbox.setValue(selected);
        });

        dishesCheckbox.addValueChangeListener(event -> {
            selectedDishes.clear();
            selectedDishes.addAll(event.getValue());
            dishesCheckbox.setValue(selectedDishes);
        });
    }

    private Div createIngredientsFilter() {
        Div ingredientsDiv = new Div();
        ingredientsDiv.getStyle().setPaddingTop("20px");

        Div ingredientsContainer = new Div();
        ingredientsContainer.addClassNames("d-flex flex-wrap justify-content-between");

        Map<Long, String> checkboxValues = ingredientsService.getAllIngredients();
        configureIngredientsCheckbox(checkboxValues);

        List<String> partOfValues = Arrays.asList(Arrays.copyOfRange(checkboxValues.values().toArray(new String[0]), 1, 4));
        partOfIngredientCheckbox.setLabel("Інгредієнти");
        partOfIngredientCheckbox.setItems(partOfValues);
        partOfIngredientCheckbox.addClassNames("custom-checkbox");
        ingredientsContainer.add(partOfIngredientCheckbox);
        ingredientsDiv.add(ingredientsContainer);

        Dialog dialog = addIngredientsDialogElement();
        Button button = new Button("Показати всі", e -> dialog.open());
        button.addThemeVariants(ButtonVariant.LUMO_TERTIARY_INLINE);
        ingredientsDiv.add(dialog, button);

        return ingredientsDiv;
    }

    private void configureIngredientsCheckbox(Map<Long, String> checkboxValues) {
        ingredientsCheckbox.setLabel("Інгредієнти");
        ingredientsCheckbox.addClassNames("custom-checkbox");
        ingredientsCheckbox.setItems(checkboxValues.values());
        ingredientsCheckbox.addThemeVariants(CheckboxGroupVariant.LUMO_VERTICAL);
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

        partOfIngredientCheckbox.addValueChangeListener(event -> {
            Set<String> selected = partOfIngredientCheckbox.getSelectedItems();
            ingredientsCheckbox.setValue(selected);
        });

        ingredientsCheckbox.addValueChangeListener(event -> {
            selectedIngredients.clear();
            selectedIngredients.addAll(event.getValue());
            ingredientsCheckbox.setValue(selectedIngredients);
        });
    }

    private Div createReviewFilter(){
        Div priceDiv = new Div();
        priceDiv.addClassNames("main-div");
        Div inputContainer = new Div();
        inputContainer.addClassNames("d-flex flex-wrap justify-content-between");
//        inputContainer.getStyle().setMaxWidth("100%");

        reviewKeywordInput.setLabel("Відгуки");
        reviewKeywordInput.setClassName("review-text");
        reviewKeywordInput.setPlaceholder("Ключові слова");

        inputContainer.add(reviewKeywordInput, reviewSearchButton);
        priceDiv.add(inputContainer);

        return priceDiv;
    }
}