package ua.huryn.elasticsearch.view;

import com.google.maps.errors.ApiException;
import com.vaadin.flow.component.Text;
import com.vaadin.flow.component.dependency.CssImport;
import com.vaadin.flow.component.dependency.JsModule;
import com.vaadin.flow.component.orderedlayout.FlexComponent;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.theme.lumo.LumoUtility;
import ua.huryn.elasticsearch.MainView;
import ua.huryn.elasticsearch.service.RestaurantService;

@PageTitle("Menu")
@Route(value="", layout = MainView.class)
@CssImport("styles.css")
public class MenuView extends VerticalLayout {

    public MenuView() {
        // Створення вертикальної секції №1
        HorizontalLayout section1 = new HorizontalLayout();
        section1.setAlignItems(FlexComponent.Alignment.CENTER);
        VerticalLayout horizontalSection1 = new VerticalLayout();
        horizontalSection1.add(new Text("Horizontal Section 1"));
        horizontalSection1.addClassNames("left-hor-sec");
        VerticalLayout horizontalSection2 = new VerticalLayout();
        horizontalSection2.add(new Text("Horizontal Section 2"));

        section1.add(horizontalSection1, horizontalSection2);
        add(section1);
    }
}
