//package ua.huryn.elasticsearch;
//
//import lombok.AllArgsConstructor;
//import lombok.extern.slf4j.Slf4j;
//import org.springframework.boot.CommandLineRunner;
//import org.springframework.stereotype.Component;
//import ua.huryn.elasticsearch.config.GeneralProperties;
//import ua.huryn.elasticsearch.service.RestaurantService;
//
//import java.util.Arrays;
//
//@Component
//@Slf4j
//@AllArgsConstructor
//public class DbOperationRunner implements CommandLineRunner {
//    private final RestaurantService restaurantService;
//    private final GeneralProperties generalProperties;
//
//    @Override
//    public void run(String... args) {
//        if (generalProperties.isAddDateOnStartup()) {
//            try {
//                log.info("Start adding data from Google");
//                log.debug("Add data to database");
//                restaurantService.addDataToDb();
//                log.debug("Add data to file");
//                restaurantService.addApiDataToFile();
//                log.info("Data from Google was added");
//            } catch (Exception e) {
//                log.error("Exception was thrown during adding data from Google", e);
//                log.info("Let's try to add it from file");
//                restaurantService.addDataFromFileToDb();
//                log.info("Data from file was added");
//            }
//        }
//    }
//}