package pl.sda.fitapp.gui;

import com.vaadin.server.Page;
import com.vaadin.server.VaadinRequest;
import com.vaadin.spring.annotation.SpringUI;
import com.vaadin.ui.*;
import org.springframework.beans.factory.annotation.Autowired;
import pl.sda.fitapp.domains.UserType;
import pl.sda.fitapp.gui.element.CommonUIElement;
import pl.sda.fitapp.gui.element.UserRegistrationFormElement;
import pl.sda.fitapp.service.GymUserService;

import static com.vaadin.ui.Alignment.MIDDLE_CENTER;

@SpringUI(path = "/register_user")
public class RegisterUserUI extends UI {

    @Autowired
    private GymUserService gymUserService;

    @Autowired
    private CommonUIElement commonUIElement;

    @Autowired
    private UserRegistrationFormElement userRegistrationFormElement;

    private VerticalLayout rootLayout = new VerticalLayout();

    @Override
    protected void init(VaadinRequest vaadinRequest) {
        commonUIElement.setupLayout(rootLayout);
        rootLayout.addComponent(commonUIElement.displayHeader(Page.getCurrent()));
        rootLayout.addComponent(userRegistrationFormElement.displayRegisterForm(UserType.USER, Page.getCurrent()));
        setContent(rootLayout);
    }







}
