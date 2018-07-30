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
import pl.sda.fitapp.service.GymUserService;

import java.io.File;
import java.util.Objects;

import static com.vaadin.ui.Alignment.MIDDLE_CENTER;
import static com.vaadin.ui.Alignment.MIDDLE_LEFT;

@SpringUI(path = "/register")
public class RegisterUI extends UI {

    @Autowired
    private GymUserService gymUserService;


    private VerticalLayout rootLayout;

    @Override
    protected void init(VaadinRequest vaadinRequest) {
        setupLayout();
        displayHeader();
        displayRegisterForm();
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

    private void displayRegisterForm() {

        FormLayout formLayout = new FormLayout();
        formLayout.setSpacing(false);
        formLayout.setMargin(false);
        formLayout.setCaption("Registration Form");

        TextField nameField = new TextField("First Name");
        TextField surnameField = new TextField("Last Name");
        TextField emailField = new TextField("Email");
        PasswordField passwordField = new PasswordField("Password");
        PasswordField confirmPasswordField = new PasswordField("Confirm Password");



        Binder<GymUser> binder = new Binder<>();

        binder.forField(nameField)
                .asRequired("First Name may not be empty")
                .bind(GymUser::getName, GymUser::setName);

        binder.forField(surnameField)
                .asRequired("Last Name may not be empty")
                .bind(GymUser::getSurname, GymUser::setSurname);

        binder.forField(emailField)
                .asRequired("Email may not be empty")
                .withValidator(new EmailValidator("Not a valid email address"))
                .bind(GymUser::getEmail, GymUser::setEmail);

        binder.forField(passwordField)
                .asRequired("Password may not be empty")
                .withValidator(new StringLengthValidator(
                        "Password must be at least 7 characters long", 7, null))
                .bind(GymUser::getPassword, GymUser::setPassword);

        binder.forField(confirmPasswordField)
                .asRequired("Must confirm password")
                .bind(GymUser::getPassword, (person, password) -> {
                });

        binder.withValidator(Validator.from(account -> {
            if (passwordField.isEmpty() || confirmPasswordField.isEmpty()) {
                return true;
            } else {
                return Objects.equals(passwordField.getValue(),
                        confirmPasswordField.getValue());
            }
        }, "Entered password and confirmation password must match"));

        Label validationStatus = new Label();
        binder.setStatusLabel(validationStatus);

        binder.setBean(new GymUser());

        Button registerButton = new Button("Register");
        registerButton.setEnabled(false);
        registerButton.addClickListener(
                event -> gymUserService.registerUser(binder.getBean()));

        binder.addStatusChangeListener(
                event -> registerButton.setEnabled(binder.isValid()));


        //Add to form layout
        formLayout.addComponents(
                nameField,
                surnameField,
                emailField,
                passwordField,
                confirmPasswordField,
                registerButton
        );

        rootLayout.addComponent(formLayout);




    }
//    }    private void displayRegisterForm() {
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
