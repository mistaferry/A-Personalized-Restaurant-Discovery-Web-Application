package ua.huryn.elasticsearch.service.impl;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.maps.*;
import com.google.maps.errors.ApiException;
import com.google.maps.errors.InvalidRequestException;
import com.google.maps.model.*;
import lombok.extern.slf4j.Slf4j;
import org.elasticsearch.index.query.BoolQueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;

import org.jetbrains.annotations.NotNull;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.data.elasticsearch.core.ElasticsearchOperations;
import org.springframework.data.domain.PageRequest;

import org.springframework.data.elasticsearch.core.SearchHits;
import org.springframework.data.elasticsearch.core.SearchHit;
import org.springframework.data.elasticsearch.core.query.StringQuery;
import org.springframework.stereotype.Service;
import ua.huryn.elasticsearch.config.GeneralProperties;
import ua.huryn.elasticsearch.entity.db.*;
import ua.huryn.elasticsearch.entity.dto.RestaurantDTO;
import ua.huryn.elasticsearch.entity.dto.ReviewDTO;
import ua.huryn.elasticsearch.entity.model.RestaurantModel;
import ua.huryn.elasticsearch.entity.model.ReviewModel;
import ua.huryn.elasticsearch.repository.db.*;
import ua.huryn.elasticsearch.service.DishService;
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
public class RestaurantServiceImpl implements RestaurantService {
    private final RestaurantDbRepository restaurantDbRepository;
    private final DishDbRepository dishDbRepository;
    private final CategoryDbRepository categoryDbRepository;
    private final RestaurantInfoEnDbRepository restaurantInfoEnDbRepository;
    private final IngredientDbRepository ingredientDbRepository;
    private final UserDbRepository userDbRepository;
    private final ReviewDbRepository reviewDbRepository;
    private final DishService dishService;
//    private final RestaurantRepository restaurantRepository;
    private final GeneralProperties generalProperties;
    private final ElasticsearchOperations elasticsearchOperations;
    private final String localDirectory;

    public RestaurantServiceImpl(RestaurantDbRepository restaurantDbRepository, DishDbRepository dishDbRepository, CategoryDbRepository categoryDbRepository, RestaurantInfoEnDbRepository restaurantInfoEnDbRepository, IngredientDbRepository ingredientDbRepository, UserDbRepository userDbRepository, ReviewDbRepository reviewDbRepository, DishService dishService, /*RestaurantRepository restaurantRepository,*/ GeneralProperties generalProperties, ElasticsearchOperations elasticsearchOperations) {
        this.dishDbRepository = dishDbRepository;
        this.restaurantDbRepository = restaurantDbRepository;
        this.categoryDbRepository = categoryDbRepository;
        this.restaurantInfoEnDbRepository = restaurantInfoEnDbRepository;
        this.ingredientDbRepository = ingredientDbRepository;
        this.userDbRepository = userDbRepository;
        this.reviewDbRepository = reviewDbRepository;
        this.dishService = dishService;
//        this.restaurantRepository = restaurantRepository;
        this.generalProperties = generalProperties;
        this.localDirectory=generalProperties.getLocalDirectory();
        this.elasticsearchOperations = elasticsearchOperations;
    }

