package ua.huryn.elasticsearch.config;
import com.vaadin.flow.spring.security.VaadinWebSecurity;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.core.OAuth2AuthenticatedPrincipal;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import ua.huryn.elasticsearch.entity.db.Role;
import ua.huryn.elasticsearch.entity.db.User;
import ua.huryn.elasticsearch.repository.db.UserDbRepository;

@EnableWebSecurity
@Configuration
public class SecurityConfig extends VaadinWebSecurity {
    private UserDbRepository userDbRepository;
    private static final String LOGIN_URL = "/oauth2/authorization/google";

    public SecurityConfig(UserDbRepository userDbRepository) {
        this.userDbRepository = userDbRepository;
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        super.configure(http);
        http
                .oauth2Login((oauth2Login) -> oauth2Login
                        .loginPage(LOGIN_URL).permitAll()
                );

        /*http.authorizeHttpRequests(authorize -> {
            String role = getRole();
            if ("admin".equals(role)) {
                authorize.requestMatchers("/admin/**").hasRole("ADMIN");
            } else if ("user".equals(role)) {
                authorize.requestMatchers("/user/**").hasRole("USER");
            }
            authorize.anyRequest().authenticated();
        });*/
    }
/*
    private String getRole(){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        OAuth2AuthenticatedPrincipal principal = (OAuth2AuthenticatedPrincipal) authentication.getPrincipal();

        String email = principal.getAttribute("email");
        User existingUser = userDbRepository.findByEmail(email).orElse(null);
        return existingUser.getRole().getName();
    }*/
}