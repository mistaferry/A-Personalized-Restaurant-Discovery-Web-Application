package ua.huryn.elasticsearch.view;

import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.html.Anchor;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.server.auth.AnonymousAllowed;

@Route("login")
@PageTitle("Login")
@AnonymousAllowed
public class LoginView extends VerticalLayout {
    // URL that Spring Security uses to connect to Google services
    private static final String OAUTH_URL = "/oauth2/authorization/google";

    public LoginView() {
        Anchor loginLink = new Anchor(OAUTH_URL, "Login with Google");
        // Instruct Vaadin Router to ignore doing SPA handling
        loginLink.setRouterIgnore(true);
        add(loginLink);
    }
}
