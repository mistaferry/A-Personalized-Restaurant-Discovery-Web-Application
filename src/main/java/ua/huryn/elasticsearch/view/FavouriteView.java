package ua.huryn.elasticsearch.view;

import com.vaadin.flow.component.Text;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.sidenav.SideNav;
import com.vaadin.flow.component.sidenav.SideNavItem;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import ua.huryn.elasticsearch.MainView;

@Route(value = "favourite", layout = MainView.class)
@PageTitle("Favourite")
public class FavouriteView extends VerticalLayout {

    public FavouriteView() {
        add(new Text("Favourite"));
    }
}
