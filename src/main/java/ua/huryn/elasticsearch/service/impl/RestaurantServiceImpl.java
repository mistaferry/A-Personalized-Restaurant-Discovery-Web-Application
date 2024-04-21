package ua.huryn.elasticsearch.service.impl;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.maps.DistanceMatrixApi;
import com.google.maps.DistanceMatrixApiRequest;
import com.google.maps.GeoApiContext;
import com.google.maps.PlacesApi;
import com.google.maps.errors.ApiException;
import com.google.maps.errors.InvalidRequestException;
import com.google.maps.model.*;
import com.google.maps.routing.v2.*;
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

import java.time.Duration;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.File;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;
import java.util.regex.*;

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
    public List<RestaurantModel> findByCuisineType(String cuisineType) {
        return restaurantRepository.findByCuisineType(cuisineType);
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
    public List<RestaurantModel> getFiltered(List<String> cuisineTypes, List<Integer> rating, List<Integer> price, List<Integer> routes, String firstPoint) {
        List<RestaurantModel> filteredData = restaurantRepository.findAll();

        filteredData = filteredByRating(rating, filteredData);
        filteredData = filteredByPriceLevel(price, filteredData);
        filteredData = filteredByCuisineType(cuisineTypes, filteredData);
        filteredData = getRestaurantInGivenDistance(firstPoint, routes, filteredData);

        if(cuisineTypes.isEmpty() && rating.isEmpty() && price.isEmpty() && filteredData.isEmpty()){
            filteredData = restaurantRepository.findAll();
        }
        System.out.println("cuisineTypes - " +cuisineTypes);
        System.out.println("rating - " +rating);
        System.out.println("price - " +price);
        System.out.println("routes - " +routes);
        System.out.println("filtered size - " + filteredData.size());
        return filteredData;
    }

    @Override
    public List<RestaurantModel> getRestaurantInGivenDistance(String firstPoint, List<Integer> routes, List<RestaurantModel> filtered) {
        if(routes != null && !routes.isEmpty()) {
            Pattern SPLIT_PATTERN = Pattern.compile("^(.*?),\\s*(.*)$");
            Matcher splitMatcher = SPLIT_PATTERN.matcher(firstPoint);

            List<TravelMode> modes = new ArrayList<>();
            for (Integer route: routes){
                if(route == 2){
                    modes.add(TravelMode.WALKING);
                }
                if(route == 1){
                    modes.add(TravelMode.DRIVING);
                }
                if(route == 0){
                    modes.add(TravelMode.UNKNOWN);
                }
            }

            if (splitMatcher.matches()) {
                try {
                    GeoApiContext context = getGeoApiContext();
                    String location = splitMatcher.group(1).trim();
                    String numValue = splitMatcher.group(2).trim();

//                    Pattern GEO_COORDINATE_PATTERN = Pattern.compile("[0-9.]\\s[0-9.]");
//                    Matcher geoMatcher = GEO_COORDINATE_PATTERN.matcher(location);
//
//                    if (geoMatcher.matches()) {
                    filtered = getRestaurantsByAddress(context, location, numValue, filtered, modes);
//                    } else {
//                        filtered = getRestaurantsByAddress(context, location, numValue, filtered, modes);
//                    }
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
        }
        return filtered;
    }

    private static GeoApiContext getGeoApiContext() throws IOException {
        Properties appProps = new Properties();
        appProps.load(new FileInputStream("src/main/resources/api.properties"));
        return new GeoApiContext.Builder()
                .apiKey(appProps.getProperty("api_key"))
                .build();
    }

    private List<RestaurantModel> getRestaurantsByAddress(GeoApiContext context, String address, String numValue, List<RestaurantModel> filtered, List<TravelMode> modes){
        List<RestaurantModel> restaurantModelList = new ArrayList<>();
        try {
            for (TravelMode mode: modes){
                for (RestaurantModel restaurant : filtered) {
                    if(mode.equals(TravelMode.UNKNOWN)){
                        mode = TravelMode.WALKING;
                        DistanceMatrix distanceMatrix = new DistanceMatrixApiRequest(context)
                                .origins(address)
                                .mode(mode)
                                .destinations(restaurant.getAddress())
                                .await();
                        long timeInSec = ((distanceMatrix.rows[0]).elements[0]).duration.inSeconds;
                        int timeInMin = Math.round((float) timeInSec /60);
                        if (timeInMin <= Integer.parseInt(numValue)) {
                            restaurantModelList.add(restaurant);
                        }
                    }else {
                        DistanceMatrix distanceMatrix = new DistanceMatrixApiRequest(context)
                                .origins(address)
                                .mode(mode)
                                .destinations(restaurant.getAddress())
                                .await();
                        long distanceInMeters = ((distanceMatrix.rows[0]).elements[0]).distance.inMeters;
                        int distanceInKm = (int) Math.round(distanceInMeters * 0.001);
                        if (distanceInKm <= Integer.parseInt(numValue)) {
                            restaurantModelList.add(restaurant);
                        }
                    }
                }
            }
            return restaurantModelList;
        } catch (ApiException | InterruptedException | IOException e) {
            throw new RuntimeException(e);
        }
    }

    private boolean isGeographicCoordinates(String text) {
        Pattern GEO_COORDINATE_PATTERN = Pattern.compile(
                "^\\s*([-+]?\\d*\\.?\\d+) \\s*([-+]?\\d*\\.?\\d+)\\s*$"
        );
        Matcher matcher = GEO_COORDINATE_PATTERN.matcher(text);
        return matcher.matches();
    }

    private boolean isAddress(String text) {
        return !isGeographicCoordinates(text);
    }

    private List<RestaurantModel> filteredByCuisineType(List<String> cuisineTypes, List<RestaurantModel> filteredData){
        if(cuisineTypes != null && !cuisineTypes.isEmpty()){
            List<RestaurantModel> filtered = new ArrayList<>();
            for (String cuisine: cuisineTypes){
                for(RestaurantModel restaurantModel: filteredData) {
                    if(restaurantModel.getCuisineType().equals(cuisine)){
                        filtered.add(restaurantModel);
                    }
                }
            }
            return filtered;
        }else{
            return filteredData;
        }
    }

    private List<RestaurantModel> filteredByPriceLevel(List<Integer> price, List<RestaurantModel> filteredData) {
        if(price != null && !price.isEmpty()){
            List<RestaurantModel> filtered = new ArrayList<>();
            for (Integer priceLevel: price){
                for(RestaurantModel restaurantModel: filteredData){
                    if(restaurantModel.getPriceLevel() == priceLevel){
                        filtered.add(restaurantModel);
                    }
                }
            }
            return filtered;
        }else {
            return filteredData;
        }
    }

    private List<RestaurantModel> filteredByRating(List<Integer> rating, List<RestaurantModel> filteredData) {
        if(rating != null && !rating.isEmpty()){
            List<RestaurantModel> filtered = new ArrayList<>();
            for (Integer rate: rating){
                filtered.addAll(findByRating(rate));
            }
            return filtered;
        }else {
            return filteredData;
        }
    }

    @Override
    public void addDataToDb() throws IOException, InterruptedException, ApiException {
        List<LatLng> locationsList = getLocationFromJson();
        List<String> cuisineList = getCuisineTypeFromJson();
        GeoApiContext context = getGeoApiContext();

        for (String cuisine: cuisineList){
            for (LatLng loc: locationsList){
                PlacesSearchResponse response = PlacesApi.nearbySearchQuery(context, loc)
                        .radius(3500)
                        .type(PlaceType.RESTAURANT)
                        .keyword(cuisine)
                        .language("en")
                        .await();

                PlacesSearchResult[] placesSearchResults = response.results;
                getRestaurantsDataFromResponse(placesSearchResults, context, cuisine);
                getRestaurantsDataFromResponseWithNextPageToken(loc, response, context, cuisine);
            }
        }
        getRestaurantsNearby(locationsList, context);
    }

    private void getRestaurantsNearby(List<LatLng> locationsList, GeoApiContext context) throws ApiException, InterruptedException, IOException {
        for (LatLng loc: locationsList){
            PlacesSearchResponse response = PlacesApi.nearbySearchQuery(context, loc)
                    .radius(3500)
                    .type(PlaceType.RESTAURANT)
                    .language("en")
                    .await();

            PlacesSearchResult[] placesSearchResults = response.results;
            getRestaurantsDataFromResponse(placesSearchResults, context, "");
            getRestaurantsDataFromResponseWithNextPageToken(loc, response, context, "");
        }
    }

    private void getRestaurantsDataFromResponseWithNextPageToken(LatLng loc, PlacesSearchResponse response, GeoApiContext context, String cuisine) throws ApiException, InterruptedException, IOException {
        String nextPageToken = response.nextPageToken;
        while (nextPageToken != null) {
            try {
                response = PlacesApi.nearbySearchQuery(context, loc)
                        .radius(1500)
                        .type(PlaceType.RESTAURANT)
                        .language("en")
                        .pageToken(nextPageToken)
                        .await();
                getRestaurantsDataFromResponse(response.results, context, cuisine);
                nextPageToken = response.nextPageToken;
            } catch (InvalidRequestException e) {
                break;
            }
        }
    }

    private void getRestaurantsDataFromResponse(PlacesSearchResult[] res, GeoApiContext context, String cuisine) {
        for (int i = 0; i < res.length; i++) {
            PlacesSearchResult r = res[i];
            Object existsObject = restaurantDbRepository.findByPlace_id(r.placeId);
            if(existsObject == null) {
                Restaurant restaurant = getItem(r, context, cuisine);
                restaurantDbRepository.save(restaurant);
            }
        }
    }

    public List<String> getCuisineTypeFromJson() {
        List<String> cuisineTypeList = new ArrayList<>();
        String path = "src/main/resources/json_data/cuisine_types.json";
        try {
            String content = "";
            content = new String(Files.readAllBytes(Paths.get(path)));
            JSONArray jsonArray = new JSONArray(content);
            JSONObject jsonObject = jsonArray.getJSONObject(0);
            JSONArray cuisineTypesArray = jsonObject.getJSONArray("cuisine_types");
            for (int i = 0; i < cuisineTypesArray.length(); i++) {
                cuisineTypeList.add(cuisineTypesArray.getString(i));
            }
        }catch (IOException e){
            System.err.println("Немає доступу до файлу: " + path);
        }
        return cuisineTypeList;
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

    private Restaurant getItem(PlacesSearchResult restaurantResult, GeoApiContext context, String cuisine){
        Restaurant restaurant = new Restaurant();
        restaurant.setPlace_id(restaurantResult.placeId);
        restaurant.setName(restaurantResult.name);
        restaurant.setAddress(restaurantResult.vicinity);
        restaurant.setLatitude(restaurantResult.geometry.location.lat);
        restaurant.setLongitude(restaurantResult.geometry.location.lng);
        restaurant.setRating((double) restaurantResult.rating);
        restaurant.setCuisine_type(cuisine);
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
            List<Restaurant> restaurants = objectMapper.readValue(new File("src/main/resources/json_data/restaurants.json"),
                    new TypeReference<List<Restaurant>>() {});

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
