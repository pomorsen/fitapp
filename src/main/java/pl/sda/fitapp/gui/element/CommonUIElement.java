package pl.sda.fitapp.gui.element;

import com.vaadin.server.FileResource;
import com.vaadin.server.Page;
import com.vaadin.server.VaadinRequest;
import com.vaadin.server.VaadinSession;
import com.vaadin.ui.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.sda.fitapp.authorization.service.AuthService;
import pl.sda.fitapp.authorization.service.UserService;
import pl.sda.fitapp.authorization.ui.PrivateComponent;
import pl.sda.fitapp.domains.UserType;
import pl.sda.fitapp.domains.dto.LoggedUserDto;
import sun.rmi.runtime.Log;

import java.io.File;

import static com.vaadin.ui.Alignment.MIDDLE_CENTER;
import static com.vaadin.ui.Alignment.MIDDLE_LEFT;
import static com.vaadin.ui.Alignment.MIDDLE_RIGHT;
import static pl.sda.fitapp.authorization.service.AuthService.SESSION_USERNAME;

@Service
public class CommonUIElement{

    @Autowired
    private AuthService authService;

    @Autowired
    private UserService userService;

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
        mainMenuButtonsLayout.setDefaultComponentAlignment(MIDDLE_LEFT);

        // Context menu
        HorizontalLayout mainMenuLoginLayout = new HorizontalLayout();
        mainMenuLoginLayout.setSpacing(true);
        mainMenuLoginLayout.setMargin(true);
        mainMenuLoginLayout.setDefaultComponentAlignment(MIDDLE_RIGHT);

        Button searchTrainingButton = new Button("Szukaj treningu");
        searchTrainingButton.addClickListener(event -> {
            page.setLocation("/activities_results");
        });

        mainMenuButtonsLayout.addComponentsAndExpand(searchTrainingButton);


        if (isLogged) {

            //if logged user is trainer
            Object attribute = VaadinSession.getCurrent().getAttribute(SESSION_USERNAME);
            if (attribute instanceof LoggedUserDto){
                LoggedUserDto loggedUserDto = (LoggedUserDto) attribute;
                if(loggedUserDto.getUserType().equals(UserType.TRAINER)){
                    Button registerNewTrainingButton = new Button("Dodaj nowy trening");
                    mainMenuButtonsLayout.addComponentsAndExpand(registerNewTrainingButton);
                }
            }


            //wyloguj
            Button logoutButton = new Button("Wyloguj");
            logoutButton.addClickListener(event -> {
               authService.logOut();
            });

            //moje konto
            Button accountSettingsButton = new Button("Konto");

            mainMenuLoginLayout.addComponentsAndExpand(logoutButton, accountSettingsButton);
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


            mainMenuLoginLayout.addComponentsAndExpand(loginButton, registerButton);
        }

        // ...
        // Add elements together
        menuBarLayout.addComponent(logo);
        menuBarLayout.setComponentAlignment(logo, MIDDLE_LEFT);
        menuBarLayout.setExpandRatio(logo, 3);

        menuBarLayout.addComponents(mainMenuButtonsLayout,mainMenuLoginLayout);
        menuBarLayout.setExpandRatio(mainMenuButtonsLayout, 6);
        menuBarLayout.setExpandRatio(mainMenuLoginLayout, 3);

        return menuBarLayout;
    }

    public Layout setupLayout(VerticalLayout rootLayout) {
        rootLayout.setDefaultComponentAlignment(MIDDLE_CENTER);
        return rootLayout;
    }


}
