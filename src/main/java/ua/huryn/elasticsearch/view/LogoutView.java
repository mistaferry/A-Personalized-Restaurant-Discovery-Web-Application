package ua.huryn.elasticsearch.view;

import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.server.VaadinServletRequest;
import com.vaadin.flow.server.auth.AnonymousAllowed;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;

@Route("logout")
@PageTitle("Logout")
@AnonymousAllowed
public class LogoutView extends VerticalLayout {
    private static final String LOGOUT_URL = "/";

    public LogoutView() {
        UI.getCurrent().getPage().setLocation(LOGOUT_URL);
        SecurityContextLogoutHandler logoutHandler = new SecurityContextLogoutHandler();
        logoutHandler.logout(
                VaadinServletRequest.getCurrent().getHttpServletRequest(), null,
                null);
    }
}
