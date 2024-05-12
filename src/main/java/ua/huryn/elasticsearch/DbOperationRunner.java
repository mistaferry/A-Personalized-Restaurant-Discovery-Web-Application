package ua.huryn.elasticsearch;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import ua.huryn.elasticsearch.config.BootstrapProperties;
import ua.huryn.elasticsearch.repository.db.CategoryDbRepository;
import ua.huryn.elasticsearch.repository.db.RestaurantDbRepository;
import ua.huryn.elasticsearch.service.RestaurantService;

@Component
@Slf4j
@AllArgsConstructor
public class DbOperationRunner implements CommandLineRunner {
    private final RestaurantService restaurantService;
    private final BootstrapProperties bootstrapProperties;

    @Override
    public void run(String... args) {
        if (bootstrapProperties.isAddDate()) {
            try {
                log.info("Start adding data from Google");
                log.debug("Add data to database");
                restaurantService.addDataToDb();
                log.debug("Add data to file");
                restaurantService.addApiDataToFile();
                log.info("Data from Google was added");
            } catch (Exception e) {
                log.error("Can't add data from Google. Let's try to add it from file");
//                restaurantService.addDataFromFileToDb();
//                log.info("Data from file was added");
            }
        }
    }
}
