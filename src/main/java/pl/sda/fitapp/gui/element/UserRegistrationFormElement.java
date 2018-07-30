package pl.sda.fitapp.gui.element;

import com.vaadin.data.Binder;
import com.vaadin.data.Validator;
import com.vaadin.data.validator.EmailValidator;
import com.vaadin.data.validator.StringLengthValidator;
import com.vaadin.ui.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.sda.fitapp.domains.GymUser;
import pl.sda.fitapp.domains.Trainer;
import pl.sda.fitapp.domains.UserType;
import pl.sda.fitapp.service.GymUserService;
import pl.sda.fitapp.service.TrainerService;

import java.util.Objects;

@Service
public class UserRegistrationFormElement {

    @Autowired
    private GymUserService gymUserService;

    public Layout displayRegisterForm(UserType userType) {

        FormLayout formLayout = new FormLayout();
        formLayout.setSpacing(false);
        formLayout.setMargin(false);
        formLayout.setCaption("User Registration Form");

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

        return formLayout;
    }

}
