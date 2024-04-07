package ua.huryn.elasticsearch.service.impl;

import com.google.maps.GeoApiContext;
import com.google.maps.PlacesApi;
import com.google.maps.errors.ApiException;
import com.google.maps.model.LatLng;
import com.google.maps.model.PlacesSearchResponse;
import com.google.maps.model.PlacesSearchResult;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ua.huryn.elasticsearch.model.Restaurant;
import ua.huryn.elasticsearch.repository.RestaurantRepository;
import ua.huryn.elasticsearch.service.DbInsertService;
import ua.huryn.elasticsearch.service.RestaurantService;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.List;
import java.util.Properties;

@Service
@RequiredArgsConstructor
public class RestaurantServiceImpl implements RestaurantService {
    DbInsertService dbInsertRepository;
    private final RestaurantRepository restaurantRepository;


    LatLng location = new LatLng(50.450001, 30.523333);
    @Override
    public List<Restaurant> findByName(String name) {
        return restaurantRepository.findByName(name);
    }

    @Override
    public List<Restaurant> findByRating(int rating) {
        return restaurantRepository.findByRating(rating);
    }



    @Override
    public List<Restaurant> findByLatitudeAndLongitude(double latitude, double longitude) {
        return restaurantRepository.findByLatitudeAndLongitude(latitude, longitude);
    }

    @Override
    public PlacesSearchResponse getAllRestaurantsFromApi() throws IOException, InterruptedException, ApiException {
        Properties appProps = new Properties();
        appProps.load(new FileInputStream("src/main/resources/api.properties"));
        GeoApiContext context = new GeoApiContext.Builder()
                .apiKey(appProps.getProperty("api_key"))
                .build();

        PlacesSearchResponse response = PlacesApi.nearbySearchQuery(context, location)
                .radius(500) // Set the search radius
                .keyword("restaurant") // Optional: specify a keyword to filter results
                .await(); // Execute the request synchronously

        // Iterate over the results and do something with them
        for (PlacesSearchResult result : response.results) {
            // Process each search result
            System.out.println(result.name + " - " + result.vicinity);
        }
        return response;
    }

    @Override
    public List<Restaurant> getAll() throws IOException, InterruptedException, ApiException {
        addRestaurantsToDb();
        return restaurantRepository.findAll();
    }

    @Override
    public void addRestaurantsToDb() throws IOException, InterruptedException, ApiException {
        PlacesSearchResponse response = getAllRestaurantsFromApi();
        for (PlacesSearchResult result : response.results) {
            Restaurant restaurant = new Restaurant();
            restaurant.setName(result.name);
            restaurant.setLatitude(location.lat);
            restaurant.setLongitude(location.lng);
            restaurant.setRating((int) result.rating);
            dbInsertRepository.insert(restaurant);
        }
    }
}
