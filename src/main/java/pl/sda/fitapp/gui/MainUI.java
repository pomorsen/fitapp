package pl.sda.fitapp.gui;

import com.vaadin.server.Page;
import com.vaadin.server.VaadinRequest;
import com.vaadin.spring.annotation.SpringUI;
import com.vaadin.ui.*;
import org.springframework.beans.factory.annotation.Autowired;
import pl.sda.fitapp.gui.element.CommonUIElement;

import static com.vaadin.ui.Alignment.MIDDLE_CENTER;

@SpringUI
public class MainUI extends UI {

    @Autowired
    private CommonUIElement commonUIElement;

    private VerticalLayout rootLayout = new VerticalLayout();

    @Override
    protected void init(VaadinRequest vaadinRequest) {
        commonUIElement.setupLayout(rootLayout);
        rootLayout.addComponent(commonUIElement.displayHeader(Page.getCurrent()));
        setContent(rootLayout);
    }

}
