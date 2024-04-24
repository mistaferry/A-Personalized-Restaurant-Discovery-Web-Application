package ua.huryn.elasticsearch.view;

import com.vaadin.flow.component.Text;
import com.vaadin.flow.component.dependency.CssImport;
import com.vaadin.flow.component.dependency.StyleSheet;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.sidenav.SideNav;
import com.vaadin.flow.component.sidenav.SideNavItem;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import org.springframework.beans.factory.annotation.Autowired;
import ua.huryn.elasticsearch.MainView;
import ua.huryn.elasticsearch.model.RestaurantModel;
import ua.huryn.elasticsearch.service.RestaurantService;

import java.util.List;

@PageTitle("Favourite")
@Route(value = "favourite", layout = MainView.class)
@CssImport("styles.css")
@StyleSheet("https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css")
public class FavouriteView extends VerticalLayout {
    private final RestaurantService restaurantService;

    @Autowired
    public FavouriteView(RestaurantService restaurantService) {
        this.restaurantService = restaurantService;
        List<RestaurantModel> restaurantModels = restaurantService.findByRating(4.5);
        for (RestaurantModel restaurantModel: restaurantModels){
            System.out.println(restaurantModel);
        }
        add(new Text("Favourite"));

    }
}
