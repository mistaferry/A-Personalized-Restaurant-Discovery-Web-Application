package ua.huryn.elasticsearch;

import com.vaadin.flow.component.applayout.AppLayout;
import com.vaadin.flow.component.applayout.DrawerToggle;
import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.sidenav.SideNavItem;
import ua.huryn.elasticsearch.view.LogoutView;
import ua.huryn.elasticsearch.view.MenuView;

public class MainView extends AppLayout {

    public MainView() {
        createHeader();
    }

    private void createHeader() {
        H1 logo = new H1("Foodie Finds");
        var header = new HorizontalLayout(new DrawerToggle(), logo);
        addToNavbar(header);
        SideNavItem dashboardLink = new SideNavItem("Меню", MenuView.class, VaadinIcon.COFFEE.create());
        SideNavItem inboxLink = new SideNavItem("Вихід", LogoutView.class, VaadinIcon.USER.create());
        addToDrawer(dashboardLink, inboxLink);
    }
}
