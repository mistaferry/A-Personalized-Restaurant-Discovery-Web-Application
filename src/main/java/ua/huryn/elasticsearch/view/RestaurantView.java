package ua.huryn.elasticsearch.view;

import com.vaadin.flow.component.Text;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.html.Anchor;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.html.Image;
import com.vaadin.flow.component.html.Span;
import com.vaadin.flow.component.messages.MessageInput;
import com.vaadin.flow.component.messages.MessageList;
import com.vaadin.flow.component.messages.MessageListItem;
import com.vaadin.flow.component.textfield.TextArea;
import com.vaadin.flow.router.*;
import jakarta.annotation.security.PermitAll;
import ua.huryn.elasticsearch.MainView;
import ua.huryn.elasticsearch.entity.db.Restaurant;
import ua.huryn.elasticsearch.entity.db.Review;
import ua.huryn.elasticsearch.entity.dto.RestaurantDTO;
import ua.huryn.elasticsearch.entity.dto.ReviewDTO;
import ua.huryn.elasticsearch.service.RestaurantService;
import ua.huryn.elasticsearch.service.ReviewService;
import ua.huryn.elasticsearch.utils.Convertor;

import java.awt.*;
import java.sql.Timestamp;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

@Route(value = "restaurant", layout = MainView.class)
public class RestaurantView extends Div implements HasUrlParameter<Long> {
    private Long restaurantId;
    private RestaurantDTO restaurant;
    private final RestaurantService restaurantService;
    private final ReviewService reviewService;

    public RestaurantView(RestaurantService restaurantService, ReviewService reviewService) {
        this.restaurantService = restaurantService;
        this.reviewService = reviewService;
    }

    @Override
    public void setParameter(BeforeEvent event, Long parameter) {
        this.restaurantId = parameter;
        this.restaurant = restaurantService.findByRestaurantId(this.restaurantId);

        add(pageDiv());
    }

    public Div pageDiv() {
        Div mainInfoDiv = new Div();
        mainInfoDiv.addClassNames("basic");

        Div restaurantInfoDiv = infoDiv();
        Div reviews = reviewsDiv();

        mainInfoDiv.add(restaurantInfoDiv, reviews);

        return mainInfoDiv;

    }

    public Div mainInfoDiv() {
        Div mainInfoDiv = new Div();
        mainInfoDiv.addClassNames("restaurant-page-info-div");

        Div restaurantPhotosDiv = new Div();
        restaurantPhotosDiv.addClassNames("restaurant-image");

        Div photo = new Div();
        photo.addClassNames("basic");
        Image image = new Image();
        image.setSrc("https://t3.ftcdn.net/jpg/03/24/73/92/360_F_324739203_keeq8udvv0P2h1MLYJ0GLSlTBagoXS48.jpg");
        photo.add(image);
        restaurantPhotosDiv.add(photo);

        Div leftPartDiv = new Div();
        leftPartDiv.addClassNames("restaurant-info-div");

        Div restaurantData = new Div();
        restaurantData.addClassNames("restaurant-data");

        Div restaurantName = new Div(new Span(restaurant.getName()));
        restaurantName.getStyle().setFontSize("24px");

        String cuisine = restaurant.getCuisineType().substring(0, 1).toUpperCase() + restaurant.getCuisineType().substring(1);
        Div cuisineType = new Div(new Span(cuisine + " кухня"));
        cuisineType.getStyle().setColor("#003399");

        restaurantData.add(restaurantName, cuisineType);

        String restaurantWebsite = restaurant.getWebsite();
        if (restaurantWebsite != null && !restaurantWebsite.equals("null")) {
            Anchor website = new Anchor(restaurantWebsite, restaurantWebsite);
            website.setTarget("_blank");
            website.getStyle().setTextDecoration("none").setColor("black");
            Text text = new Text("Вебсайт - ");
            Div websiteLink = new Div(text, website);
            restaurantData.add(websiteLink);
        }

        Div address = new Div(new Span(restaurant.getAddress()));
        Div rating = new Div(new Span("Рейтинг - " + String.format(String.valueOf(Math.round(restaurant.getRating()))) + " ⭐"));
        restaurantData.add(address, rating);

        leftPartDiv.add(restaurantData);

        mainInfoDiv.add(leftPartDiv, restaurantPhotosDiv);
        return mainInfoDiv;
    }

    public Div infoDiv() {
        Div imageDiv = new Div();
        imageDiv.addClassNames("curr-restaurant-info-div");

        Image image = new Image();
        image.setSrc("https://t3.ftcdn.net/jpg/03/24/73/92/360_F_324739203_keeq8udvv0P2h1MLYJ0GLSlTBagoXS48.jpg");
        image.addClassNames("fit-c");

        Div downPartDiv = new Div();

        Div restaurantData = new Div();
        restaurantData.addClassNames("restaurant-data");

        Div restaurantName = new Div(new Span(restaurant.getName()));
        restaurantName.getStyle().setFontSize("24px");

        String cuisine = restaurant.getCuisineType().substring(0, 1).toUpperCase() + restaurant.getCuisineType().substring(1);
        Div cuisineType = new Div(new Span(cuisine + " кухня"));
        cuisineType.getStyle().setColor("#003399");

        restaurantData.add(restaurantName, cuisineType);

        String restaurantWebsite = restaurant.getWebsite();
        if (restaurantWebsite != null && !restaurantWebsite.equals("null")) {
            Anchor website = new Anchor(restaurantWebsite, restaurantWebsite);
            website.setTarget("_blank");
            website.getStyle().setTextDecoration("none").setColor("black");
            Text text = new Text("Вебсайт - ");
            Div websiteLink = new Div(text, website);
            restaurantData.add(websiteLink);
        }

        Div address = new Div(new Span(restaurant.getAddress()));
        Div rating = new Div(new Span("Рейтинг - " + restaurant.getRating() + " ⭐"));
        restaurantData.add(address, rating);

        downPartDiv.add(restaurantData);

        imageDiv.add(image, downPartDiv);
        return imageDiv;
    }

    public Div reviewsDiv() {
        Restaurant rest = Convertor.convertToEntity(restaurantService.findByRestaurantId(restaurantId));
        Div reviewDiv = new Div();
        reviewDiv.addClassNames("review-div");

        Div inputReview = new Div();
//        TextArea textArea = new TextArea();
//        textArea.setWidthFull();
//        textArea.setMaxHeight("100px");
//        textArea.setLabel("Новий відгук");
//        Div addReviewButton = new Div(new Button("Додати"));
        MessageInput input = new MessageInput();
        input.addSubmitListener(submitEvent -> {
            MessageListItem newMessage = new MessageListItem(
                    submitEvent.getValue(), Instant.now(), "Milla Sting");
            Review review = new Review();
            review.setText(newMessage.getText());
            review.setRestaurant(rest);
            review.setTime(Timestamp.from(newMessage.getTime()));
            reviewService.saveToDb(review);
        });
        inputReview.add(input);

        reviewDiv.add(inputReview);

        Div listOfReviews = new Div();
        MessageList list = new MessageList();
        List<ReviewDTO> reviews = reviewService.getReviewsByRestaurant(restaurant);
        List<MessageListItem> messages = new ArrayList<>();
        System.out.println("reviews - " + reviews.size());


        for (ReviewDTO review : reviews) {
            messages.add(new MessageListItem(review.getText(), review.getTime().toInstant(), review.getUserDTO().getUsername()));
        }
        list.setItems(messages);
        listOfReviews.add(list);
        reviewDiv.add(listOfReviews);
        return reviewDiv;
    }

}