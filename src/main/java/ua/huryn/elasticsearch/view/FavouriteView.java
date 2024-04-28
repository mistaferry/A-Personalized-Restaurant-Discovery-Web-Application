package ua.huryn.elasticsearch.view;

import com.vaadin.flow.component.Text;
import com.vaadin.flow.component.dependency.CssImport;
import com.vaadin.flow.component.dependency.StyleSheet;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import org.springframework.beans.factory.annotation.Autowired;
import ua.huryn.elasticsearch.MainView;
import ua.huryn.elasticsearch.entity.db.Restaurant;
import ua.huryn.elasticsearch.entity.model.RestaurantModel;
import ua.huryn.elasticsearch.repository.db.RestaurantDbRepository;
import ua.huryn.elasticsearch.service.RestaurantService;

import java.util.ArrayList;
import java.util.List;

@PageTitle("Favourite")
@Route(value = "favourite", layout = MainView.class)
@CssImport("styles.css")
@StyleSheet("https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css")
public class FavouriteView extends VerticalLayout {
    private final RestaurantService restaurantService;
    private final RestaurantDbRepository restaurantDbRepository;

    @Autowired
    public FavouriteView(RestaurantService restaurantService, RestaurantDbRepository restaurantDbRepository) {
        this.restaurantService = restaurantService;
        this.restaurantDbRepository = restaurantDbRepository;
        add(new Text("Favourite"));
//        List<Restaurant> list = restaurantDbRepository.findAll();
//        for (Restaurant restaurant: list){
//            add(new Text(restaurant.getCategories().get(0).getName()));
//        }
//        List<RestaurantModel> restaurantModels = restaurantService.search("Pu");
//        System.out.println(restaurantModels.size());
    }
}
