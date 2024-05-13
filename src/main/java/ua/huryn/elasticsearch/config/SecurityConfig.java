package ua.huryn.elasticsearch.config;
import com.vaadin.flow.spring.security.VaadinWebSecurity;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;

@EnableWebSecurity
@Configuration
public class SecurityConfig extends VaadinWebSecurity {

    private static final String LOGIN_URL = "/oauth2/authorization/google";

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        super.configure(http);
        http.oauth2Login((oauth2Login) -> oauth2Login
                        .loginPage(LOGIN_URL).permitAll()
                );
    }
}