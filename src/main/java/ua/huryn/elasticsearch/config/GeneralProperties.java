package ua.huryn.elasticsearch.config;


import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.Arrays;

@Slf4j
@Component
@Getter
public class GeneralProperties {
    @Value("${startup.add-data}")
    private boolean addDateOnStartup;
    @Value("${google.api-key}")
    private String googleApiKey;
    @Value("${app.local-directory}")
    private String localDirectory;
    @Value("${app.cuisine-types}")
    private String[] cuisineTypes;

    @PostConstruct
    private void postConstruct() {
        log.info("load data on startup - {}", addDateOnStartup);
        if (addDateOnStartup && googleApiKey.isEmpty()) {
            log.warn("You are going to import data, but apiKey is empty. You can't import data from Google this way.");
        }
        log.info("cuisine types - {}", Arrays.toString(cuisineTypes));
    }
}
