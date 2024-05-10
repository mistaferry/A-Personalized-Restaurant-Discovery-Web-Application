package ua.huryn.elasticsearch.view.components;

import com.vaadin.flow.component.Text;
import com.vaadin.flow.component.html.Anchor;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.html.Image;
import com.vaadin.flow.component.html.Span;
import com.vaadin.flow.dom.Style;
import com.vaadin.flow.router.RouteConfiguration;
import ua.huryn.elasticsearch.entity.dto.RestaurantDTO;
import ua.huryn.elasticsearch.view.RestaurantView;

import java.util.HashMap;
import java.util.Map;

public class RestaurantItem {

    public static Div create(RestaurantDTO restaurant) {
        Div div = new Div();
        div.addClassNames("item-div");

        Div imageContainer = new Div();
        imageContainer.addClassNames("d-flex justify-content-center");

//        String scr = restaurant.getPhotoRef();
//        if(scr == null){
//            scr = "src/main/resources/db_data/restaurant_images/stock.jpg";
//        }
//        final String path = scr;
//        StreamResource imageResource = new StreamResource(restaurant.getName().toLowerCase() + "_image.jpg",
//                () -> getClass().getResourceAsStream(path));

        Image image = new Image();
        image.setSrc("https://pianavyshnia.com/wp-content/uploads/2022/10/logo.png");
        image.addClassNames("image");
        imageContainer.add(image);

        Div info = new Div();
        info.addClassNames("d-flex flex-row justify-content-around");

        Div infoDiv = new Div();

        infoDiv.addClassNames("info flex-column width-60");
        int end = Math.min(24, restaurant.getName().length());
        String route = RouteConfiguration.forSessionScope()
                .getUrl(RestaurantView.class, restaurant.getRestaurantId());
        Anchor restaurantName = new Anchor(route, restaurant.getName().substring(0, end));
        restaurantName.getStyle().setTextDecoration("none").setColor("000000FF");
        Div resNameDiv = new Div();
        resNameDiv.add(restaurantName);

        String cuisine = restaurant.getCuisineType();
        Span cuisineType = null;
        if (!cuisine.isBlank()) {
            cuisine = cuisine.substring(0, 1).toUpperCase() + cuisine.substring(1);
            cuisineType = new Span(cuisine + " cuisine");
        } else {
            cuisineType = new Span("");
        }
        cuisineType.getStyle().setColor("#0074D9").setFontSize("smaller").setFontWeight(Style.FontWeight.BOLD);
        Div cuisineDiv = new Div(cuisineType);
        Map<Integer, String> checkboxValues = new HashMap<>();
        checkboxValues.put(4, "₴₴₴₴₴");
        checkboxValues.put(3, "₴₴₴ - ₴₴₴₴");
        checkboxValues.put(2, "₴₴ - ₴₴₴");
        checkboxValues.put(1, "₴ - ₴₴");
        checkboxValues.put(0, "₴");
        Div priceDiv = new Div(new Span(checkboxValues.get(restaurant.getPriceLevel())));
        infoDiv.add(resNameDiv, cuisineDiv, priceDiv);


        Div rate = new Div();
        rate.addClassNames("info width-30");

        String formattedRating = String.format("⭐ " + Math.round(restaurant.getRating()));
        Text ratingValue = new Text(formattedRating);

        rate.add(ratingValue);
        info.add(infoDiv, rate);
        div.add(imageContainer, info);
        return div;
    }
}