    @Override
    public List<RestaurantDTO> findByRating(double rating) {

        double[] ratingRange = calculateRatingRange(rating);
        double startRating = ratingRange[0];
        double endRating = ratingRange[1];

        List<Restaurant> restaurantList = restaurantDbRepository.findByRating(startRating, endRating);

        return Convertor.convertRestaurantEntityListToDTO(restaurantList);
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
        List<Category> categories = categoryDbRepository.findByRestaurantId(restaurant.getId());
        restaurant.setCategories(categories);
        setAllRestaurantListsData(restaurant);
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
    public List<RestaurantDTO> findByNameAndAddress(String value) {
        String[] values = value.split(", ");
        List<RestaurantDTO> restaurantByName = findByName(values[0]);

        return List.of();
    }

    @Override
    public List<RestaurantDTO> findByAddress(String address) {
//        List<Restaurant> restaurants = restaurantDbRepository
        return List.of();
    }

    @Override
    public List<RestaurantDTO> getAll() {
        List<Restaurant> restaurants = restaurantDbRepository.getAll();
        for (Restaurant restaurant: restaurants) {
            setAllRestaurantListsData(restaurant);
        }
        return Convertor.convertRestaurantEntityListToDTO(restaurants);
    }

    @Override
    public void setAllRestaurantListsData(Restaurant restaurant) {
        List<Dish> dishes = dishDbRepository.findByRestaurantId(restaurant.getId());
        for (Dish dish: dishes){
            List<Ingredient> ingredients = ingredientDbRepository.findIngredientsByDishId(dish.getId()).orElse(null);
            dish.setIngredients(ingredients);
        }
        restaurant.setDishes(dishes);
    }

    private GeoApiContext getGeoApiContext() throws IOException {
        return new GeoApiContext.Builder()
                .apiKey(generalProperties.getGoogleApiKey())
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
                        long distanceInMeters = ((distanceMatrix.rows[0]).elements[0]).distance.inMeters;
                        int distanceInKm = (int) Math.round(distanceInMeters * 0.001);
                        int givenValue = Integer.parseInt(numValue);
                        if (distanceInKm <= givenValue) {
                            restaurantModelList.add(restaurant);
                        }
                    }else {
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
    public List<RestaurantDTO> getFiltered(List<RestaurantDTO> filteredData, List<String> cuisineTypes, List<Integer> rating, List<Integer> price, List<Integer> routes, List<String> dishes, List<String> ingredients, String routeDeparturePoint, String fullTextSearch, String keywords) {

        log.info("cuisine types - {}", cuisineTypes);
        log.info("rating - {}", rating);
        log.info("price - {}", price);
        log.info("routes - {}", routes);
        log.info("dishes - {}", dishes);
        log.info("ingredients - {}", ingredients);
        log.info("routeDeparturePoint - {}", routeDeparturePoint);
        filteredData = filteredByRating(rating, filteredData);
        filteredData = getRestaurantsDTOBySearchInEngAndUkr(fullTextSearch, filteredData);
        filteredData = getRestaurantsByReviewKeywords(keywords, filteredData);
        filteredData = filteredByCuisineType(cuisineTypes, filteredData);
        filteredData = filteredByPriceLevel(price, filteredData);
        filteredData = getRestaurantInGivenDistance(routeDeparturePoint, routes, filteredData);
        filteredData = filteredByDishes(dishes, filteredData);
        filteredData = filteredByIngredients(ingredients, filteredData);

//        log.info("{}",filteredData);
        log.info("filtered size - " + filteredData.size());
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
        if(routes != null && !routes.isEmpty() && firstPoint != null && !firstPoint.isEmpty() && !firstPoint.isBlank()) {

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

            try {
                String[] parts = firstPoint.split(",");
                GeoApiContext context = getGeoApiContext();
                String location = parts[0];
                String numValue = parts[1].replace(" ", "");
                filtered = getRestaurantsByAddress(context, location, numValue, filtered, modes);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }

        }
        return filtered;
    }

    private List<RestaurantDTO> filteredByDishes(List<String> dishes, List<RestaurantDTO> filteredData){
        if(dishes != null && !dishes.isEmpty()){
            List<RestaurantDTO> filtered = new ArrayList<>();
            Set<Long> addedRestaurantIds = new HashSet<>();

            for (String dish : dishes) {
                List<Restaurant> restaurantsByDish = restaurantDbRepository.findByDishName(dish);
                for (Restaurant restaurant : restaurantsByDish) {
                    Long restaurantId = restaurant.getId();
                    addedRestaurantIds.add(restaurantId);
                }
            }
            for(RestaurantDTO restaurantDTO: filteredData){
                for(Long id: addedRestaurantIds){
                    if (restaurantDTO.getRestaurantId().equals(id)){
                        filtered.add(restaurantDTO);
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
            Set<Long> addedRestaurantIds = new HashSet<>();

            for (String ingredient : ingredients) {
                List<Restaurant> restaurantsByDish = restaurantDbRepository.findRestaurantByIngredientName(ingredient).orElse(null);
                if(restaurantsByDish != null){
                    for (Restaurant restaurant: restaurantsByDish){
                        addedRestaurantIds.add(restaurant.getId());
                    }
                }
            }
//            log.info("set size - {}", addedRestaurantIds.size());
            for (RestaurantDTO restaurantDTO : filteredData) {
                if(!addedRestaurantIds.contains(restaurantDTO.getRestaurantId())){
                    filtered.add(restaurantDTO);
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
        List<String> cuisineList = getCuisineType();
        GeoApiContext context = getGeoApiContext();

        log.info("Received information about {} cuisines and {} locations", cuisineList.size(), locationsList.size());

        for (String cuisine: cuisineList){
            for (LatLng loc: locationsList){
                log.info("Get restaurants for location: {}", loc);
                PlacesSearchResponse response = PlacesApi.nearbySearchQuery(context, loc)
                        .radius(3500)
                        .type(PlaceType.RESTAURANT)
                        .keyword(cuisine)
                        .language("uk")
                        .await();

                PlacesSearchResult[] placesSearchResults = response.results;
//                saveDataFromResponseToDb(placesSearchResults, context, cuisine);
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
                    .language("uk")
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
                        .language("uk")
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

    public List<String> getCuisineType() {
        return Arrays.asList(generalProperties.getCuisineTypes());
    }

    private List<LatLng> getLocationFromJson() {
        List<LatLng> locationsList = new ArrayList<>();
        String path = localDirectory + "/db_data/locations.json";
        try {
            String content;
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
            ImageResult photo;
            photo = new PhotoRequest(context)
                    .photoReference(photos[0].photoReference)
                    .maxWidth(photos[0].width)
                    .maxHeight(photos[0].height)
                    .await();
            byte[] imageData = photo.imageData;

            String outputDirPath = localDirectory + "/db_data/restaurant_images";
            File outputDir = new File(outputDirPath);

            if (!outputDir.exists()) {
                outputDir.mkdirs();
            }
            String imagePath = "/db_data/restaurant_images/" + restaurantResult.placeId + "_image.jpg";
            String path = localDirectory + imagePath;
            Files.createDirectories(Paths.get(path).toAbsolutePath().getParent());
            try (FileOutputStream fos = new FileOutputStream(path)) {
                fos.write(imageData);
                log.debug("Image saved to: {}", path);
            } catch (IOException e) {
                log.error("Error saving image: {}", e.getMessage());
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

        } catch (Exception e) {
            log.error("Unable to retrieve restaurant data from placeId: " + restaurantResult.placeId);
        }
    }

    private void saveRestaurantReviews(Restaurant restaurant, String placeId, GeoApiContext context){
        try {
            PlaceDetails details = PlacesApi.placeDetails(context, restaurant.getPlaceId()).language("uk").await();
            if(details != null){
                PlaceDetails.Review[] placeReviews = details.reviews;
                if (placeReviews != null) {
                    for (PlaceDetails.Review review : placeReviews) {
                        Review placeReview = new Review();
                        User user = userDbRepository.findById(0L).orElse(null);
                        String text = review.text;
                        String truncatedText = text.substring(0, Math.min(text.length(), 255));
                        placeReview.setText(truncatedText);
//                        log.info(truncatedText);
                        placeReview.setTime(Timestamp.from(review.time));
                        placeReview.setUser(user);
                        placeReview.setRestaurant(restaurant);
                        reviewDbRepository.save(placeReview);
                    }
                }

                RestaurantInfoEn restaurantInfoEn = new RestaurantInfoEn();
                restaurantInfoEn.setRestaurantId(restaurant.getId());
                restaurantInfoEn.setNameEn(details.name);
                restaurantInfoEn.setAddressEn(details.vicinity);
                restaurantInfoEnDbRepository.save(restaurantInfoEn);
            }
        } catch (Exception e) {
            log.error("Unable to retrieve restaurant data from placeId: " + restaurant.getPlaceId());
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
            List<RestaurantDTO> restaurants = getAll();

            ObjectMapper mapper = new ObjectMapper();

            Files.createDirectories(Paths.get(localDirectory,"db_data"));

            File file = new File(localDirectory, "db_data/restaurants.json");

            mapper.writeValue(file, restaurants);
        } catch (Exception e) {
            log.error("Cannot save data to a file");
        }
    }

    @Override
    public void addDataFromFileToDb(){
        ObjectMapper objectMapper = new ObjectMapper();

        try {
            List<Restaurant> restaurants = objectMapper.readValue(new File(localDirectory, "db_data/restaurants.json"),
                    new TypeReference<>() {
                    });

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
                List<Dish> dishes = restaurant.getDishes();
                for(Dish dish : dishes){
                    Dish existsDish = dishDbRepository.findById(dish.getId()).orElse(null);
                    if(existsDish == null){
                        List<Ingredient> ingredients = dish.getIngredients();
                        for(Ingredient ingredient : ingredients){
                            Ingredient existsIngredient = ingredientDbRepository.findById(ingredient.getId()).orElse(null);
                            if(existsIngredient == null){
                                ingredientDbRepository.save(ingredient);
                            }
                        }
                        dish.setIngredients(ingredients);
                        dishDbRepository.save(dish);
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
    public List<ReviewModel> searchByReviewKeywords(List<String> parts) {
        BoolQueryBuilder boolQuery = QueryBuilders.boolQuery();

        if (parts != null && !parts.isEmpty()) {
            for (String part : parts) {
                if (!part.isBlank()) {
                    boolQuery.must(QueryBuilders.matchQuery("text", part));
                }
            }
        }

        String queryString = boolQuery.toString();
        StringQuery stringQuery = new StringQuery(queryString);

        stringQuery.setPageable(PageRequest.of(0, 3000));

        SearchHits<ReviewModel> searchHits = elasticsearchOperations.search(
                stringQuery, ReviewModel.class);


        return searchHits.stream()
                .map(SearchHit::getContent)
                .collect(Collectors.toList());

    }

    @Override
    public RestaurantDTO searchByRestaurantInfo(String info) {
        List<RestaurantDTO> restaurantDTOS = getAll();
        for (RestaurantDTO restaurant: restaurantDTOS){
            String string = restaurant.getName() + " " + restaurant.getAddress();
            if(string.equals(info)){
                return restaurant;
            }
        }
        return null;
    }

    public List<RestaurantDTO> getRestaurantsByReviewKeywords(String keywords, List<RestaurantDTO> filteredData){
        if(keywords != null && !keywords.isBlank()){
            List<ReviewDTO> foundReviews = getReviewsDTOByKeywords(keywords);

            Set<Long> restaurantsId = new HashSet<>();

            for(ReviewDTO review: foundReviews){
                restaurantsId.add(review.getRestaurantDTO().getRestaurantId());
            }
            List<RestaurantDTO> result = new ArrayList<>();

            for (RestaurantDTO restaurantDTO: filteredData){
                for (Long id: restaurantsId) {
                    if (restaurantDTO.getRestaurantId().equals(id)) {
                        result.add(restaurantDTO);
                    }
                }
            }
            return result;
        }
        return filteredData;
    }

    @Override
    public List<ReviewDTO> getReviewsDTOByKeywords(String searchString){
        searchString = searchString.toLowerCase();
        String[] words = searchString.split("\\s+");
        List<String> wordList = Arrays.asList(words);

        java.util.List<ReviewModel> list = searchByReviewKeywords(wordList);
        Set<Long> reviewsId = new HashSet<>();
        for (ReviewModel reviewModel: list){
            reviewsId.add(reviewModel.getReviewId());
        }
        List<Review> entityList = new ArrayList<>();
        for (Long id : reviewsId) {
            entityList.add(reviewDbRepository.findById(id).orElse(null));
        }
        return Convertor.convertReviewEntityListToDTO(entityList);
    }

    @Override
    public List<RestaurantModel> fullTextSearchInEngAndUkr(List<String> parts) {
        BoolQueryBuilder boolQuery = QueryBuilders.boolQuery();

        if (parts != null && !parts.isEmpty()) {
            for (String part : parts) {
                if (!part.isBlank()) {
                    boolQuery.should(QueryBuilders.wildcardQuery("search_string_ukr", "*" + part + "*"))
                            .should(QueryBuilders.wildcardQuery("search_string_eng", "*" + part + "*"));
                }
            }
        }

        String queryString = boolQuery.toString();
        StringQuery stringQuery = new StringQuery(queryString);
        stringQuery.setPageable(PageRequest.of(0, 3000));

        SearchHits<RestaurantModel> searchHits = elasticsearchOperations.search(
                stringQuery, RestaurantModel.class);

        return searchHits.stream()
                .map(SearchHit::getContent)
                .collect(Collectors.toList());
    }


    @Override
    public List<RestaurantDTO> getRestaurantsDTOBySearchInEngAndUkr(String searchString, List<RestaurantDTO> restaurantList){
        if(searchString != null && !searchString.isBlank()){
            searchString = searchString.toLowerCase();
            String[] words = searchString.split("\\s+");
            List<String> wordList = Arrays.asList(words);

            java.util.List<RestaurantModel> list = fullTextSearchInEngAndUkr(wordList);
            List<Restaurant> entityList = new ArrayList<>();
            for (RestaurantModel restaurantModel : list) {
                entityList.add(restaurantDbRepository.findById(restaurantModel.getRestaurantId()));
            }
            return Convertor.convertRestaurantEntityListToDTO(entityList);
        }
        return restaurantList;
    }
}
