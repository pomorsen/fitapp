package pl.sda.fitapp.gui;

import com.vaadin.data.provider.ListDataProvider;
import com.vaadin.server.VaadinRequest;
import com.vaadin.spring.annotation.SpringUI;
import com.vaadin.ui.Grid;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.UI;
import com.vaadin.ui.VerticalLayout;
import org.springframework.beans.factory.annotation.Autowired;
import pl.sda.fitapp.domains.Activity;
import pl.sda.fitapp.gui.element.HeaderElement;
import pl.sda.fitapp.service.ActivityService;
import pl.sda.fitapp.service.TrainerService;

import static com.vaadin.ui.Alignment.MIDDLE_CENTER;

@SpringUI(path = "/activities_results")
public class ActivitiesResultsUI extends UI {

    @Autowired
    private ActivityService activityService;

    @Autowired
    private HeaderElement headerElement;

    private VerticalLayout rootLayout;

    @Override
    protected void init(VaadinRequest vaadinRequest) {
        setupLayout();
        rootLayout.addComponent(headerElement.displayHeader());
        displayActivities();

    }

    private void setupLayout() {
        rootLayout = new VerticalLayout();
        rootLayout.setDefaultComponentAlignment(MIDDLE_CENTER);
        setContent(rootLayout);
    }

    private void displayActivities() {
        HorizontalLayout horizontalGridLayout = new HorizontalLayout();
        horizontalGridLayout.setWidth("80%");
        ListDataProvider<Activity> dataProvider = new ListDataProvider(activityService.getAllActivites());

        Grid<Activity> grid = new Grid<>();
        grid.setDataProvider(dataProvider);

        // dodać wspólną klasę dla Activity i Trenera i wrzucić do ListDataProvidera
        // wtedy będzie dostęp do imienia trenera

//        grid.addColumn(trener -> trener.getTrainer().getName()).setCaption("Imie");
//        grid.addColumn(trener -> trener.getTrainer().getSurname()).setCaption("Nazwisko");

        grid.addColumn(Activity::getTrainer).setCaption("Trainer ");
        grid.addColumn(Activity::getPlace).setCaption("Object ");
        grid.addColumn(Activity::getActivityType).setCaption("Activity Type");
        grid.addColumn(Activity::getStartTime).setCaption("Start time");
        grid.addColumn(Activity::getDuration).setCaption("Duration");
        grid.addColumn(Activity::getMaxParticipants).setCaption("Max Participants");
        grid.addColumn(Activity::getPrice).setCaption("Price");
        grid.addColumn(Activity::getDescription).setCaption("Description");
        horizontalGridLayout.addComponent(grid);

        grid.setWidth("100%");

        horizontalGridLayout.setDefaultComponentAlignment(MIDDLE_CENTER);
        rootLayout.addComponent(horizontalGridLayout);
    }
}
