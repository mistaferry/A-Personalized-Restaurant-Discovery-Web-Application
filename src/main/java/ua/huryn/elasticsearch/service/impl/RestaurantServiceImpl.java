package ua.huryn.elasticsearch.service.impl;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.maps.GeoApiContext;
import com.google.maps.PlacesApi;
import com.google.maps.errors.ApiException;
import com.google.maps.errors.InvalidRequestException;
import com.google.maps.model.*;
import lombok.RequiredArgsConstructor;
import org.jetbrains.annotations.NotNull;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.stereotype.Service;
import ua.huryn.elasticsearch.entity.Category;
import ua.huryn.elasticsearch.entity.Restaurant;
import ua.huryn.elasticsearch.model.RestaurantModel;
import ua.huryn.elasticsearch.repository.elasticsearch.RestaurantRepository;
import ua.huryn.elasticsearch.repository.db.CategoryDbRepository;
import ua.huryn.elasticsearch.repository.db.RestaurantDbRepository;
import ua.huryn.elasticsearch.service.RestaurantService;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.File;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

@Service
@RequiredArgsConstructor
public class RestaurantServiceImpl implements RestaurantService {
    private final RestaurantRepository restaurantRepository;
    private final RestaurantDbRepository restaurantDbRepository;
    private final CategoryDbRepository categoryDbRepository;

    @Override
    public List<RestaurantModel> findByRating(double rating) {
        List<RestaurantModel> list = new ArrayList<>();

        double[] ratingRange = calculateRatingRange(rating);
        double startRating = ratingRange[0];
        double endRating = ratingRange[1];

        for (double i = startRating; i <= endRating; i += 0.1) {
            list.addAll(restaurantRepository.findByRating(i));
        }
        return list;
    }

    private double[] calculateRatingRange(double rating) {
        double startRating = 0;
        double endRating = 0;
        long key = Math.round(rating);

        switch ((int) key) {
            case 5:
                startRating = 4.5;
                endRating = 5.0;
                break;
            case 4:
                startRating = 3.5;
                endRating = 4.4;
                break;
            case 3:
                startRating = 2.5;
                endRating = 3.4;
                break;
            case 2:
                startRating = 1.5;
                endRating = 2.4;
                break;
            case 1:
                startRating = 0;
                endRating = 1.4;
                break;
            default:
                break;
        }

        return new double[]{startRating, endRating};
    }

    @Override
    public List<RestaurantModel> findByName(String name) {
        return restaurantRepository.findByName(name);
    }

    @Override
    public List<RestaurantModel> findByLatitudeAndLongitude(double latitude, double longitude) {
        return restaurantRepository.findByLatitudeAndLongitude(latitude, longitude);
    }

    @Override
    public List<RestaurantModel> findByPrice_level(int priceLevel) {
        return new ArrayList<>(restaurantRepository.findByPriceLevel(priceLevel));
    }

    @Override
    public List<RestaurantModel> findByRatingAndPrice_level(double rating, int priceLevel) {
        List<RestaurantModel> list = new ArrayList<>();

        double[] ratingRange = calculateRatingRange(rating);
        double startRating = ratingRange[0];
        double endRating = ratingRange[1];

        for (double i = startRating; i <= endRating; i += 0.1) {
            list.addAll(restaurantRepository.findByRatingAndPriceLevel(i, priceLevel));
        }
        return list;
    }

    @Override
    public List<RestaurantModel> getFiltered(List<Integer> rating, List<Integer> price) {
        List<RestaurantModel> filteredData = new ArrayList<>();
        if((rating!= null && !rating.isEmpty()) &&
                (price!= null && !price.isEmpty())){
            filteredByRatingAndPriceLevel(rating, price, filteredData);
        }else{
            filteredByRating(rating, filteredData);
            filteredByPriceLevel(price, filteredData);
        }
        if(filteredData.isEmpty()){
            filteredData.addAll(findByRating(4.5));
        }
        return filteredData;
    }

    private void filteredByPriceLevel(List<Integer> price, List<RestaurantModel> filteredData) {
        if(price != null && !price.isEmpty()){
            for (Integer priceLevel: price){
                filteredData.addAll(findByPrice_level(priceLevel));
            }
        }
    }

    private void filteredByRating(List<Integer> rating, List<RestaurantModel> filteredData) {
        if(rating != null && !rating.isEmpty()){
            for (Integer rate: rating){
                filteredData.addAll(findByRating(rate));
            }
        }
    }

    private void filteredByRatingAndPriceLevel(List<Integer> rating, List<Integer> price, List<RestaurantModel> filteredData) {
        for (Integer rate: rating){
            for (Integer p: price){
                List<RestaurantModel> filteredByBoth = findByRatingAndPrice_level(rate, p);
                filteredData.addAll(filteredByBoth);
            }
        }
    }

//    @Override
//    public List<RestaurantModel> findByCategory(Category category) {
//        return ;
//    }

