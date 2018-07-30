package pl.sda.fitapp.gui;

import com.vaadin.server.FileResource;
import com.vaadin.server.VaadinRequest;
import com.vaadin.spring.annotation.SpringUI;
import com.vaadin.ui.*;
import org.springframework.beans.factory.annotation.Autowired;
import pl.sda.fitapp.gui.element.HeaderElement;

import java.io.File;

import static com.vaadin.ui.Alignment.MIDDLE_CENTER;
import static com.vaadin.ui.Alignment.MIDDLE_LEFT;

@SpringUI
public class MainUI extends UI {

    @Autowired
    private HeaderElement headerElement;

    private VerticalLayout rootLayout;

    @Override
    protected void init(VaadinRequest vaadinRequest) {
        setupLayout();
//        displayHeader()
        rootLayout.addComponent(headerElement.displayHeader());
//        displayWorkouts();
//            addTodoList();
//            addDeleteButton();
    }

    private void setupLayout() {
        rootLayout = new VerticalLayout();
        rootLayout.setDefaultComponentAlignment(MIDDLE_CENTER);
        setContent(rootLayout);
    }

    private void displayHeader() {

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

        // Add elements together
        menuBarLayout.addComponent(logo);
        menuBarLayout.setComponentAlignment(logo, MIDDLE_LEFT);
        menuBarLayout.setExpandRatio(logo, 3);

        menuBarLayout.addComponent(mainMenuButtonsLayout);
        menuBarLayout.setExpandRatio(mainMenuButtonsLayout, 9);

        // Save on Root Layout
        rootLayout.addComponent(menuBarLayout);

    }



}
