package com.example.application.views.list;

import com.example.application.views.list.YellowTripView;
import com.vaadin.flow.component.applayout.AppLayout;
import com.vaadin.flow.component.applayout.DrawerToggle;
import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.orderedlayout.FlexComponent;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.HighlightConditions;
import com.vaadin.flow.router.RouterLink;
import com.vaadin.flow.server.PWA;

//Side layout in all views with navigation drawer
public class MainLayout extends AppLayout { 

    public MainLayout() {
        createHeader();
        createDrawer();
    }

    //Main header at top of page
    private void createHeader() {
        H1 logo = new H1("NYC TLC Dataset");
        logo.addClassNames("text-l", "m-m");

        HorizontalLayout header = new HorizontalLayout(
          new DrawerToggle(), 
          logo
        );

        header.setDefaultVerticalComponentAlignment(FlexComponent.Alignment.CENTER); 
        header.setWidth("100%");
        header.addClassNames("py-0", "px-m");

        addToNavbar(header); 
    }
    
    //Creates RouterLink to all views, then adds them to side navigation drawer
    private void createDrawer() {
        RouterLink yellowListLink = new RouterLink("YellowTrips", YellowTripView.class);
        RouterLink greenListLink = new RouterLink("GreenTrips", GreenTripView.class);
        RouterLink fhvListLink = new RouterLink("FHVTrips", FHVTripView.class);
        RouterLink mapListLink = new RouterLink("TaxiZoneMap", MapView.class);
        RouterLink columnChartLink = new RouterLink("BarCharts", BarChartView.class);
        RouterLink lineChartLink = new RouterLink("LineCharts", LineChart.class);
        RouterLink lineChartAllTripsLink = new RouterLink("AllTripsCharts", LineChartAllYears.class);
        yellowListLink.setHighlightCondition(HighlightConditions.sameLocation());

        addToDrawer(new VerticalLayout(
        	mapListLink,
            yellowListLink, 
            greenListLink,
            fhvListLink,
            columnChartLink,
            lineChartLink,
            lineChartAllTripsLink
        ));
    }
}