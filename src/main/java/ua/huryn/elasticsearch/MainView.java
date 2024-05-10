package ua.huryn.elasticsearch;

import com.vaadin.flow.component.applayout.AppLayout;
import com.vaadin.flow.component.applayout.DrawerToggle;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;

public class MainView extends AppLayout {
    public MainView() {
        createHeader();
    }

    private void createHeader() {
        H1 logo = new H1("Diplom Huryn");
        var header = new HorizontalLayout(new DrawerToggle(), logo);
        addToNavbar(header);

    }
}
