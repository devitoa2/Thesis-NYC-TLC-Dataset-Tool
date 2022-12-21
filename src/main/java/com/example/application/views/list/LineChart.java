package com.example.application.views.list;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

import com.example.application.data.entity.YellowTrips;
import com.example.application.data.service.TripService;
import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.charts.Chart;
import com.vaadin.flow.component.charts.model.ChartType;
import com.vaadin.flow.component.charts.model.Configuration;
import com.vaadin.flow.component.charts.model.DataSeries;
import com.vaadin.flow.component.charts.model.DataSeriesItem;
import com.vaadin.flow.component.charts.model.LayoutDirection;
import com.vaadin.flow.component.charts.model.Legend;
import com.vaadin.flow.component.charts.model.PlotOptionsLine;
import com.vaadin.flow.component.charts.model.XAxis;
import com.vaadin.flow.component.charts.model.YAxis;
import com.vaadin.flow.component.charts.model.style.SolidColor;
import com.vaadin.flow.component.combobox.ComboBox;
import com.vaadin.flow.component.html.Span;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.orderedlayout.FlexComponent.Alignment;
import com.vaadin.flow.component.orderedlayout.FlexComponent.JustifyContentMode;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;

@Route(value = "linechart", layout = MainLayout.class) 
@PageTitle("Trip Data - Linecharts View")
public class LineChart extends VerticalLayout {
    private final TripService service;
    PlotOptionsLine yellowOpts = new PlotOptionsLine();
    PlotOptionsLine greenOpts = new PlotOptionsLine();
    PlotOptionsLine fhvOpts = new PlotOptionsLine();
    DateTimeFormatter monthYear = DateTimeFormatter.ofPattern("MMMM YYYY");
    LocalDate yearDate = LocalDate.of(2022, 1, 1);
    ComboBox<Integer> comboBox = new ComboBox<>("Year");
    Span TripsPerMonth = new Span();
    Chart TripsPerMonthChart = new Chart(ChartType.LINE);
    DataSeries yellowTripsPerMonth = new DataSeries();
    DataSeries greenTripsPerMonth = new DataSeries();
    DataSeries fhvTripsPerMonth = new DataSeries();
    int year = 2022;
    int monthEnd = 12;
    
    //Inject trip service into the view as a dependency
    public LineChart(TripService service) { 
        this.service = service;
        addClassName("linechart-view");
        setDefaultHorizontalComponentAlignment(Alignment.CENTER); 
        add(getToolbar(),getTripsPerMonthHeader(),getTripsPerMonthChart(),getTripsPerYearHeader(), getTripsPerYearChart());
    }
    
    //Updates TripsPerMonthHeader and TripsPerMonth based on selected date
    private HorizontalLayout getToolbar() {
        HorizontalLayout toolbar = new HorizontalLayout(); 
        comboBox.setItems(2015,2016,2017,2018,2019,2020,2021,2022);
        comboBox.setValue(2022);
        comboBox.addValueChangeListener(e -> TripsPerMonth.setText(" Monthly Taxi trips for the year of " + comboBox.getValue().toString()));
        comboBox.addValueChangeListener(e -> updateTripsPerMonth());
        add(comboBox);
        toolbar.addClassName("toolbar");
        toolbar.setWidthFull();
        toolbar.setJustifyContentMode(JustifyContentMode.EVENLY);
        	return toolbar;
        }
    
    private Component getTripsPerMonthHeader() {
        TripsPerMonth.setText(" Monthly Taxi trips for the year of " + comboBox.getValue().toString());
        TripsPerMonth.addClassNames("text-xl", "mt-m");
        return TripsPerMonth;
    }

