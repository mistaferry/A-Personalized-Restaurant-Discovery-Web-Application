package ua.huryn.elasticsearch.view;

import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.router.*;
import ua.huryn.elasticsearch.MainView;
import ua.huryn.elasticsearch.service.RestaurantService;

@Route(value = "restaurant", layout = MainView.class)
public class RestaurantView extends Div implements HasUrlParameter<Long> {
    private Long restaurantId;
    private final RestaurantService restaurantService;

    public RestaurantView(RestaurantService restaurantService) {
        this.restaurantService = restaurantService;
    }

    @Override
    public void setParameter(BeforeEvent event, Long parameter) {
        restaurantId = parameter;
    }


}