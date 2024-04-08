package ua.huryn.elasticsearch;

import com.vaadin.flow.component.applayout.AppLayout;
import com.vaadin.flow.component.applayout.DrawerToggle;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.shared.InputField;
import com.vaadin.flow.router.RouterLink;
import ua.huryn.elasticsearch.view.FavouriteView;
import ua.huryn.elasticsearch.view.MenuView;
import com.vaadin.flow.component.html.Span;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.Route;

import java.awt.*;

public class MainView extends AppLayout {

//    public MainView(@Autowired RestaurantService restaurantService) throws IOException, InterruptedException, ApiException {
//
//        add(new Text("Welcome to MainView."));
//        // Create a grid to display the data
//
//    }
//
//    <div class="col-auto">
//      <label class="sr-only" for="inlineFormInputGroup">Username</label>
//      <div class="input-group mb-2">
//        <div class="input-group-prepend">
//          <div class="input-group-text">@</div>
//        </div>
//        <input type="text" class="form-control" id="inlineFormInputGroup" placeholder="Username">
//      </div>
//    </div>

    public MainView() {
        createHeader();
        createDrawer();
    }

    private void createHeader() {
        H1 logo = new H1("Diplom Huryn");
//        TextField search = new TextField();
//        search.setPlaceholder("Search");
//        search.setClearButtonVisible(true);
//        search.setPrefixComponent(VaadinIcon.SEARCH.create());
//        search.setClassName("mx-auto");

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