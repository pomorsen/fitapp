package pl.sda.fitapp.gui;

import com.vaadin.data.provider.ListDataProvider;
import com.vaadin.server.Page;
import com.vaadin.server.VaadinRequest;
import com.vaadin.spring.annotation.SpringUI;
import com.vaadin.ui.*;
import com.vaadin.ui.themes.ValoTheme;
import org.springframework.beans.factory.annotation.Autowired;
import pl.sda.fitapp.domains.Activity;
import pl.sda.fitapp.gui.element.CommonUIElement;
import pl.sda.fitapp.service.ActivityService;

import static com.vaadin.ui.Alignment.MIDDLE_CENTER;

@SpringUI
public class MainUI extends UI {

    @Autowired
    private CommonUIElement commonUIElement;

    @Autowired
    private ActivityService activityService;

    private VerticalLayout rootLayout = new VerticalLayout();

    @Override
    protected void init(VaadinRequest vaadinRequest) {
        commonUIElement.setupLayout(rootLayout);
        rootLayout.addComponent(commonUIElement.displayHeader(Page.getCurrent()));
        displayGrid();
        setContent(rootLayout);
    }

    private void displayGrid() {

        VerticalLayout verticalGridLayout = new VerticalLayout();
        verticalGridLayout.setWidth("80%");
        verticalGridLayout.setDefaultComponentAlignment(MIDDLE_CENTER);

        GridLayout gridLayout = new GridLayout();
        gridLayout.setSizeFull();
        gridLayout.setColumns(2);
        gridLayout.setRows(2);
        gridLayout.setSpacing(true);

        // north - west
        ListDataProvider<Activity> dataProvider = new ListDataProvider(activityService.getAllActivites());
        Grid<Activity> grid = new Grid<>();
        grid.setCaption("Twoje ostatnie treningi");
        grid.setDataProvider(dataProvider);
        grid.addColumn(Activity::getTrainer).setCaption("Trainer ");
        grid.addColumn(Activity::getPlace).setCaption("Object ");
        gridLayout.addComponent(grid,0,0);

        // north - east
        Grid<Activity> grid1 = new Grid<>();
        grid1.setCaption("Popularne dyscypliny");
        grid1.setDataProvider(dataProvider);
        grid1.addColumn(Activity::getTrainer).setCaption("Trainer ");
        grid1.addColumn(Activity::getPlace).setCaption("Object ");
        gridLayout.addComponent(grid1,1,0);

        // south - west
        Grid<Activity> grid2 = new Grid<>();
        grid2.setCaption("Popularni trenerzy");
        grid2.setDataProvider(dataProvider);
        grid2.addColumn(Activity::getTrainer).setCaption("Trainer ");
        grid2.addColumn(Activity::getPlace).setCaption("Object ");
        gridLayout.addComponent(grid2,0,1);

        // south - east
        Grid<Activity> grid3 = new Grid<>();
        grid3.setCaption("Last minute!");
        grid3.setDataProvider(dataProvider);
        grid3.addColumn(Activity::getTrainer).setCaption("Trainer ");
        grid3.addColumn(Activity::getPlace).setCaption("Object ");
        gridLayout.addComponent(grid3,1,1);

        verticalGridLayout.addComponent(gridLayout);

        rootLayout.addComponent(verticalGridLayout);
    }

}
