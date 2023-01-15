package mulak.piotr.citiesapi;

import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.dependency.StyleSheet;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.html.Label;
import com.vaadin.flow.component.icon.Icon;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.Route;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Route
@StyleSheet("/css/style.css")
public class CitiesGui extends VerticalLayout {

    private Icon iconTitle;

    private Label topLabel;
    private CityRepository cityRepository;
    private TextField textFieldId;
    private TextField textFieldShowElement;
    private Button buttonShow;
    private Button buttonShowAll;
    private Button buttonDelete;
    private Grid grid;
    private HorizontalLayout horizontalLayout;
    private HorizontalLayout topHorizontalLayout;

    @Autowired
    public CitiesGui(CityRepository cityRepository) {
        this.cityRepository = cityRepository;
        initGui();
    }

    public void buttonShowAction() {

        textFieldShowElement.setValue(cityRepository.getById(Integer.parseInt(textFieldId.getValue())).toString());
        Notification notification = new Notification("Success!", 2000);
        notification.open();
    }
    public void buttonDeleteAction() {
        cityRepository.deleteById(Long.parseLong(textFieldId.getValue()));
        Notification notification = new Notification("Item deleted", 2000);
        notification.open();
    }
    public void buttonShowAllAction(){
        removeAll();
        add(topHorizontalLayout, textFieldId, horizontalLayout, textFieldShowElement, buttonShowAll);
        List<City> cityList = (List<City>) cityRepository.findAll();
        grid = new Grid<>(City.class, false);
        grid.setAllRowsVisible(true);
        grid.addColumn("id");
        grid.addColumn("name");
        grid.addColumn("country");
        grid.addColumn( "population");
        grid.addColumn("area");
        grid.setItems(cityList);

        add(grid);
    }

    private void initGui() {
        iconTitle = new Icon(VaadinIcon.GLOBE);
        topLabel = new Label("Cities");
        textFieldId = new TextField("ID:");
        textFieldShowElement = new TextField("result:");
        buttonShow = new Button("Show", new Icon(VaadinIcon.DATABASE));
        buttonShow.addClickListener(buttonClickEvent -> buttonShowAction());
        buttonShowAll = new Button("Show All", new Icon(VaadinIcon.DATABASE));
        buttonShowAll.addClickListener(buttonClickEvent -> buttonShowAllAction());
        buttonDelete = new Button("Delete", new Icon(VaadinIcon.CLOSE));
        buttonDelete.addClickListener(buttonClickEvent -> buttonDeleteAction());
        horizontalLayout = new HorizontalLayout(buttonShow, buttonDelete);
        horizontalLayout.setPadding(true);
        topHorizontalLayout = new HorizontalLayout(iconTitle, topLabel);
        topHorizontalLayout.setPadding(true);

        add(topHorizontalLayout, textFieldId, horizontalLayout, textFieldShowElement, buttonShowAll);
    }

}
