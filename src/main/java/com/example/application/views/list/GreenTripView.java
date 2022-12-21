package com.example.application.views.list;

import java.time.LocalDate;

import com.example.application.data.entity.GTripStats;
import com.example.application.data.entity.GreenTrips;
import com.example.application.data.service.TripService;
import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.checkbox.Checkbox;
import com.vaadin.flow.component.checkbox.CheckboxGroup;
import com.vaadin.flow.component.checkbox.CheckboxGroupVariant;
import com.vaadin.flow.component.datepicker.DatePicker;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.orderedlayout.FlexComponent.JustifyContentMode;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.value.ValueChangeMode;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;

@Route(value="green", layout = MainLayout.class)
@PageTitle("NYC TLC Green Taxi Trip Data")
public class GreenTripView extends VerticalLayout { 
	//Create a new grid of Yellow Trips (aggregate trips)
    Grid<GTripStats> grid = new Grid<>(GTripStats.class); 
    DatePicker startDatePicker = new DatePicker("Start date");
    DatePicker endDatePicker = new DatePicker("End date");
    Button button = new Button("Update Grid");
    CheckboxGroup<String> checkboxGroup = new CheckboxGroup<>();
    Checkbox checkbox = new Checkbox("Toggle all/none");
    TripService service;
    
    //Inject trip service into the view as a dependency
    public GreenTripView(TripService service) {
    	this.service = service;
        addClassName("green-list-view");
        setSizeFull();
        configureGrid(); 

        add(getToolbar());
        add(grid);
        updateList();
    }

    private void configureGrid() {
        grid.addClassNames("greentrips-grid");
        grid.setSizeFull();
        grid.setColumns("zone","locationID", "total_trips", "trip_distance", "fare_amount", "total_amount");
        grid.getColumnByKey("zone").setHeader("Zone ID");
        grid.getColumnByKey("locationID").setHeader("Location");
        grid.getColumnByKey("total_trips").setHeader("Total Trips");
        grid.getColumnByKey("trip_distance").setHeader("Avg Trip Distance (miles)");
        grid.getColumnByKey("fare_amount").setHeader("Avg Fare Amount");
        grid.getColumnByKey("total_amount").setHeader("Avg Total Trip Amount");
        grid.getColumns().forEach(col -> col.setAutoWidth(true)); 
    }

    private HorizontalLayout getToolbar() {
    	//Set Date Format to not use days
    	DatePicker.DatePickerI18n singleFormatI18n = new DatePicker.DatePickerI18n();
    	singleFormatI18n.setDateFormat("yyyy-MM");

    	//Set limits for selectable date range within bounds of the available dataset
    	LocalDate minDate = LocalDate.of(2015, 1, 1);
    	LocalDate maxDate = LocalDate.of(2022, 8, 31);
    	
    	//Apply date format, date range limits, default value, and set helper text
    	startDatePicker.setI18n(singleFormatI18n);
    	startDatePicker.setMin(minDate);
    	startDatePicker.setMax(maxDate);
    	startDatePicker.setValue(service.getStart());
    	startDatePicker.setHelperText("Select a month and date: yyyy-MM");
    	
    	//Apply date format, date range limits, default value, and set helper text
        endDatePicker.setI18n(singleFormatI18n);
        endDatePicker.setMin(service.getStart());
    	endDatePicker.setMax(maxDate);
    	endDatePicker.setValue(service.getEnd());
    	endDatePicker.setHelperText("Select a month and date: yyyy-MM");
    	
    	startDatePicker.addValueChangeListener(e -> endDatePicker.setMin(e.getValue()));
    	endDatePicker.addValueChangeListener(e -> startDatePicker.setMax(e.getValue()));
       
    	//Set event listeners to dynamically update date range limits as user makes selections
    	button.setHeightFull();
    	button.setWidthFull();
    	//Set event listener for updating the grid
    	button.addClickListener(clickEvent ->
    	  updateList());
    	
      	checkboxGroup.setLabel("Filter by time of day");
      	checkboxGroup.setItems("Morning", "Afternoon", 
      			"Evening","Overnight");
        //Checks whether a selection was made in other views and updates accordingly
      	if (service.getTimeList()!= null) {
        	checkboxGroup.setValue(service.getTimeList());
        	}
      	checkboxGroup.setHelperText("At least 1 checkbox must be selected");
      	checkboxGroup.addThemeVariants(CheckboxGroupVariant.LUMO_VERTICAL);
        //Event listener to select/deselect all checkboxes in the group
      	checkbox.addValueChangeListener(event -> {
    	    if (checkbox.getValue()) {
    	        checkboxGroup.select("Morning", "Afternoon", 
    	      			"Evening","Overnight");
    	    } else {
    	        checkboxGroup.deselectAll();
    	    }
    	});
    	
        //Groups components appropriately for better display
    	VerticalLayout date = new VerticalLayout(startDatePicker, endDatePicker);
    	VerticalLayout check = new VerticalLayout(checkboxGroup,checkbox);
        HorizontalLayout toolbar = new HorizontalLayout(date,check,button);
        toolbar.addClassName("toolbar");
        toolbar.setWidthFull();
        toolbar.setJustifyContentMode(JustifyContentMode.EVENLY);
        return toolbar;
    }

    //Called when the update grid button is pressed. 
    //Updates global variables set by UI components then updates the grid accordingly
    private void updateList() { 
    service.setStart(startDatePicker.getValue());
    service.setEnd(endDatePicker.getValue());	
    service.setTimeList(checkboxGroup.getSelectedItems());
    grid.setItems(service.gfilter());
}
}