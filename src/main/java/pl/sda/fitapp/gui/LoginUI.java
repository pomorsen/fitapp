package pl.sda.fitapp.gui;

import com.vaadin.data.Binder;
import com.vaadin.data.Validator;
import com.vaadin.data.validator.EmailValidator;
import com.vaadin.data.validator.StringLengthValidator;
import com.vaadin.server.FileResource;
import com.vaadin.server.VaadinRequest;
import com.vaadin.spring.annotation.SpringUI;
import com.vaadin.ui.*;
import org.springframework.beans.factory.annotation.Autowired;
import pl.sda.fitapp.domains.GymUser;
import pl.sda.fitapp.domains.dto.GymUserLoginDto;
import pl.sda.fitapp.service.GymUserService;

import java.io.File;
import java.util.Objects;

import static com.vaadin.ui.Alignment.MIDDLE_CENTER;
import static com.vaadin.ui.Alignment.MIDDLE_LEFT;

@SpringUI(path = "/login")
public class LoginUI extends UI {

    @Autowired
    private GymUserService gymUserService;

    private VerticalLayout rootLayout;

    @Override
    protected void init(VaadinRequest vaadinRequest) {
        setupLayout();
        displayHeader();
        displayLoginForm();
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

    private void displayLoginForm() {

        FormLayout formLayout = new FormLayout();
        formLayout.setSpacing(false);
        formLayout.setMargin(false);
        formLayout.setCaption("Login Form");

        TextField emailField = new TextField("Email");
        PasswordField passwordField = new PasswordField("Password");

        Binder<GymUserLoginDto> binder = new Binder<>();

        binder.forField(emailField)
                .asRequired("Email may not be empty")
                .withValidator(new EmailValidator("Not a valid email address"))
                .bind(GymUserLoginDto::getEmail, GymUserLoginDto::setEmail);

        binder.forField(passwordField)
                .asRequired("Password may not be empty")
                .bind(GymUserLoginDto::getPassword, GymUserLoginDto::setPassword);

        Label validationStatus = new Label();
        binder.setStatusLabel(validationStatus);

        binder.setBean(new GymUserLoginDto());

        Button loginButton = new Button("Login");
        loginButton.addClickListener(
                event -> {
                    String caption = "";
                    Notification.Type notificationType = Notification.Type.TRAY_NOTIFICATION;
                    if(gymUserService.loginUser(binder.getBean()).isPresent()){
                        caption = "Welcome!";
                        //getUI().getPage().open("/user-data?userId=" + String.valueOf(addPerson),null);
                    } else {
                        caption = "Couldn't login with provided login and password!";
                        notificationType = Notification.Type.ERROR_MESSAGE;
                    }
                    gymUserService.loginUser(binder.getBean());
                    Notification.show(caption, notificationType);
                });


        //Add to form layout
        formLayout.addComponents(
                emailField,
                passwordField,
                loginButton
        );

        rootLayout.addComponent(formLayout);




    }
//    }    private void displayLoginForm() {
//
//        // Create the custom layout and set it as a component in
//
//        VerticalLayout verticalLayout = new VerticalLayout();
//
//        TextField emailTextField = new TextField();
//        emailTextField.setPlaceholder("e-mail");
//        emailTextField.setCaption("e-mail");
//
//        TextField textFieldSurname = new TextField();
//        textFieldSurname.setPlaceholder("Surname");
//
//        Button buttonAddPerson = new Button("Click");
//        buttonAddPerson = new Button("Click");
//
//        verticalLayout.addComponents(emailTextField,textFieldSurname)
//        rootLayout.addComponent(loginLayout);
//
//    }


}
