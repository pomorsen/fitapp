package pl.sda.fitapp.authorization.ui;

import com.vaadin.event.ShortcutAction;
import com.vaadin.ui.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.sda.fitapp.authorization.service.AuthService;
import pl.sda.fitapp.authorization.ui.VaadinUI;
import pl.sda.fitapp.domains.UserType;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author Alejandro Duarte.
 */
public class PublicComponent extends CustomComponent {

    @Autowired
    private AuthService authService;

    public PublicComponent() {

        List<String> userTypes = Arrays.asList("Użytkownik", "Trener");
        RadioButtonGroup<String> userType = new RadioButtonGroup<>("Wybierz rodzaj użytkownika", userTypes);

        userType.addValueChangeListener(event -> Notification.show("Value changed:",
                String.valueOf(event.getValue()),
                Notification.Type.TRAY_NOTIFICATION));
        //

        TextField username = new TextField("Username");
        username.focus();

        PasswordField password = new PasswordField("Password");

        CheckBox rememberMe = new CheckBox("Remember me");

        Button button = new Button("Login", e -> onLogin(username.getValue(), password.getValue(), rememberMe.getValue(), userType.getValue()));
        button.setClickShortcut(ShortcutAction.KeyCode.ENTER);

        FormLayout formLayout = new FormLayout(username, password, button, rememberMe);
        formLayout.setSizeUndefined();

        VerticalLayout layout = new VerticalLayout(formLayout);
        layout.setSizeFull();
        layout.setComponentAlignment(formLayout, Alignment.MIDDLE_CENTER);

        setCompositionRoot(layout);
        setSizeFull();
    }

    private void onLogin(String username, String password, boolean rememberMe, String userType) {
        if (authService.login(username, password, rememberMe, userType) > 0) {
            VaadinUI ui = (VaadinUI) UI.getCurrent();
            ui.showPrivateComponent();
        } else {
            Notification.show("Invalid credentials (for demo use: admin/password)", Notification.Type.ERROR_MESSAGE);
        }
    }

}
