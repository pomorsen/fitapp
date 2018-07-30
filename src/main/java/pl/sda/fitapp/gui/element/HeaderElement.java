package pl.sda.fitapp.gui.element;

import com.vaadin.server.FileResource;
import com.vaadin.ui.Button;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Image;
import com.vaadin.ui.Layout;
import org.springframework.stereotype.Service;

import java.io.File;

import static com.vaadin.ui.Alignment.MIDDLE_CENTER;
import static com.vaadin.ui.Alignment.MIDDLE_LEFT;

@Service
public class HeaderElement {


    public Layout displayHeader(boolean isLogged) {

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

            //moje konto
            Button accountSettingsButton = new Button("Konto");

            mainMenuButtonsLayout.addComponentsAndExpand(logoutButton, accountSettingsButton);
        } else {
            //zaloguj
            Button loginButoon = new Button("Zaloguj się");

            //zarejestruj
            Button registerButton = new Button("Rejestracja");

            mainMenuButtonsLayout.addComponentsAndExpand(loginButoon, registerButton);
        }

        // Add elements together
        menuBarLayout.addComponent(logo);
        menuBarLayout.setComponentAlignment(logo, MIDDLE_LEFT);
        menuBarLayout.setExpandRatio(logo, 3);

        menuBarLayout.addComponent(mainMenuButtonsLayout);
        menuBarLayout.setExpandRatio(mainMenuButtonsLayout, 9);


        return menuBarLayout;
    }


}
