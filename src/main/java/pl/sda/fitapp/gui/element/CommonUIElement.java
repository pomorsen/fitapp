package pl.sda.fitapp.gui.element;

import com.vaadin.server.FileResource;
import com.vaadin.server.Page;
import com.vaadin.server.VaadinRequest;
import com.vaadin.ui.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.sda.fitapp.authorization.service.AuthService;
import pl.sda.fitapp.authorization.ui.PrivateComponent;

import java.io.File;

import static com.vaadin.ui.Alignment.MIDDLE_CENTER;
import static com.vaadin.ui.Alignment.MIDDLE_LEFT;

@Service
public class CommonUIElement{

    @Autowired
    private AuthService authService;

    public Layout displayHeader(Page page) {

        boolean isLogged = authService.isAuthenticated();

        // Horizontal panel which will hold 2 labels - logo + menu
        HorizontalLayout menuBarLayout = new HorizontalLayout();
        menuBarLayout.setWidth("100%");
        menuBarLayout.setHeight("100");

        // Logo
        FileResource logoResource = new FileResource(new File("img/logo.jpg"));
        Image logo = new Image(null, logoResource);
        logo.setWidth("80");
        logo.setHeight("80");


        // Context menu
        HorizontalLayout mainMenuButtonsLayout = new HorizontalLayout();
        mainMenuButtonsLayout.setSpacing(true);
        mainMenuButtonsLayout.setMargin(true);
        mainMenuButtonsLayout.setDefaultComponentAlignment(MIDDLE_CENTER);

        Button searchTrainingButton = new Button("Szukaj treningu");
        Button registerNewTrainingButton = new Button("Rejestruj trening");

        mainMenuButtonsLayout.addComponentsAndExpand(searchTrainingButton, registerNewTrainingButton);


        if (isLogged) {
            //wyloguj
            Button logoutButton = new Button("Wyloguj");
            logoutButton.addClickListener(event -> {
               authService.logOut();
            });

            //moje konto
            Button accountSettingsButton = new Button("Konto");

            mainMenuButtonsLayout.addComponentsAndExpand(logoutButton, accountSettingsButton);
        } else {
            //zaloguj
            Button loginButton = new Button("Zaloguj się");
            loginButton.addClickListener(event -> {
                page.setLocation("/auth_login");
            });

            //zarejestruj
            Button registerButton = new Button("Rejestracja");
            registerButton.addClickListener(event -> {
                page.setLocation("/register");
            });


            mainMenuButtonsLayout.addComponentsAndExpand(loginButton, registerButton);
        }

        // ...
        // Add elements together
        menuBarLayout.addComponent(logo);
        menuBarLayout.setComponentAlignment(logo, MIDDLE_LEFT);
        menuBarLayout.setExpandRatio(logo, 3);

        menuBarLayout.addComponent(mainMenuButtonsLayout);
        menuBarLayout.setExpandRatio(mainMenuButtonsLayout, 9);


        return menuBarLayout;
    }

    public Layout setupLayout(VerticalLayout rootLayout) {
        rootLayout.setDefaultComponentAlignment(MIDDLE_CENTER);
        return rootLayout;
    }


}