    private Chart getTripsPerMonthChart() {
    	//Sets trips columns to their respective colors
        yellowTripsPerMonth.setName("Yellow Taxi Trips");
        greenTripsPerMonth.setName("Green Taxi Trips");
        fhvTripsPerMonth.setName("FHV Taxi Trips");
        yellowOpts.setColor(SolidColor.YELLOW); 
        greenOpts.setColor(SolidColor.GREEN); 
        fhvOpts.setColor(SolidColor.BLUE); 
        yellowTripsPerMonth.setPlotOptions(yellowOpts);
        greenTripsPerMonth.setPlotOptions(greenOpts);
        fhvTripsPerMonth.setPlotOptions(fhvOpts);
        
        //Sets initial chart values
        updateTripsPerMonth();
        
        XAxis xaxis = new XAxis();
        xaxis.setCategories("Jan", "Feb", "Mar", "Apr",
                "May", "Jun", "Jul", "Aug",
                "Sep", "Oct", "Nov", "Dec");
        xaxis.setTitle("Month");
        YAxis yaxis = new YAxis();
        yaxis.setTitle("Number of trips");
        
        Configuration conf = TripsPerMonthChart.getConfiguration();
        conf.addxAxis(xaxis);
        conf.addyAxis(yaxis);
        Legend legend = conf.getLegend();
        legend.getTitle().setText("Type of Trip");
        legend.setLayout(LayoutDirection.VERTICAL);
		return TripsPerMonthChart;
    }
    
    private void updateTripsPerMonth() {
    	yellowTripsPerMonth.clear();
    	greenTripsPerMonth.clear();
    	fhvTripsPerMonth.clear();
    	year = comboBox.getValue();
    	//2022's full data is not available at this time, chart is adjusted accordingly
        if (year==2022) {
        	monthEnd=8;
        } else {
        	monthEnd=12;
        }
        for (int month=1; month<=monthEnd; month++) { 
        	yellowTripsPerMonth.add(new DataSeriesItem("YellowTrips",service.countYellowTrips(year,month)));
        	greenTripsPerMonth.add(new DataSeriesItem("GreenTrips",service.countGreenTrips(year,month)));
        	fhvTripsPerMonth.add(new DataSeriesItem("FHVTrips",service.countFHVTrips(year,month)));
        }
        TripsPerMonthChart.getConfiguration().setSeries(yellowTripsPerMonth,greenTripsPerMonth,fhvTripsPerMonth);
        TripsPerMonthChart.drawChart();
    }

    private Component getTripsPerYearHeader() {
        Span stats = new Span(
                " Yearly taxi trips from 2015 to 2022 "
                		); 
        stats.addClassNames("text-xl", "mt-m");
        return stats;
    }

    private Chart getTripsPerYearChart() {
        Chart chart = new Chart(ChartType.LINE);
        DataSeries yellowTrips = new DataSeries();
        DataSeries greenTrips = new DataSeries();
        DataSeries fhvTrips = new DataSeries();
        
        yellowOpts.setColor(SolidColor.YELLOW); 
        greenOpts.setColor(SolidColor.GREEN); 
        fhvOpts.setColor(SolidColor.BLUE); 
        
        yellowTrips.setName("Yellow Taxi Trips");
        greenTrips.setName("Green Taxi Trips");
        fhvTrips.setName("FHV Taxi Trips");
        yellowTrips.setPlotOptions(yellowOpts);
        greenTrips.setPlotOptions(greenOpts);
        fhvTrips.setPlotOptions(fhvOpts);
        
        //Pulls all available yearly trip data
        for (int year=2015; year<=2022; year++) {
        yellowTrips.add(new DataSeriesItem("YellowTrip",service.countYellowTrips(year)));
        greenTrips.add(new DataSeriesItem("GreenTrip",service.countGreenTrips(year)));
        fhvTrips.add(new DataSeriesItem("FHVTrip",service.countFHVTrips(year)));
        }
        chart.getConfiguration().setSeries(yellowTrips,greenTrips,fhvTrips);
        
        XAxis xaxis = new XAxis();
        xaxis.setCategories("2015", "2016",   "2017",
                            "2018",    "2019", "2020",
                            "2021",  "2022");
        xaxis.setTitle("Year");
        YAxis yaxis = new YAxis();
        yaxis.setTitle("Number of trips");
        Configuration conf = chart.getConfiguration();
        conf.addxAxis(xaxis);
        conf.addyAxis(yaxis);
        
        Legend legend = conf.getLegend();
        legend.getTitle().setText("Type of Trip");
        legend.setLayout(LayoutDirection.VERTICAL);
        return chart;
    }
}
