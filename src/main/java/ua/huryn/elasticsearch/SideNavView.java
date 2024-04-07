package ua.huryn.elasticsearch;

import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.sidenav.SideNav;
import com.vaadin.flow.component.sidenav.SideNavItem;

public class SideNavView {
    public SideNavView(){
        SideNav nav = new SideNav();

        SideNavItem homeLink = new SideNavItem("Home", MainView.class, VaadinIcon.DASHBOARD.create());
        SideNavItem menuLink = new SideNavItem("Menu", MenuView.class, VaadinIcon.ENVELOPE.create());

        nav.addItem(homeLink, menuLink);
    }
}
