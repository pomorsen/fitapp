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

@SpringUI(path = "/register")
public class RegisterUI extends UI {

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
        displayRegistrationOption();
        setContent(rootLayout);
    }

    private void displayRegistrationOption() {
        HorizontalLayout horizontalLayout = new HorizontalLayout();
        horizontalLayout.setDefaultComponentAlignment(Alignment.MIDDLE_CENTER);
        horizontalLayout.setWidth("60%");
        horizontalLayout.setCaption("Wybierz rodzaj rejestrowanego użytkownika aplikacji:");



        Button registerTrainerButton = new Button("Trener!");
        registerTrainerButton.addClickListener(event -> {
            getPage().setLocation("/register_trainer");
        });

        Button registerUserButton = new Button("Użytkownik!");
        registerUserButton.addClickListener(event -> {
            getPage().setLocation("/register_user");
        });

        horizontalLayout.addComponentsAndExpand(registerTrainerButton,registerUserButton);
        rootLayout.addComponent(horizontalLayout);
    }


}
