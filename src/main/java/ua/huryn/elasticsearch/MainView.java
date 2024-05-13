package ua.huryn.elasticsearch;

import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.applayout.AppLayout;
import com.vaadin.flow.component.applayout.DrawerToggle;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.html.Span;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.orderedlayout.FlexComponent;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.sidenav.SideNav;
import com.vaadin.flow.component.sidenav.SideNavItem;

import java.awt.*;

public class MainView extends AppLayout {
    private static final String OAUTH_URL = "/oauth2/authorization/google";

    public MainView() {
        createHeader();
    }

    private void createHeader() {
        H1 logo = new H1("Diploma Huryn");
        var header = new HorizontalLayout(new DrawerToggle(), logo);
        addToNavbar(header);
    }

    private SideNav getSideNav() {
        SideNav sideNav = new SideNav();
        sideNav.addItem(
                new SideNavItem("Menu", "/",
                        VaadinIcon.SEARCH.create()),
                new SideNavItem("Log in", OAUTH_URL, VaadinIcon.USER.create()));
        return sideNav;
    }
}
