package ua.huryn.elasticsearch;

import com.vaadin.flow.component.applayout.AppLayout;
import com.vaadin.flow.component.applayout.DrawerToggle;
import com.vaadin.flow.component.dependency.CssImport;
import com.vaadin.flow.component.dependency.JsModule;
import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.orderedlayout.FlexComponent;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.RouterLink;
import com.vaadin.flow.theme.lumo.LumoUtility;
import ua.huryn.elasticsearch.view.FavouriteView;
import ua.huryn.elasticsearch.view.MenuView;

public class MainView extends AppLayout {

//    public MainView(@Autowired RestaurantService restaurantService) throws IOException, InterruptedException, ApiException {
//
//        add(new Text("Welcome to MainView."));
//        // Create a grid to display the data
//
//    }


    public MainView() {
        createHeader();
        createDrawer();
    }

    private void createHeader() {
        H1 logo = new H1("Diplom Huryn");
        logo.addClassNames(
                LumoUtility.FontSize.LARGE,
                LumoUtility.Margin.MEDIUM,
                LumoUtility.TextColor.ERROR);

        var header = new HorizontalLayout(new DrawerToggle(), logo );

        header.setDefaultVerticalComponentAlignment(FlexComponent.Alignment.CENTER);
        header.setWidthFull();
        header.addClassNames(
                LumoUtility.Padding.Vertical.NONE,
                LumoUtility.Padding.Horizontal.MEDIUM);

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