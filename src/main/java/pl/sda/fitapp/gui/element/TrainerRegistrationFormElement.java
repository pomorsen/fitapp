package pl.sda.fitapp.gui.element;

import com.vaadin.data.Binder;
import com.vaadin.data.Validator;
import com.vaadin.data.validator.EmailValidator;
import com.vaadin.data.validator.StringLengthValidator;
import com.vaadin.server.Page;
import com.vaadin.ui.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.sda.fitapp.domains.GymUser;
import pl.sda.fitapp.domains.Trainer;
import pl.sda.fitapp.domains.UserType;
import pl.sda.fitapp.repositories.TrainerRepository;
import pl.sda.fitapp.service.TrainerService;

import java.util.Objects;
import java.util.concurrent.TimeUnit;

@Service
public class TrainerRegistrationFormElement {

    @Autowired
    private TrainerService trainerService;

    @Autowired
    private TrainerRepository trainerRepository;

    public Layout displayRegisterForm(UserType userType, Page page) {

        VerticalLayout mainFormLayout = new VerticalLayout();
        mainFormLayout.setDefaultComponentAlignment(Alignment.MIDDLE_CENTER);
        mainFormLayout.setWidth("25%");


        FormLayout formLayout = new FormLayout();
        formLayout.setSpacing(false);
        formLayout.setMargin(false);
        formLayout.setCaption("Trainer Registration Form");
        formLayout.setDefaultComponentAlignment(Alignment.MIDDLE_CENTER);

        TextField nameField = new TextField("First Name");
        TextField surnameField = new TextField("Last Name");
        TextField emailField = new TextField("Email");
        PasswordField passwordField = new PasswordField("Password");
        PasswordField confirmPasswordField = new PasswordField("Confirm Password");

        Binder<Trainer> binder = new Binder<>();

        binder.forField(nameField)
                .asRequired("First Name may not be empty")
                .bind(Trainer::getName, Trainer::setName);

        binder.forField(surnameField)
                .asRequired("Last Name may not be empty")
                .bind(Trainer::getSurname, Trainer::setSurname);

        binder.forField(emailField)
                .asRequired("Email may not be empty")
                .withValidator(new EmailValidator("Not a valid email address"))
                .bind(Trainer::getEmail, Trainer::setEmail);

        binder.forField(passwordField)
                .asRequired("Password may not be empty")
                .withValidator(new StringLengthValidator(
                        "Password must be at least 7 characters long", 7, null))
                .bind(Trainer::getPassword, Trainer::setPassword);

        binder.forField(confirmPasswordField)
                .asRequired("Must confirm password")
                .bind(Trainer::getPassword, (person, password) -> {
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

        binder.setBean(new Trainer());

        Button registerButton = new Button("Register");
        registerButton.setEnabled(false);
        registerButton.addClickListener(
                event -> {
                    //sprawdź czy istnieje
                    if (trainerRepository.existsTrainerByEmail(emailField.getValue())) {
                        Notification.show("Trener z adresem e-mail " + emailField.getValue() + " już istnieje!", Notification.Type.ERROR_MESSAGE);
                    } else {
                        Notification.show("Zarejestrowano!");
                        trainerService.registerTrainer(binder.getBean());
                        page.setLocation("/auth_login");
                    }
                });

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

        mainFormLayout.addComponent(formLayout);

        return mainFormLayout;
    }

}
