package pl.sda.fitapp.gui;

import com.vaadin.server.FileResource;
import com.vaadin.server.VaadinRequest;
import com.vaadin.spring.annotation.SpringUI;
import com.vaadin.ui.*;
import org.springframework.beans.factory.annotation.Autowired;
import pl.sda.fitapp.gui.element.HeaderElement;

import java.io.File;

import static com.vaadin.ui.Alignment.MIDDLE_CENTER;
import static com.vaadin.ui.Alignment.MIDDLE_LEFT;

@SpringUI
public class MainUI extends UI {

    @Autowired
    private HeaderElement headerElement;

    private VerticalLayout rootLayout;

    @Override
    protected void init(VaadinRequest vaadinRequest) {
        setupLayout();
        rootLayout.addComponent(headerElement.displayHeader());
//        displayWorkouts();
//            addTodoList();
//            addDeleteButton();
    }

    private void setupLayout() {
        rootLayout = new VerticalLayout();
        rootLayout.setDefaultComponentAlignment(MIDDLE_CENTER);
        setContent(rootLayout);
    }




}
