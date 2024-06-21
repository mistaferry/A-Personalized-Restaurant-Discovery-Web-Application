package ua.huryn.elasticsearch.view;

import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.dependency.CssImport;
import com.vaadin.flow.component.dependency.StyleSheet;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import jakarta.annotation.security.PermitAll;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.core.OAuth2AuthenticatedPrincipal;
import ua.huryn.elasticsearch.entity.db.User;
import ua.huryn.elasticsearch.repository.db.UserDbRepository;

@PageTitle("Login")
@Route(value = "", layout = MainView.class)
@CssImport("styles.css")
@PermitAll
@StyleSheet("https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css")
public class LoginView extends VerticalLayout {
    private final UserDbRepository userDbRepository;
    private String USER_MAIN_PAGE = "/user/menu";
    private String ADMIN_MAIN_PAGE = "/admin/add";

    public LoginView(UserDbRepository userDbRepository) {
        this.userDbRepository = userDbRepository;
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        OAuth2AuthenticatedPrincipal principal = (OAuth2AuthenticatedPrincipal) authentication.getPrincipal();

        redirectToUserPage(principal);
    }

    private void redirectToUserPage(OAuth2AuthenticatedPrincipal principal) {
        String email = principal.getAttribute("email");
        User existingUser = this.userDbRepository.findByEmail(email).orElse(null);

        if(existingUser != null){
            if(existingUser.getRole().getName().equals("admin")){
                UI.getCurrent().getPage().setLocation(ADMIN_MAIN_PAGE);
            }else{
                UI.getCurrent().getPage().setLocation(USER_MAIN_PAGE);
            }
        }
    }
}