package pl.sda.fitapp.authorization.configuration;

import com.vaadin.annotations.VaadinServletConfiguration;
import com.vaadin.server.VaadinServlet;
import pl.sda.fitapp.authorization.ui.VaadinUI;

import javax.servlet.annotation.WebServlet;


public class WebConfig {
    @WebServlet(urlPatterns = "/*", asyncSupported = true)
    @VaadinServletConfiguration(ui = VaadinUI.class, productionMode = false)
    public static class VaadinUIServlet extends VaadinServlet {
    }

}
