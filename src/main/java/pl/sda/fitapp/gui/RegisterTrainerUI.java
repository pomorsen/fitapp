package pl.sda.fitapp.gui;

import com.vaadin.server.VaadinRequest;
import com.vaadin.spring.annotation.SpringUI;
import com.vaadin.ui.*;
import org.springframework.beans.factory.annotation.Autowired;
import pl.sda.fitapp.domains.UserType;
import pl.sda.fitapp.gui.element.HeaderElement;
import pl.sda.fitapp.gui.element.TrainerRegistrationFormElement;
import pl.sda.fitapp.service.TrainerService;

import static com.vaadin.ui.Alignment.MIDDLE_CENTER;

@SpringUI(path = "/register_trainer")
public class RegisterTrainerUI extends UI {

    @Autowired
    private TrainerService trainerService;

    @Autowired
    private HeaderElement headerElement;

    @Autowired
    private TrainerRegistrationFormElement userRegistrationFormElement;


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
