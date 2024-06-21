package ua.huryn.elasticsearch.view;

import com.vaadin.flow.component.applayout.AppLayout;
import com.vaadin.flow.component.applayout.DrawerToggle;
import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.sidenav.SideNavItem;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.core.OAuth2AuthenticatedPrincipal;
import ua.huryn.elasticsearch.entity.db.User;
import ua.huryn.elasticsearch.repository.db.UserDbRepository;

public class MainView extends AppLayout {
    private final UserDbRepository userDbRepository;

    public MainView(UserDbRepository userDbRepository) {
        this.userDbRepository = userDbRepository;
        createHeader();
    }

    private void createHeader() {
        H1 logo = new H1("Foodie Finds");
        var header = new HorizontalLayout(new DrawerToggle(), logo);
        addToNavbar(header);

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        OAuth2AuthenticatedPrincipal principal = (OAuth2AuthenticatedPrincipal) authentication.getPrincipal();
        getRightView(principal);
    }

    private void getRightView(OAuth2AuthenticatedPrincipal principal) {
        String email = principal.getAttribute("email");
        User existingUser = this.userDbRepository.findByEmail(email).orElse(null);

        if(existingUser != null){
            if(!existingUser.getRole().getName().equals("admin")){
                SideNavItem dashboardLink = new SideNavItem("Меню", MenuView.class, VaadinIcon.COFFEE.create());
                SideNavItem logout = new SideNavItem("Вихід", LogoutView.class, VaadinIcon.USER.create());
                addToDrawer(dashboardLink, logout);
            }else{
                SideNavItem addInfo = new SideNavItem("Додати інформацію", AdminView.class, VaadinIcon.ADD_DOCK.create());
                SideNavItem editInfo = new SideNavItem("Змінити інформацію", AdminEdit.class, VaadinIcon.EDIT.create());
                SideNavItem deleteInfo = new SideNavItem("Видалити інформацію", AdminDelete.class, VaadinIcon.DEL.create());
                SideNavItem logout = new SideNavItem("Вихід", LogoutView.class, VaadinIcon.USER.create());
                addToDrawer(addInfo, editInfo, deleteInfo, logout);
            }
        }
    }
}
