package com.example.application.views.list;

import com.example.application.data.service.TripService;
import com.vaadin.flow.component.Unit;
import com.vaadin.flow.component.html.Image;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.orderedlayout.FlexComponent.Alignment;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;

@Route(value="map", layout = MainLayout.class)
@PageTitle("NYC TLC Trip Data Zone Map")
public class MapView extends VerticalLayout {

	Image brooklynMap = new Image("images/taxi_zone_map_brooklyn.jpg", "Brooklyn");
	Image bronxMap = new Image("images/taxi_zone_map_bronx.jpg", "Bronx");
	Image manhattanMap = new Image("images/taxi_zone_map_manhattan.jpg", "Manhattan");
	Image queensMap = new Image("images/taxi_zone_map_queens.jpg", "Queens");
	Image statenIslandMap = new Image("images/taxi_zone_map_staten_island.jpg", "Staten Island");
	
	public MapView() { 
        addClassName("map-view");
        setDefaultHorizontalComponentAlignment(Alignment.CENTER); 
    	brooklynMap.setWidth(475, Unit.PIXELS);
    	bronxMap.setWidth(475, Unit.PIXELS);
    	manhattanMap.setWidth(475, Unit.PIXELS);
    	queensMap.setWidth(475, Unit.PIXELS);
    	statenIslandMap.setWidth(500, Unit.PIXELS);
    	HorizontalLayout toolbar = new HorizontalLayout(manhattanMap,queensMap);
    	HorizontalLayout toolbar2 = new HorizontalLayout(brooklynMap,bronxMap);
    	
        add(toolbar,toolbar2,statenIslandMap);
	}
}
