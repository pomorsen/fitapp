package pl.sda.fitapp.authorization.ui;

import com.vaadin.server.VaadinRequest;
import com.vaadin.ui.UI;
import pl.sda.fitapp.authorization.service.AuthService;

/**
 * @author Alejandro Duarte.
 */
public class VaadinUI extends UI {

    @Override
    protected void init(VaadinRequest vaadinRequest) {
        if (!AuthService.isAuthenticated()) {
            showPublicComponent();
        } else {
            showPrivateComponent();
        }
    }

    public void showPublicComponent() {
        setContent(new PublicComponent());
    }

    public void showPrivateComponent() {
        setContent(new PrivateComponent());
    }

}
