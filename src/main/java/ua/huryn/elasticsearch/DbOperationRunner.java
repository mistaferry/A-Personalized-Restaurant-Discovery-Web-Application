package ua.huryn.elasticsearch;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import ua.huryn.elasticsearch.repository.db.CategoryDbRepository;
import ua.huryn.elasticsearch.repository.db.RestaurantDbRepository;
import ua.huryn.elasticsearch.service.RestaurantService;

@Component
public class DbOperationRunner implements CommandLineRunner {
    @Autowired
    RestaurantDbRepository restaurantDbRepository;
    @Autowired
    CategoryDbRepository categoryDbRepository;
    private final RestaurantService restaurantService;

    public DbOperationRunner(RestaurantService restaurantService) {
        this.restaurantService = restaurantService;
    }

    @Override
    public void run(String... args){
        try {
            restaurantService.addDataToDb();
//            restaurantService.addApiDataToFile();
            System.out.println("Всі дані додано!");
        } catch (Exception e) {
//            System.err.println("Неможливо отримани дані за допомогою Api.\n" +
//                    "Будемо отримувати дані з файла");
//            e.printStackTrace();
//            restaurantService.addDataFromFileToDb();
        }
    }
}

