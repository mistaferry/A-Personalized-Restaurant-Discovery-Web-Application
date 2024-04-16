package ua.huryn.elasticsearch;

import com.vaadin.flow.component.applayout.AppLayout;
import com.vaadin.flow.component.applayout.DrawerToggle;
import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.RouterLink;
import ua.huryn.elasticsearch.view.FavouriteView;
import ua.huryn.elasticsearch.view.MenuView;

public class MainView extends AppLayout {
    public MainView() {
        createHeader();
        createDrawer();
    }

    private void createHeader() {
        H1 logo = new H1("Diplom Huryn");
        var header = new HorizontalLayout(new DrawerToggle(), logo);
        addToNavbar(header);

    }

//    навігація з правої частини сторінки
    private void createDrawer() {
        addToDrawer(new VerticalLayout(
//                перелік ресторанів
                new RouterLink("Home", MenuView.class),
//                обрані ресторани
                new RouterLink("Favourite", FavouriteView.class)));
    }

}