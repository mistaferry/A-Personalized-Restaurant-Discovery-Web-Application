package ua.huryn.elasticsearch;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import ua.huryn.elasticsearch.repository.RestaurantRepository;

@SpringBootApplication
public class ElasticsearchApplication {
	public static void main(String[] args) {
		SpringApplication.run(ElasticsearchApplication.class, args);
	}
}