    @Override
    public void addDataToDb() throws IOException, InterruptedException, ApiException {
        List<LatLng> locationsList = getLocationFromJson();
        Properties appProps = new Properties();
        appProps.load(new FileInputStream("src/main/resources/api.properties"));
        GeoApiContext context = new GeoApiContext.Builder()
                .apiKey(appProps.getProperty("api_key"))
                .build();
        int count = 0;
        for (LatLng loc: locationsList){
            PlacesSearchResponse response = PlacesApi.nearbySearchQuery(context, loc)
                    .radius(3500)
                    .keyword("restaurant")
                    .language("en")
                    .await();

            PlacesSearchResult[] placesSearchResults = response.results;
            getRestaurantsDataFromResponse(placesSearchResults, context);
            getRestaurantsDataFromResponseWithNextPageToken(loc, response, context);

        }
    }

    private void getRestaurantsDataFromResponseWithNextPageToken(LatLng loc, PlacesSearchResponse response, GeoApiContext context) throws ApiException, InterruptedException, IOException {
        String nextPageToken = response.nextPageToken;
        while (nextPageToken != null) {
            try {
                response = PlacesApi.nearbySearchQuery(context, loc)
                        .radius(1500)
                        .keyword("restaurant")
                        .language("en")
                        .pageToken(nextPageToken)
                        .await();
                getRestaurantsDataFromResponse(response.results, context);
                nextPageToken = response.nextPageToken;
            } catch (InvalidRequestException e) {
                break;
            }
        }
    }

    private void getRestaurantsDataFromResponse(PlacesSearchResult[] res, GeoApiContext context) {
        for (int i = 0; i < res.length; i++) {
            PlacesSearchResult r = res[i];
            Object existsObject = restaurantDbRepository.findByPlace_id(r.placeId);
            if(existsObject == null) {
                Restaurant restaurant = getItem(r, context);
                restaurantDbRepository.save(restaurant);
            }
        }
    }

    private List<LatLng> getLocationFromJson() {
        List<LatLng> locationsList = new ArrayList<>();
        String path = "src/main/resources/json_data/locations.json";
        try {
            String content = "";
            content = new String(Files.readAllBytes(Paths.get(path)));
            JSONArray jsonArray = new JSONArray(content);
            for (int i = 0; i < jsonArray.length(); i++) {
                JSONObject jsonObject = jsonArray.getJSONObject(i);
                double lat = jsonObject.getDouble("lat");
                double lng = jsonObject.getDouble("lng");
                LatLng l = new LatLng(lat, lng);
                locationsList.add(l);
            }
        }catch (IOException e){
            System.err.println("Немає доступу до файлу: " + path);
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
            System.err.println("Неможливо отримати дані ресторану з placeId: " + restaurantResult.placeId);
        }
    }

    @NotNull
    private List<Category> getCategories(PlacesSearchResult restaurantResult) {
        List<Category> categoriesList = new ArrayList<>();
        String[] types = restaurantResult.types;

        for (int j = 0; j < types.length; j++) {
            String typeName = types[j];
            Object existingCategory = categoryDbRepository.findByName(typeName);

            if (existingCategory != null) {
                Category category = categoryDbRepository.findByName(typeName);
                categoriesList.add(category);
            } else {
                Category newCategory = new Category();
                newCategory.setName(typeName);
                categoryDbRepository.save(newCategory);
                categoriesList.add(newCategory);
            }
        }
        return categoriesList;
    }

    @Override
    public void addApiDataToFile(){
        try {
            List<RestaurantModel> restaurants = restaurantRepository.findAll();

            ObjectMapper mapper = new ObjectMapper();

            File file = new File("src/main/resources/json_data/restaurants.json");

            // Зберегти всі ресторани в файл у форматі JSON
            mapper.writeValue(file, restaurants);

            System.out.println("Дані збережено у файл restaurants.json");
        } catch (Exception e) {
            System.err.println("Неможливо зберегти дані в файл.");
            e.printStackTrace();
        }
    }

    @Override
    public void addDataFromFileToDb(){
        ObjectMapper objectMapper = new ObjectMapper();

        try {
            // Read JSON from file into a list of Restaurant objects
            List<Restaurant> restaurants = objectMapper.readValue(new File("src/main/resources/json_data/restaurants.json"),
                    new TypeReference<List<Restaurant>>() {});

            // Do something with the list of restaurants
            for (Restaurant restaurant : restaurants) {
                List<Category> categories = restaurant.getCategories();
                for (Category category: categories){
                    Category existsCategory = categoryDbRepository.findByName(category.getName());
                    if(existsCategory == null){
                        categoryDbRepository.save(category);
                    }
                }
            }

            restaurantDbRepository.saveAll(restaurants);
            System.out.println("Успішно отримано дані з файлу");
        } catch (IOException e) {
            e.printStackTrace();
            System.err.println("Неможливо отримані дані з файлу.");
        }
    }
}
