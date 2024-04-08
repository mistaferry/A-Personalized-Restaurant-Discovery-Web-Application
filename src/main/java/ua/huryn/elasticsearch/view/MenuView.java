package ua.huryn.elasticsearch.view;

import com.vaadin.flow.component.Text;
import com.vaadin.flow.component.Unit;
import com.vaadin.flow.component.dependency.CssImport;
import com.vaadin.flow.component.dependency.StyleSheet;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import ua.huryn.elasticsearch.MainView;
import com.vaadin.flow.component.textfield.TextField;


import java.awt.*;

@PageTitle("Menu")
@Route(value="", layout = MainView.class)
@CssImport("styles.css")
@StyleSheet("https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css")
public class MenuView extends VerticalLayout {

    public MenuView() {
        Div section1 = new Div();
        section1.addClassNames("w-100 bg-warning p-2 mx-1 d-flex justify-content-center");

        Div searchDiv = new Div();
        TextField searchField = new TextField();
        searchField.setPlaceholder("Search...");
        searchField.setPrefixComponent(VaadinIcon.SEARCH.create());
        searchField.addClassNames("bg-light p-2");
        searchField.setMaxLength(50);
        searchDiv.add(searchField);
        section1.add(searchDiv);

        Div section2 = new Div();
        section2.addClassNames("d-flex flex-row", "w-100");

        Div filtersDiv = new Div();
        filtersDiv.add(new Text("hdjdl"));
        filtersDiv.addClassNames("w-25 bg-success p-2 mx-1");

        Div menuDiv = new Div();
        menuDiv.add(new Text("uy76tre"));
        menuDiv.addClassNames("w-75 bg-danger p-2");

        section2.add(filtersDiv, menuDiv);
        add(section1,section2);
    }
}
