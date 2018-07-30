package pl.sda.fitapp.gui;

import com.vaadin.data.provider.ListDataProvider;
import com.vaadin.server.VaadinRequest;
import com.vaadin.spring.annotation.SpringUI;
import com.vaadin.ui.Grid;
import com.vaadin.ui.UI;
import org.springframework.beans.factory.annotation.Autowired;
import pl.sda.fitapp.domains.Activity;
import pl.sda.fitapp.service.ActivityService;
import pl.sda.fitapp.service.TrainerService;

@SpringUI(path = "/activites_results")
public class ActivitiesResults extends UI {

    @Autowired
    private ActivityService activityService;

    @Autowired
    private TrainerService trainerService;

    @Override
    protected void init(VaadinRequest vaadinRequest) {
        ListDataProvider<Activity> dataProvider = new ListDataProvider(activityService.getAllActivites());

        Grid<Activity> grid = new Grid<>();
        grid.setDataProvider(dataProvider);

        // dodać wspólną metodę dla Activity i Trenera i wrzucić do ListDataProvidera
        // wtedy będzie dostęp do imienia trenera

        grid.addColumn(Activity::getTrainerId).setCaption("Trainer ID");
        grid.addColumn(Activity::getObjectId).setCaption("Object ID");
        setContent(grid);
    }
}
