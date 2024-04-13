package ua.huryn.elasticsearch;

import com.google.maps.errors.ApiException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import ua.huryn.elasticsearch.repository1.CategoryDbRepository;
import ua.huryn.elasticsearch.repository1.RestaurantDbRepository;
import ua.huryn.elasticsearch.service.RestaurantService;
import ua.huryn.elasticsearch.service.impl.RestaurantServiceImpl;

@Component
public class DbOperationRunner implements CommandLineRunner {
    @Autowired
    RestaurantDbRepository restaurantDbRepository;
    @Autowired
    CategoryDbRepository categoryDbRepository;
    @Autowired
    RestaurantService restaurantService = new RestaurantServiceImpl();

    @Override
    public void run(String... args){
        try {
            restaurantService.addDataToDb();
            restaurantService.addApiDataToFile();
        } catch (Exception e) {
            System.err.println("Неможливо отримани дані за допомогою Api.\n" +
                    "Будемо отримувати дані з файла");
//            e.printStackTrace();
        restaurantService.addDataFromFileToDb();
        }
    }
}

