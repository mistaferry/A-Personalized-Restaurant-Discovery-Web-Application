package ua.huryn.elasticsearch.service.impl;

import com.google.maps.GeoApiContext;
import com.google.maps.PlacesApi;
import com.google.maps.errors.ApiException;
import com.google.maps.model.*;
import lombok.RequiredArgsConstructor;
import org.jetbrains.annotations.NotNull;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ua.huryn.elasticsearch.entity.Category;
import ua.huryn.elasticsearch.entity.Restaurant;
import ua.huryn.elasticsearch.model.RestaurantModel;
import ua.huryn.elasticsearch.repository.RestaurantRepository;
import ua.huryn.elasticsearch.repository1.CategoryDbRepository;
import ua.huryn.elasticsearch.repository1.RestaurantDbRepository;
import ua.huryn.elasticsearch.service.RestaurantService;

import java.io.FileInputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

@Service
@RequiredArgsConstructor
public class RestaurantServiceImpl implements RestaurantService {
    @Autowired
    RestaurantRepository restaurantRepository;
    @Autowired
    RestaurantDbRepository restaurantDbRepository;
    @Autowired
    CategoryDbRepository categoryDbRepository;

    @Override
    public List<RestaurantModel> findByName(String name) {
        return restaurantRepository.findByName(name);
    }

    @Override
    public List<RestaurantModel> findByRating(int rating) {
        return restaurantRepository.findByRating(rating);
    }



    @Override
    public List<RestaurantModel> findByLatitudeAndLongitude(double latitude, double longitude) {
        return restaurantRepository.findByLatitudeAndLongitude(latitude, longitude);
    }

    @Override
    public void addDataToDb(){
        try {
            List<LatLng> locationsList = getLocationFromJson();
            Properties appProps = new Properties();
            appProps.load(new FileInputStream("src/main/resources/api.properties"));
            GeoApiContext context = new GeoApiContext.Builder()
                    .apiKey(appProps.getProperty("api_key"))
                    .build();

            for (LatLng loc: locationsList){
                PlacesSearchResponse response = PlacesApi.nearbySearchQuery(context, loc)
                        .radius(1000)
                        .keyword("restaurant")
                        .language("en")
                        .await();

                PlacesSearchResult[] res = response.results;
                for (int i = 0; i < res.length; i++) {
                    PlacesSearchResult r = res[i];
                    Restaurant existsRestaurant = restaurantDbRepository.findByPlace_id(r.placeId);
                    if(existsRestaurant == null) {
                        Restaurant restaurant = getItem(r, context);
                        restaurantDbRepository.save(restaurant);
                    }
                }
            }
        } catch (IOException | ApiException | InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    private List<LatLng> getLocationFromJson(){
        List<LatLng> locationsList = new ArrayList<>();
        try {
            String content = "";
            content = new String(Files.readAllBytes(Paths.get("src/main/resources/json_data/locations.json")));
            JSONArray jsonArray = new JSONArray(content);
            for (int i = 0; i < jsonArray.length(); i++) {
                JSONObject jsonObject = jsonArray.getJSONObject(i);
                double lat = jsonObject.getDouble("lat");
                double lng = jsonObject.getDouble("lng");
                LatLng l = new LatLng(lat, lng);
                locationsList.add(l);
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return locationsList;
    }

    private Restaurant getItem(PlacesSearchResult restaurantResult, GeoApiContext context){
        Restaurant restaurant = new Restaurant();
        restaurant.setPlace_id(restaurantResult.placeId);
        restaurant.setName(restaurantResult.name);
        restaurant.setAddress(restaurantResult.vicinity);
        restaurant.setLatitude(restaurantResult.geometry.location.lat);
        restaurant.setLongitude(restaurantResult.geometry.location.lng);
        restaurant.setRating((double) restaurantResult.rating);
        Photo[] photos = restaurantResult.photos;
        if(photos != null) {
            restaurant.setPhoto_ref(photos[0].photoReference);
        }else{
            restaurant.setPhoto_ref("");
        }
        List<Category> categoriesList = getCategories(restaurantResult);

        setItemDetails(restaurantResult, restaurant, context);

        restaurant.setCategories(categoriesList);
        return restaurant;
    }

    private static void setItemDetails(PlacesSearchResult restaurantResult, Restaurant restaurant, GeoApiContext context) {
        try {
            PlaceDetails details = PlacesApi.placeDetails(context, restaurantResult.placeId).await();
            PriceLevel priceLev = details.priceLevel;
            if (priceLev != null) {
                restaurant.setPrice_level(priceLev.ordinal());
            } else {
                restaurant.setPrice_level(0);
            }
            restaurant.setWebsite(String.valueOf(details.website));
        } catch (Exception e) {
            System.err.println("Помилка при отриманні даних для ресторану з placeId: " + restaurantResult.placeId);
        }
    }

    @NotNull
    private List<Category> getCategories(PlacesSearchResult restaurantResult) {
        List<Category> categoriesList = new ArrayList<>();
        String[] types = restaurantResult.types;

        for (int j = 0; j < types.length; j++) {
            String typeName = types[j];
            Category existingCategory = categoryDbRepository.findByName(typeName);

            if (existingCategory != null) {
                categoriesList.add(existingCategory);
            } else {
                Category newCategory = new Category();
                newCategory.setName(typeName);
                categoryDbRepository.save(newCategory);
                categoriesList.add(newCategory);
            }
        }
        return categoriesList;
    }
}
