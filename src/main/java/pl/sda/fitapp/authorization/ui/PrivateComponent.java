package pl.sda.fitapp.authorization.ui;

import com.vaadin.server.VaadinSession;
import com.vaadin.ui.*;
import com.vaadin.ui.themes.ValoTheme;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.sda.fitapp.authorization.service.AuthService;
import pl.sda.fitapp.domains.dto.LoggedUserDto;

/**
 * @author Alejandro Duarte.
 */
@Service
public class PrivateComponent extends CustomComponent {

    public Layout getPrivateComponent() {
        LoggedUserDto username = (LoggedUserDto) VaadinSession.getCurrent().getAttribute(AuthService.SESSION_USERNAME);

        Label label = new Label("Welcome, " + username.getUsername() + " " + username.getUserType());
        label.addStyleName(ValoTheme.LABEL_HUGE);

        //Button button = new Button("Sign out", event -> onLogout(event));

        VerticalLayout verticalLayout = new VerticalLayout(label);
        verticalLayout.setDefaultComponentAlignment(Alignment.MIDDLE_CENTER);

        return verticalLayout;
    }

//    public void onLogout(Button.ClickEvent event) {
//        AuthService.logOut();
//    }

}
