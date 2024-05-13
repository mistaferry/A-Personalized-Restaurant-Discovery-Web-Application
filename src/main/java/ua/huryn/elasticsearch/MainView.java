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
import ua.huryn.elasticsearch.view.LoginView;
import ua.huryn.elasticsearch.view.MenuView;

import java.awt.*;

public class MainView extends AppLayout {

    public MainView() {
        createHeader();
    }

    private void createHeader() {
        H1 logo = new H1("Diploma Huryn");
        var header = new HorizontalLayout(new DrawerToggle(), logo);
        addToNavbar(header);
        SideNavItem dashboardLink = new SideNavItem("Menu", MenuView.class, VaadinIcon.COFFEE.create());
        SideNavItem inboxLink = new SideNavItem("Login", LoginView.class, VaadinIcon.USER.create());
        addToDrawer(dashboardLink, inboxLink);
    }
}
