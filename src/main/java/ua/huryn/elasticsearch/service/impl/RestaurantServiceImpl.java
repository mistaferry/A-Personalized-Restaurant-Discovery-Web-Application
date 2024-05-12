package ua.huryn.elasticsearch.service.impl;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.maps.*;
import com.google.maps.errors.ApiException;
import com.google.maps.errors.InvalidRequestException;
import com.google.maps.model.*;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.elasticsearch.index.query.BoolQueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;

import org.springframework.data.elasticsearch.core.SearchHits;
import org.springframework.data.elasticsearch.core.SearchHit;
import org.jetbrains.annotations.NotNull;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.elasticsearch.core.ElasticsearchOperations;

import org.springframework.data.elasticsearch.core.query.StringQuery;
import org.springframework.stereotype.Service;
import ua.huryn.elasticsearch.entity.db.*;
import ua.huryn.elasticsearch.entity.dto.DishDTO;
import ua.huryn.elasticsearch.entity.dto.IngredientDTO;
import ua.huryn.elasticsearch.entity.dto.RestaurantDTO;
import ua.huryn.elasticsearch.config.BootstrapProperties;
import ua.huryn.elasticsearch.entity.model.RestaurantModel;
import ua.huryn.elasticsearch.repository.db.*;
import ua.huryn.elasticsearch.service.RestaurantService;
import ua.huryn.elasticsearch.utils.Convertor;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.sql.Timestamp;
import java.util.*;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

@Service
@Slf4j
@RequiredArgsConstructor
public class RestaurantServiceImpl implements RestaurantService {
    private final RestaurantDbRepository restaurantDbRepository;
    private final DishDbRepository dishDbRepository;
    private final CategoryDbRepository categoryDbRepository;
    private final RestaurantInfoEnDbRepository restaurantInfoEnDbRepository;
    private final IngredientDbRepository ingredientDbRepository;
    private final UserDbRepository userDbRepository;
    private final ReviewDbRepository reviewDbRepository;
    private final BootstrapProperties bootstrapProperties;
    @Autowired
    private ElasticsearchOperations elasticsearchOperations;

