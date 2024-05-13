package ua.huryn.elasticsearch.view.components;

import com.vaadin.flow.component.Text;
import com.vaadin.flow.component.html.Anchor;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.html.Image;
import com.vaadin.flow.component.html.Span;
import com.vaadin.flow.dom.Style;
import com.vaadin.flow.router.RouteConfiguration;
import com.vaadin.flow.server.StreamResource;
import lombok.Getter;
import lombok.Setter;
import org.jetbrains.annotations.NotNull;
import ua.huryn.elasticsearch.config.GeneralProperties;
import ua.huryn.elasticsearch.entity.dto.RestaurantDTO;
import ua.huryn.elasticsearch.view.RestaurantInfoView;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.Map;

import static org.reflections.Reflections.log;

@Getter
@Setter
public class RestaurantItem {
    private final String localDirectory;
    private final GeneralProperties generalProperties;

    public RestaurantItem(GeneralProperties generalProperties) {
        this.generalProperties = generalProperties;
        this.localDirectory=generalProperties.getLocalDirectory();
    }


    public Div create(RestaurantDTO restaurant) {
        Div div = new Div();
        div.addClassNames("item-div");

        Div imageContainer = new Div();
        imageContainer.addClassNames("d-flex justify-content-center");

        Image image = getImage(restaurant);

        image.addClassNames("image");
        imageContainer.add(image);

        Div info = new Div();
        info.addClassNames("d-flex flex-row justify-content-around");

        Div infoDiv = new Div();

        infoDiv.addClassNames("info flex-column width-60");
        int end = Math.min(24, restaurant.getName().length());
        String route = RouteConfiguration.forSessionScope()
                .getUrl(RestaurantInfoView.class, restaurant.getRestaurantId());
        Anchor restaurantName = new Anchor(route, restaurant.getName().substring(0, end));
        restaurantName.getStyle().setTextDecoration("none").setColor("000000FF");
        Div resNameDiv = new Div();
        resNameDiv.add(restaurantName);

        String cuisine = restaurant.getCuisineType();
        Span cuisineType = null;
        if (!cuisine.isBlank()) {
            cuisine = cuisine.substring(0, 1).toUpperCase() + cuisine.substring(1);
            cuisineType = new Span(cuisine + " кухня");
        } else {
            cuisineType = new Span("");
        }

        cuisineType.getStyle().setColor("#0074D9").setFontSize("smaller").setFontWeight(Style.FontWeight.BOLD);
        Div cuisineDiv = new Div(cuisineType);
        Div priceDiv = getPriceDiv(restaurant);
        infoDiv.add(resNameDiv, cuisineDiv, priceDiv);

        Div rate = new Div();
        rate.addClassNames("info width-30");
        long rating = Math.round(restaurant.getRating());
        String formattedRating = String.format("⭐ " + rating);
        Text ratingValue = new Text(formattedRating);

        rate.add(ratingValue);
        info.add(infoDiv, rate);
        div.add(imageContainer, info);
        return div;
    }

    private static @NotNull Div getPriceDiv(RestaurantDTO restaurant) {
        Map<Integer, String> checkboxValues = new HashMap<>();
        checkboxValues.put(4, "₴₴₴₴₴");
        checkboxValues.put(3, "₴₴₴ - ₴₴₴₴");
        checkboxValues.put(2, "₴₴ - ₴₴₴");
        checkboxValues.put(1, "₴ - ₴₴");
        checkboxValues.put(0, "₴");
        return new Div(new Span(checkboxValues.get(restaurant.getPriceLevel())));
    }

    private @NotNull Image getImage(RestaurantDTO restaurant) {
        String scr = localDirectory + "/db_data/restaurant_images/" + restaurant.getPlaceId() + "_image.jpg";

        StreamResource resource = new StreamResource("image.jpg", () -> {
            try {
                return new FileInputStream(scr);
            } catch (FileNotFoundException e) {
                log.error("No image found for restaurant placeId - " + restaurant.getPlaceId());
            }
            return null;
        });
        return new Image(resource, "Image");
    }
}