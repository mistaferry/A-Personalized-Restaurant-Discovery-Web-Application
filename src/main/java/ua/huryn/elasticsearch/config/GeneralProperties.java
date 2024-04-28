package ua.huryn.elasticsearch.config;


import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

@Slf4j
@Component
@Getter
public class BootstrapProperties {
    @Value("${bootstrap.addData}")
    private boolean addDate;
    @Value("${bootstrap.google.apiKey}")
    private String apiKey;

    @PostConstruct
    private void postConstruct() {
        log.info("load data on startup - {}", addDate);
        if (addDate && apiKey.isEmpty()) {
            log.warn("You are going to import data, but apiKey is empty. You can't import data from Google this way.");
        }
    }
}
