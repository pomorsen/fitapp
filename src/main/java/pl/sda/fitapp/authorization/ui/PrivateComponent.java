package pl.sda.fitapp.authorization.ui;

import com.vaadin.server.VaadinSession;
import com.vaadin.ui.*;
import com.vaadin.ui.themes.ValoTheme;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.sda.fitapp.authorization.service.AuthService;

/**
 * @author Alejandro Duarte.
 */
@Service
public class PrivateComponent extends CustomComponent {
    
    public Layout getPrivateComponent() {
        String username = (String) VaadinSession.getCurrent().getAttribute(AuthService.SESSION_USERNAME);

        Label label = new Label("Welcome, " + username);
        label.addStyleName(ValoTheme.LABEL_HUGE);

        Button button = new Button("Sign out", this::onLogout);

        return new VerticalLayout(label, button);
    }

    private void onLogout(Button.ClickEvent event) {
        AuthService.logOut();
    }

}