    @Override
    public List<RestaurantDTO> findByRating(double rating) {
        List<RestaurantDTO> ratingList = new ArrayList<>();

        double[] ratingRange = calculateRatingRange(rating);
        double startRating = ratingRange[0];
        double endRating = ratingRange[1];

        for (double i = startRating; i <= endRating; i += 0.1) {
            List<Restaurant> restaurantList = restaurantDbRepository.findByRating(i);
            ratingList.addAll(Convertor.convertRestaurantEntityListToDTO(restaurantList));
        }
        return ratingList;
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
    public RestaurantDTO findByRestaurantId(Long id) {
        Restaurant restaurant = restaurantDbRepository.findById(id);
        return Convertor.convertToDTO(restaurant);
    }

    @Override
    public List<RestaurantDTO> findByName(String name) {
        return Convertor.convertRestaurantEntityListToDTO(restaurantDbRepository.findByName(name));
    }

    @Override
    public List<RestaurantDTO> findByLatitudeAndLongitude(double latitude, double longitude) {
        return Convertor.convertRestaurantEntityListToDTO(restaurantDbRepository.findByLatitudeAndLongitude(latitude, longitude));
    }

    @Override
    public List<RestaurantDTO> findByPrice_level(int priceLevel) {
        return Convertor.convertRestaurantEntityListToDTO(restaurantDbRepository.findByPriceLevel(priceLevel));
    }

    @Override
    public List<RestaurantDTO> findByCuisineType(String cuisineType) {
        return Convertor.convertRestaurantEntityListToDTO(restaurantDbRepository.findByCuisineType(cuisineType));
    }

    @Override
    public List<RestaurantDTO> findByRatingAndPrice_level(double rating, int priceLevel) {
        List<RestaurantDTO> restaurantModelList = new ArrayList<>();
        double[] ratingRange = calculateRatingRange(rating);
        double startRating = ratingRange[0];
        double endRating = ratingRange[1];

        for (double i = startRating; i <= endRating; i += 0.1) {
            List<RestaurantDTO> list = Convertor.convertRestaurantEntityListToDTO(restaurantDbRepository.findByRatingAndPriceLevel(i, priceLevel));
            restaurantModelList.addAll(list);
        }
        return restaurantModelList;
    }

    @Override
    public List<RestaurantDTO> getAll() {
        return Convertor.convertRestaurantEntityListToDTO(restaurantDbRepository.getAll());
    }

    public List<Ingredient> getAllIngredientsByRestaurantId(Long id){
        Restaurant restaurant = restaurantDbRepository.findById(id);
        List<Dish> dishes = restaurant.getDishes();
        List<Ingredient> ingredients = new ArrayList<>();
        for (Dish dish: dishes){
            ingredients.addAll(dish.getIngredients());
        }
        return ingredients;
    }

    private /*static*/ GeoApiContext getGeoApiContext() throws IOException {
        return new GeoApiContext.Builder()
                .apiKey(bootstrapProperties.getApiKey())
                .build();
    }

    private List<RestaurantDTO> getRestaurantsByAddress(GeoApiContext context, String address, String numValue, List<RestaurantDTO> filtered, List<TravelMode> modes){
        List<RestaurantDTO> restaurantModelList = new ArrayList<>();
        try {
            for (TravelMode mode: modes){
                for (RestaurantDTO restaurant : filtered) {
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

    @Override
    public List<RestaurantDTO> getFiltered(List<String> cuisineTypes, List<Integer> rating, List<Integer> price, List<Integer> routes, List<String> dishes, List<String> ingredients, String routeDeparturePoint, String fullTextSearch) {
        List<RestaurantDTO> filteredData = Convertor.convertRestaurantEntityListToDTO(restaurantDbRepository.getAll());

        filteredData = getRestaurantsDTOBySearchString(fullTextSearch, filteredData);
        filteredData = filteredByCuisineType(cuisineTypes, filteredData);
        filteredData = filteredByRating(rating, filteredData);
        filteredData = filteredByPriceLevel(price, filteredData);
        filteredData = getRestaurantInGivenDistance(routeDeparturePoint, routes, filteredData);
        filteredData = filteredByDishes(dishes, filteredData);
        filteredData = filteredByIngredients(ingredients, filteredData);

//        System.out.println("filtered size - " + filteredData.size());
        return filteredData;
    }

    private List<RestaurantDTO> filteredByCuisineType(List<String> cuisineTypes, List<RestaurantDTO> filteredData){
        if(cuisineTypes != null && !cuisineTypes.isEmpty()){
            List<RestaurantDTO> filtered = new ArrayList<>();
            for (String cuisine: cuisineTypes){
                for(RestaurantDTO restaurantModel: filteredData) {
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

    private List<RestaurantDTO> filteredByRating(List<Integer> rating, List<RestaurantDTO> filteredData) {
        if(rating != null && !rating.isEmpty()){
            List<RestaurantDTO> filtered = new ArrayList<>();
            for (Integer rate: rating){
                filtered.addAll(findByRating(rate));
            }
            return filtered;
        }else {
            return filteredData;
        }
    }

    private List<RestaurantDTO> filteredByPriceLevel(List<Integer> price, List<RestaurantDTO> filteredData) {
        if(price != null && !price.isEmpty()){
            List<RestaurantDTO> filtered = new ArrayList<>();
            for (Integer priceLevel: price){
                for(RestaurantDTO restaurantModel: filteredData){
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

    @Override
    public List<RestaurantDTO> getRestaurantInGivenDistance(String firstPoint, List<Integer> routes, List<RestaurantDTO> filtered) {
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
                    filtered = getRestaurantsByAddress(context, location, numValue, filtered, modes);
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
        }
        return filtered;
    }

    private List<RestaurantDTO> filteredByDishes(List<String> dishes, List<RestaurantDTO> filteredData){
        if(dishes != null && !dishes.isEmpty()){
            List<RestaurantDTO> filtered = new ArrayList<>();
            for (String dish: dishes){
                for(RestaurantDTO restaurantModel: filteredData) {
                    List<Dish> dishesListByRestaurantId = dishDbRepository.findByRestaurantId(restaurantModel.getRestaurantId());
                    List<DishDTO> restaurantDishes = Convertor.convertDishEntityListToDTO(dishesListByRestaurantId);
                    for (DishDTO dishDTO : restaurantDishes) {
                        if (dishDTO.getName().equals(dish)) {
                            filtered.add(restaurantModel);
                        }
                    }
                }
            }
            return filtered;
        }else{
            return filteredData;
        }
    }

    private List<RestaurantDTO> filteredByIngredients(List<String> ingredients, List<RestaurantDTO> filteredData){
        if(ingredients != null && !ingredients.isEmpty()){
            List<RestaurantDTO> filtered = new ArrayList<>();
            for (RestaurantDTO restaurantModel : filteredData) {
                List<Dish> dishes = dishDbRepository.findByRestaurantId(restaurantModel.getRestaurantId());
                boolean matches = true;

                for (Dish dish : dishes) {
                    List<Ingredient> ingredientsListByDishId = ingredientDbRepository.findIngredientsByDishId(dish.getId()).get();
                    List<IngredientDTO> ingredientsList = Convertor.convertIngredientEntityListToDTO(ingredientsListByDishId);

                    for (IngredientDTO ingredientName : ingredientsList) {
                        if (ingredients.contains(ingredientName.getName())) {
                            matches = false;
                            break;
                        }
                    }

                    if (!matches) {
                        break;
                    }
                }

                if (matches) {
                    filtered.add(restaurantModel);
                }
            }
            return filtered;
        }else{
            return filteredData;
        }
    }

    @Override
    public void addDataToDb() throws IOException, InterruptedException, ApiException {
        List<LatLng> locationsList = getLocationFromJson();
        List<String> cuisineList = getCuisineTypeFromJson();
        cuisineList = cuisineList.subList(1,2);
        GeoApiContext context = getGeoApiContext();

        log.info("Received information about {} cuisines and {} locations", cuisineList.size(), locationsList.size());

        for (String cuisine: cuisineList){
            for (LatLng loc: locationsList){
                PlacesSearchResponse response = PlacesApi.nearbySearchQuery(context, loc)
                        .radius(3500)
                        .type(PlaceType.RESTAURANT)
                        .keyword(cuisine)
                        .language("ua")
                        .await();

                PlacesSearchResult[] placesSearchResults = response.results;
                saveDataFromResponseToDb(placesSearchResults, context, cuisine);
//                getRestaurantsDataFromResponseWithNextPageToken(loc, response, context, cuisine);
            }
        }
//        getRestaurantsNearby(locationsList, context);
    }

    private void getRestaurantsNearby(List<LatLng> locationsList, GeoApiContext context) throws ApiException, InterruptedException, IOException {
        for (LatLng loc: locationsList){
            PlacesSearchResponse response = PlacesApi.nearbySearchQuery(context, loc)
                    .radius(3500)
                    .type(PlaceType.RESTAURANT)
                    .language("ua")
                    .await();

            PlacesSearchResult[] placesSearchResults = response.results;
            saveDataFromResponseToDb(placesSearchResults, context, "");
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
                        .language("ua")
                        .pageToken(nextPageToken)
                        .await();
                saveDataFromResponseToDb(response.results, context, cuisine);
                nextPageToken = response.nextPageToken;
            } catch (InvalidRequestException e) {
                break;
            }
        }
    }

    private void saveDataFromResponseToDb(PlacesSearchResult[] arrayOfResults, GeoApiContext context, String cuisine) {
        log.info("Received info of {} restaurants", arrayOfResults.length);
        int countOfProcessedRestaurants = 0;
        int countOfSavedRestaurants = 0;
        for (PlacesSearchResult result : arrayOfResults) {
            log.debug("one result from search - {}", result);
            Restaurant existsObject = restaurantDbRepository.findByPlaceId(result.placeId);
            if (existsObject == null) {
                log.debug("The restaurant wasn't find. Let's add it");
                Restaurant restaurant = getRestaurantData(result, context, cuisine);
                log.debug("Add restaurant: {}", restaurant);
                restaurantDbRepository.save(restaurant);

                saveRestaurantReviews(restaurant, restaurant.getPlaceId(), context);
                countOfSavedRestaurants++;
            }
            countOfProcessedRestaurants ++;
            if(countOfProcessedRestaurants%100 == 0) {
                log.info("Processed {} restaurants, {} were saved", countOfProcessedRestaurants, countOfSavedRestaurants);
            }
        }
    }

    public List<String> getCuisineTypeFromJson() {
        List<String> cuisineTypeList = new ArrayList<>();
        String path = "src/main/resources/db_data/cuisine_types.json";
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
            System.err.println("No access to file: " + path);
        }
        return cuisineTypeList;
    }

    private List<LatLng> getLocationFromJson() {
        List<LatLng> locationsList = new ArrayList<>();
        String path = "src/main/resources/db_data/locations.json";
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
            log.error("No access to file: " + path);
        }
        return locationsList;
    }

    private Restaurant getRestaurantData(PlacesSearchResult restaurantResult, GeoApiContext context, String cuisine){
        Restaurant restaurant = new Restaurant();
        restaurant.setPlaceId(restaurantResult.placeId);
        restaurant.setName(restaurantResult.name);
        restaurant.setAddress(restaurantResult.vicinity);
        restaurant.setLatitude(restaurantResult.geometry.location.lat);
        restaurant.setLongitude(restaurantResult.geometry.location.lng);
        restaurant.setRating((double) restaurantResult.rating);
        restaurant.setCuisineType(cuisine);
        Photo[] photos = restaurantResult.photos;
        if(photos != null) {
            restaurant.setPhotoRef(setRestaurantImagePath(context, restaurant, restaurantResult));
        }else{
            restaurant.setPhotoRef(null);
        }
        setPriceLevelAndWebsite(restaurantResult, restaurant, context);
        restaurant.setCategories(getCategories(restaurantResult));
        restaurant.setDishes(getDishes(cuisine));
        return restaurant;
    }

    public Image getRestaurantImage(RestaurantDTO restaurantModel){
        String path = restaurantModel.getPhotoRef();
        try {
            File imageFile = new File(path);
            if (imageFile.exists()) {
                return ImageIO.read(imageFile);
            } else {
                return null;
            }
        } catch (IOException e) {
            return null;
        }
    }

    private String setRestaurantImagePath(GeoApiContext context, Restaurant restaurant, PlacesSearchResult restaurantResult){
        try {
            Photo[] photos = restaurantResult.photos;
            ImageResult photo = null;
            photo = new PhotoRequest(context)
                    .photoReference(photos[0].photoReference)
                    .maxWidth(photos[0].width)
                    .maxHeight(photos[0].height)
                    .await();
            byte[] imageData = photo.imageData;

            String parentDirectory = "src/main/resources/";
            String outputDirPath = parentDirectory + "db_data/restaurant_images";
            File outputDir = new File(outputDirPath);

            if (!outputDir.exists()) {
                outputDir.mkdirs();
            }

            String imagePath = "db_data/restaurant_images/" + restaurant.getName().toLowerCase() + "_image.jpg";
            String path = parentDirectory + imagePath;
            try (FileOutputStream fos = new FileOutputStream(path)) {
                fos.write(imageData);
            } catch (IOException e) {
                log.error("Cannot save image for placeId - " + restaurant.getPlaceId());
            }

            return imagePath;
        } catch (ApiException | InterruptedException | IOException e) {
            throw new RuntimeException(e);
        }
    }

    private void setPriceLevelAndWebsite(PlacesSearchResult restaurantResult, Restaurant restaurant, GeoApiContext context) {
        try {
            PlaceDetails details = PlacesApi.placeDetails(context, restaurantResult.placeId).language("en").await();
            PriceLevel priceLev = details.priceLevel;
            if (priceLev != null) {
                restaurant.setPriceLevel(priceLev.ordinal());
            } else {
                restaurant.setPriceLevel(0);
            }
            String website = String.valueOf(details.website);
            if(website.length() < 256) {
                restaurant.setWebsite(website);
            }


            RestaurantInfoEn restaurantInfoEn = new RestaurantInfoEn();
            restaurantInfoEn.setRestaurantId(restaurant.getId());
            restaurantInfoEn.setNameEn(details.name);
            restaurantInfoEn.setAddressEn(details.vicinity);
            restaurantInfoEnDbRepository.save(restaurantInfoEn);

        } catch (Exception e) {
            log.error("Unable to retrieve restaurant data from placeId: " + restaurantResult.placeId);
        }
    }

    private void saveRestaurantReviews(Restaurant restaurant, String placeId, GeoApiContext context){
        PlaceDetails details = null;
        try {
            details = PlacesApi.placeDetails(context, placeId).language("en").await();
            PlaceDetails.Review[] placeReviews =  details.reviews;
            if(placeReviews != null){
                for(PlaceDetails.Review review: placeReviews){
                    Review placeReview = new Review();
                    User user = (userDbRepository.findById(1L)).get();
                    placeReview.setText(review.text);
                    placeReview.setTime(Timestamp.from(review.time));
                    placeReview.setUser(user);
                    placeReview.setRestaurant(restaurant);
                    reviewDbRepository.save(placeReview);
                }
            }
        } catch (ApiException | InterruptedException | IOException e) {
            throw new RuntimeException(e);
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


    private List<Dish> getDishes(String cuisineType){
        return dishDbRepository.findByCuisineType(cuisineType);
    }

    @Override
    public void addApiDataToFile(){
        try {
            List<Restaurant> restaurants = restaurantDbRepository.findAll();

            ObjectMapper mapper = new ObjectMapper();

            File file = new File("src/main/resources/db_data/restaurants.json");

            mapper.writeValue(file, restaurants);
        } catch (Exception e) {
            log.error("Cannot save data to a file");
        }
    }

    @Override
    public void addDataFromFileToDb(){
        ObjectMapper objectMapper = new ObjectMapper();

        try {
            List<Restaurant> restaurants = objectMapper.readValue(new File("src/main/resources/db_data/restaurants.json"),
                    new TypeReference<List<Restaurant>>() {});

            for (Restaurant restaurant : restaurants) {
                log.info("restaurant: {}", restaurant);
                List<Category> categories = restaurant.getCategories();
                for (Category category: categories){
                    log.info("category: {}", category);
                    Category existsCategory = categoryDbRepository.findByName(category.getName());
                    if(existsCategory == null){
                        categoryDbRepository.save(category);
                    }
                }
            }

            restaurantDbRepository.saveAll(restaurants);
            log.info("Data was added from file");
        } catch (IOException e) {
            log.error("There was a problem getting data from file: {}", e.getMessage());
        }
    }

    @Override
    public List<RestaurantModel> findRestaurantsBySearchString(List<String> parts) {
        try {

            BoolQueryBuilder boolQuery = QueryBuilders.boolQuery();
            for (String part : parts) {
                boolQuery.must(QueryBuilders.wildcardQuery("search_string", "*" + part + "*"));
            }

            String queryString = boolQuery.toString();
            StringQuery stringQuery = new StringQuery(queryString);

            SearchHits<RestaurantModel> searchHits = elasticsearchOperations.search(
                    stringQuery, RestaurantModel.class);

            return searchHits.stream()
                    .map(SearchHit::getContent)
                    .collect(Collectors.toList());
        } catch (Exception e) {
            log.error("Error during search: " + e.getMessage());
        }
        return null;
    }

    @Override
    public List<RestaurantDTO> getRestaurantsDTOBySearchString(String searchString, List<RestaurantDTO> restaurantList){
        if(searchString != null && !searchString.isBlank()){
            searchString = searchString.toLowerCase();
            String[] words = searchString.split("\\s+");
            List<String> wordList = Arrays.asList(words);

            List<RestaurantModel> list = findRestaurantsBySearchString(wordList);
            List<Restaurant> entityList = new ArrayList<>();
            for (RestaurantModel restaurantModel : list) {
                entityList.add(restaurantDbRepository.findById(restaurantModel.getRestaurantId()));
            }
//            System.out.println("parts - " + searchString);
//            System.out.println("size - " + entityList.size());
            return Convertor.convertRestaurantEntityListToDTO(entityList);
        }
        return restaurantList;
    }
}
