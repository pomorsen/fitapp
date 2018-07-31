package pl.sda.fitapp.authorization.ui;

import com.vaadin.server.Page;
import com.vaadin.server.VaadinRequest;
import com.vaadin.spring.annotation.SpringUI;
import com.vaadin.ui.UI;
import com.vaadin.ui.VerticalLayout;
import org.springframework.beans.factory.annotation.Autowired;
import pl.sda.fitapp.authorization.service.AuthService;
import pl.sda.fitapp.gui.element.CommonUIElement;

import static com.vaadin.ui.Alignment.MIDDLE_CENTER;

/**
 * @author Alejandro Duarte.
 */
@SpringUI(path = "/auth_login")
public class VaadinUI extends UI {


    @Autowired
    private PublicComponent publicComponent;

    @Autowired
    private PrivateComponent privateComponent;

    @Autowired
    private CommonUIElement commonUIElement;

    @Autowired
    private AuthService authService;

    private VerticalLayout rootLayout;

    @Override
    protected void init(VaadinRequest vaadinRequest) {
        setupLayout();
        rootLayout.addComponent(commonUIElement.displayHeader(Page.getCurrent()));
        if (!authService.isAuthenticated()) {
            showPublicComponent();
        } else {
            showPrivateComponent();
        }
    }

    public void showPublicComponent() {
        rootLayout.addComponent(publicComponent.getPublicComponent());
    }

    public void showPrivateComponent() {
        rootLayout.addComponent(privateComponent.getPrivateComponent());
    }

    private void setupLayout() {
        rootLayout = new VerticalLayout();
        rootLayout.setDefaultComponentAlignment(MIDDLE_CENTER);
        setContent(rootLayout);
    }

}
