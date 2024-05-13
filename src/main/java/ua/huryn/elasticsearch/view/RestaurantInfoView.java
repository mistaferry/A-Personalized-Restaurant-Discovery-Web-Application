package ua.huryn.elasticsearch.view;

import com.vaadin.flow.component.Text;
import com.vaadin.flow.component.html.Anchor;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.html.Image;
import com.vaadin.flow.component.html.Span;
import com.vaadin.flow.component.messages.MessageInput;
import com.vaadin.flow.component.messages.MessageList;
import com.vaadin.flow.component.messages.MessageListItem;
import com.vaadin.flow.router.*;
import com.vaadin.flow.server.StreamResource;
import jakarta.annotation.security.PermitAll;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.core.OAuth2AuthenticatedPrincipal;
import ua.huryn.elasticsearch.MainView;
import ua.huryn.elasticsearch.config.GeneralProperties;
import ua.huryn.elasticsearch.entity.db.Restaurant;
import ua.huryn.elasticsearch.entity.db.Review;
import ua.huryn.elasticsearch.entity.db.User;
import ua.huryn.elasticsearch.entity.dto.RestaurantDTO;
import ua.huryn.elasticsearch.entity.dto.ReviewDTO;
import ua.huryn.elasticsearch.repository.db.UserDbRepository;
import ua.huryn.elasticsearch.service.RestaurantService;
import ua.huryn.elasticsearch.service.ReviewService;
import ua.huryn.elasticsearch.utils.Convertor;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.sql.Timestamp;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

import static org.reflections.Reflections.log;

@Route(value = "restaurant", layout = MainView.class)
@PermitAll
public class RestaurantInfoView extends Div implements HasUrlParameter<Long> {
    private Long restaurantId;
    private RestaurantDTO restaurant;
    private final RestaurantService restaurantService;
    private final ReviewService reviewService;
    private final UserDbRepository userDbRepository;
    private final String localDirectory;
    private final GeneralProperties generalProperties;
    private final User user;
    private Double latitude;
    private Div reviewDiv;
    private MessageInput input;

    public RestaurantInfoView(RestaurantService restaurantService, ReviewService reviewService, UserDbRepository userDbRepository, GeneralProperties generalProperties) {
        this.restaurantService = restaurantService;
        this.reviewService = reviewService;
        this.userDbRepository = userDbRepository;
        this.user = getUser();
        this.input = new MessageInput();
        this.generalProperties = generalProperties;
        this.localDirectory=generalProperties.getLocalDirectory();
        inputMessageListener();
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

//    public Div mainInfoDiv() {
//        Div mainInfoDiv = new Div();
//        mainInfoDiv.addClassNames("restaurant-page-info-div");
//
//        Div restaurantPhotosDiv = new Div();
//        restaurantPhotosDiv.addClassNames("restaurant-image");
//
//        Div photo = new Div();
//        photo.addClassNames("basic");
//        String scr = localDirectory + "/db_data/restaurant_images/" + restaurant.getPlaceId() + "_image.jpg";
//
//        StreamResource resource = new StreamResource("image.jpg", () -> {
//            try {
//                return new FileInputStream(scr);
//            } catch (FileNotFoundException e) {
//                log.error("No image found for restaurant placeId - " + restaurant.getPlaceId());
//            }
//            return null;
//        });
//        Image image = new Image(resource, "Image");
//        photo.add(image);
//        restaurantPhotosDiv.add(photo);
//
//        Div leftPartDiv = new Div();
//        leftPartDiv.addClassNames("restaurant-info-div");
//
//        Div restaurantData = new Div();
//        restaurantData.addClassNames("restaurant-data");
//
//        Div restaurantName = new Div(new Span(restaurant.getName()));
//        restaurantName.getStyle().setFontSize("24px");
//
//        String cuisine = restaurant.getCuisineType().substring(0, 1).toUpperCase() + restaurant.getCuisineType().substring(1);
//        Div cuisineType = new Div(new Span(cuisine + " кухня"));
//        cuisineType.getStyle().setColor("#003399");
//
//        restaurantData.add(restaurantName, cuisineType);
//
//        String restaurantWebsite = restaurant.getWebsite();
//        if (restaurantWebsite != null && !restaurantWebsite.equals("null")) {
//            Anchor website = new Anchor(restaurantWebsite, restaurantWebsite);
//            website.setTarget("_blank");
//            website.getStyle().setTextDecoration("none").setColor("black");
//            Text text = new Text("Вебсайт - ");
//            Div websiteLink = new Div(text, website);
//            restaurantData.add(websiteLink);
//        }
//
//        Div address = new Div(new Span(restaurant.getAddress()));
//        Div rating = new Div(new Span("Рейтинг - " + String.format(String.valueOf(Math.round(restaurant.getRating()))) + " ⭐"));
//        restaurantData.add(address, rating);
//
//        leftPartDiv.add(restaurantData);
//
//        mainInfoDiv.add(leftPartDiv, restaurantPhotosDiv);
//        return mainInfoDiv;
//    }

    public Div infoDiv() {
        Div imageDiv = new Div();
        imageDiv.addClassNames("curr-restaurant-info-div");

        String scr = localDirectory + "/db_data/restaurant_images/" + restaurant.getPlaceId() + "_image.jpg";

        StreamResource resource = new StreamResource("image.jpg", () -> {
            try {
                return new FileInputStream(scr);
            } catch (FileNotFoundException e) {
                log.error("No image found for restaurant placeId - " + restaurant.getPlaceId());
            }
            return null;
        });
        Image image = new Image(resource, "Image");
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
//        Restaurant rest = Convertor.convertToEntity(restaurantService.findByRestaurantId(restaurantId));
        reviewDiv = new Div();
        reviewDiv.addClassNames("review-div");

        Div inputReview = new Div();
        inputReview.add(input);

        reviewDiv.add(inputReview);

        Div listOfReviews = new Div();
        MessageList list = getMessageList();

        listOfReviews.add(list);

        listOfReviews.getStyle().set("max-height", "300px");
        listOfReviews.getStyle().set("overflow-y", "auto");

        reviewDiv.add(listOfReviews);
        return reviewDiv;
    }

    private MessageList getMessageList(){
        MessageList list = new MessageList();
        List<ReviewDTO> reviews = reviewService.getReviewsByRestaurant(restaurant);
        List<MessageListItem> messages = new ArrayList<>();

        for (ReviewDTO review : reviews) {
            messages.add(new MessageListItem(review.getText(), review.getTime().toInstant(), review.getUserDTO().getUsername(), review.getUserDTO().getPicture()));
        }
        list.setItems(messages);
        return list;
    }

    private User getUser(){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        OAuth2AuthenticatedPrincipal principal = (OAuth2AuthenticatedPrincipal) authentication.getPrincipal();
        return userDbRepository.findByEmail(principal.getAttribute("email")).orElse(null);
    }

    void inputMessageListener(){
        input.addSubmitListener(submitEvent -> {
            MessageListItem newMessage = new MessageListItem(
                    submitEvent.getValue(), Instant.now(), user.getUsername());
            Review review = new Review();
            review.setText(newMessage.getText());
            review.setRestaurant(Convertor.convertToEntity(restaurant));
            review.setTime(Timestamp.from(newMessage.getTime()));
            review.setUser(user);
            reviewService.saveToDb(review);
            removeAll();
            add(pageDiv());
        });
    }
}