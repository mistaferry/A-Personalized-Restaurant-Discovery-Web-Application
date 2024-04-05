package ua.huryn.elasticsearch;

import ch.qos.logback.classic.encoder.PatternLayoutEncoder;
import com.google.maps.GeoApiContext;
import com.google.maps.PlacesApi;
import com.google.maps.errors.ApiException;
import com.google.maps.model.LatLng;
import com.google.maps.model.PlacesSearchResponse;
import com.google.maps.model.PlacesSearchResult;
import com.vaadin.flow.component.Text;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.Route;

import java.io.IOException;

@Route
public class MainView extends VerticalLayout {

    public MainView() {
        GeoApiContext context = new GeoApiContext.Builder()
                .apiKey("AIzaSyA6E4ALQqd80tYtj2yy7YfD1J77HoRttcw")
                .build();
        LatLng location = new LatLng(50.450001, 30.523333);

// Perform the nearby search query
        try {
            PlacesSearchResponse response = PlacesApi.nearbySearchQuery(context, location)
                    .radius(500) // Set the search radius
                    .keyword("restaurant") // Optional: specify a keyword to filter results
                    .await(); // Execute the request synchronously

            // Iterate over the results and do something with them
            for (PlacesSearchResult result : response.results) {
                // Process each search result
                System.out.println(result.name + " - " + result.vicinity);
            }
        } catch (ApiException | InterruptedException | IOException e) {
            e.printStackTrace();
        }
        add(new Text("Welcome to MainView."));
    }

}