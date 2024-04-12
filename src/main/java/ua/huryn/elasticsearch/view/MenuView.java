package ua.huryn.elasticsearch.view;

import com.vaadin.flow.component.Text;
import com.vaadin.flow.component.dependency.CssImport;
import com.vaadin.flow.component.dependency.StyleSheet;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.html.Image;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import ua.huryn.elasticsearch.MainView;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.component.button.Button;

import java.util.ArrayList;

@PageTitle("Menu")
@Route(value = "", layout = MainView.class)
@CssImport("styles.css")
@StyleSheet("https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css")
public class MenuView extends VerticalLayout {

    public MenuView() {
        add(createSearchSection());
        add(createDownSection());
    }

    private Div createSearchSection() {
        Div section1 = new Div();
        section1.addClassNames("w-100 p-2 mx-1 d-flex justify-content-center align-items-center");

        TextField searchField = new TextField();
        searchField.setPlaceholder("Search...");
        searchField.setPrefixComponent(VaadinIcon.SEARCH.create());
        searchField.addClassNames("p-2", "col-5");
        searchField.setMaxLength(50);
        section1.add(searchField);

        Button searchButton = new Button("Find");
        searchButton.addClassNames("bg-primary text-light col-1");
        section1.add(searchButton);

        return section1;
    }

    private Div createDownSection() {
        Div section2 = new Div();
        section2.addClassNames("d-flex flex-row", "w-100");

        Div filtersDiv = createFilters();
        Div menuDiv = createMenuDiv();

        section2.add(filtersDiv, menuDiv);
        return section2;
    }

    private Div createFilters() {
        Div filtersDiv = new Div();
        filtersDiv.addClassNames("w-25 p-2 mx-1");

        Div category = new Div();
        category.addClassNames("checkbox-btn-group");

        Div buttonsContainer = new Div();
        buttonsContainer.addClassNames("d-flex flex-wrap justify-content-between");
        ArrayList<Button> buttonList = new ArrayList<>();
        Button button1 = new Button("Ukrainian");
        Button button2 = new Button("French");
        Button button3 = new Button("Asian");
        Button button4 = new Button("Italian");
        Button button5 = new Button("Greek");
        Button button6 = new Button("German");
        buttonList.add(button1);
        buttonList.add(button2);
        buttonList.add(button3);
        buttonList.add(button4);
        buttonList.add(button5);
        buttonList.add(button6);

        for (Button button : buttonList) {
            button.addClassName("checkbox-btn");
            button.addClickListener(e -> {
                if (button.hasClassName("active")) {
                    button.removeClassName("active");
                } else {
                    button.addClassName("active");
                }
            });
            buttonsContainer.add(button);
        }
        category.add(buttonsContainer);
        filtersDiv.add(category);

        return filtersDiv;
    }

    private Div createMenuDiv() {
        Div menuDiv = new Div();
        menuDiv.addClassNames("w-75 p-2 mx-1 d-flex flex-wrap justify-content-around checkbox-btn-group");
        menuDiv.getStyle().set("gap", "20px"); // Встановлення прогалини в 4px між Div

        ArrayList<Div> restaurants = new ArrayList<>();
        Div restaurant1 = new Div();
        Div restaurant2 = new Div();
        Div restaurant3 = new Div();
        Div restaurant4 = new Div();
        restaurants.add(restaurant1);
        restaurants.add(restaurant2);
        restaurants.add(restaurant3);
        restaurants.add(restaurant4);

        for (Div div : restaurants) {
            div.addClassNames("w-25 bg-info");
            menuDiv.add(div);
        }




        return menuDiv;
    }
}
