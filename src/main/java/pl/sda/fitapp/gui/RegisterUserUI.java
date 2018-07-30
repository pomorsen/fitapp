package pl.sda.fitapp.gui;

import com.vaadin.data.Binder;
import com.vaadin.data.Validator;
import com.vaadin.data.validator.EmailValidator;
import com.vaadin.data.validator.StringLengthValidator;
import com.vaadin.server.VaadinRequest;
import com.vaadin.spring.annotation.SpringUI;
import com.vaadin.ui.*;
import org.springframework.beans.factory.annotation.Autowired;
import pl.sda.fitapp.domains.GymUser;
import pl.sda.fitapp.domains.UserType;
import pl.sda.fitapp.gui.element.HeaderElement;
import pl.sda.fitapp.gui.element.UserRegistrationFormElement;
import pl.sda.fitapp.service.GymUserService;

import java.util.Objects;

import static com.vaadin.ui.Alignment.MIDDLE_CENTER;

@SpringUI(path = "/register_user")
public class RegisterUserUI extends UI {

    @Autowired
    private GymUserService gymUserService;

    @Autowired
    private HeaderElement headerElement;

    @Autowired
    private UserRegistrationFormElement userRegistrationFormElement;

    private VerticalLayout rootLayout;

    @Override
    protected void init(VaadinRequest vaadinRequest) {
        setupLayout();
        rootLayout.addComponent(headerElement.displayHeader(true));
        rootLayout.addComponent(userRegistrationFormElement.displayRegisterForm(UserType.USER));

//            addTodoList();
//            addDeleteButton();
    }

    private void setupLayout() {
        rootLayout = new VerticalLayout();
        rootLayout.setDefaultComponentAlignment(MIDDLE_CENTER);
        setContent(rootLayout);
    }





}
