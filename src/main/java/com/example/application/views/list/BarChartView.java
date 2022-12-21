package com.example.application.views.list;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

import com.example.application.data.service.TripService;
import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.charts.Chart;
import com.vaadin.flow.component.charts.model.ChartType;
import com.vaadin.flow.component.charts.model.Configuration;
import com.vaadin.flow.component.charts.model.DataSeries;
import com.vaadin.flow.component.charts.model.DataSeriesItem;
import com.vaadin.flow.component.charts.model.LayoutDirection;
import com.vaadin.flow.component.charts.model.Legend;
import com.vaadin.flow.component.charts.model.PlotOptionsColumn;
import com.vaadin.flow.component.charts.model.YAxis;
import com.vaadin.flow.component.charts.model.style.SolidColor;
import com.vaadin.flow.component.html.Span;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;

@Route(value = "barchart", layout = MainLayout.class) 
@PageTitle("Trip Data - Barcharts View")
public class BarChartView extends VerticalLayout {
    private final TripService service;
    DateTimeFormatter monthYear = DateTimeFormatter.ofPattern("MMMM YYYY");
    PlotOptionsColumn yellowOpts = new PlotOptionsColumn();
    PlotOptionsColumn greenOpts = new PlotOptionsColumn();
    PlotOptionsColumn fhvOpts = new PlotOptionsColumn();
    
    //Inject trip service into the view as a dependency
    public BarChartView(TripService service) { 
        this.service = service;
        addClassName("dashboard-view");
        setDefaultHorizontalComponentAlignment(Alignment.CENTER); 
        //Set Plot Options to later apply to bars
        yellowOpts.setColor(SolidColor.YELLOW); 
        greenOpts.setColor(SolidColor.GREEN); 
        fhvOpts.setColor(SolidColor.BLUE); 
        add(getTripsChartHeader(), getTripsChart(),getTripDistHeader(),getTripsDistChart(),
        		getTripFareHeader(),getTripsFareChart(),getAvgTotalTripFareHeader(),getAvgTotalTripFareChart()/*,
        		getTotalTripsPerMonthHeader(),getTotalTripsPerMonthChart()*/);
    }

    //Displays the sum of all trips across all trip types in the selected date and time of day range
    private Component getTripsChartHeader() {
        Span stats = new Span(
                service.countYellowTrips() + service.countGreenTrips() + service.countFHVTrips()
                		+ " Total taxi trips between the dates of: "
                		+ monthYear.format(service.getStart()) + " and " + monthYear.format(service.getEnd()) +
                		service.getTimeList()); 
        stats.addClassNames("text-xl", "mt-m");
        return stats;
    }
    
    private Chart getTripsChart() {
        Chart chart = new Chart(ChartType.COLUMN);
        DataSeries yellowTrips = new DataSeries();
        DataSeries greenTrips = new DataSeries();
        DataSeries fhvTrips = new DataSeries();
        yellowTrips.setName("Yellow Taxi Trips");
        greenTrips.setName("Green Taxi Trips");
        fhvTrips.setName("FHV Taxi Trips");
        //Sets trips columns to their respective colors
        yellowTrips.setPlotOptions(yellowOpts);
        greenTrips.setPlotOptions(greenOpts);
        fhvTrips.setPlotOptions(fhvOpts);
        
        //Constructs data points for chart, then applies them to the chart
        yellowTrips.add(new DataSeriesItem("YellowTrip",service.countYellowTrips()));
        greenTrips.add(new DataSeriesItem("GreenTrip",service.countGreenTrips()));
        fhvTrips.add(new DataSeriesItem("FHVTrip",service.countFHVTrips()));
        chart.getConfiguration().setSeries(yellowTrips,greenTrips,fhvTrips);
        
        YAxis yaxis = new YAxis();
        yaxis.setTitle("Number of trips");
        Configuration conf = chart.getConfiguration();
        conf.addyAxis(yaxis);
        Legend legend = conf.getLegend();
        legend.getTitle().setText("Type of Trip");
        legend.setLayout(LayoutDirection.VERTICAL);
        return chart;
    }

    private Component getTripDistHeader() {
        Span stats = new Span(
                " Average trip distance (miles) "); 
        stats.addClassNames("text-xl", "mt-m");
        return stats;
    }
    
    private Chart getTripsDistChart() {
        Chart chart = new Chart(ChartType.COLUMN);
        DataSeries yellowDist = new DataSeries();
        DataSeries greenDist = new DataSeries();
        yellowDist.setName("Yellow Taxi Trip Distance (Avg)");
        greenDist.setName("Green Taxi Trip Distance (Avg)");
        yellowDist.setPlotOptions(yellowOpts);
        greenDist.setPlotOptions(greenOpts);
        
        yellowDist.add(new DataSeriesItem("YellowTrip",service.getAvgTripDistYellow()));
        greenDist.add(new DataSeriesItem("GreenTrip",service.getAvgTripDistGreen()));
        chart.getConfiguration().setSeries(yellowDist,greenDist);
        
        YAxis yaxis = new YAxis();
        yaxis.setTitle("Distance in miles");
        Configuration conf = chart.getConfiguration();
        conf.addyAxis(yaxis);
        Legend legend = conf.getLegend();
        legend.getTitle().setText("Type of Trip");
        legend.setLayout(LayoutDirection.VERTICAL);
        return chart;
    }
    
    private Component getTripFareHeader() {
        Span stats = new Span(
                " Average fare amount "); 
        stats.addClassNames("text-xl", "mt-m");
        return stats;
    }
    
    private Chart getTripsFareChart() {
        Chart chart = new Chart(ChartType.COLUMN);
        DataSeries yellowFare = new DataSeries();
        DataSeries greenFare = new DataSeries();
        yellowFare.setName("Yellow Taxi Fare Amount (Avg)");
        greenFare.setName("Green Taxi Fare Amount (Avg)");
        yellowFare.setPlotOptions(yellowOpts);
        greenFare.setPlotOptions(greenOpts);
        
        yellowFare.add(new DataSeriesItem("YellowTrip",service.getAvgFareAmtYellow()));
        greenFare.add(new DataSeriesItem("GreenTrip",service.getAvgFareAmtGreen()));
        chart.getConfiguration().setSeries(yellowFare,greenFare);
        
        YAxis yaxis = new YAxis();
        yaxis.setTitle("Fare amount (US Dollars)");
        Configuration conf = chart.getConfiguration();
        conf.addyAxis(yaxis);
        Legend legend = conf.getLegend();
        legend.getTitle().setText("Type of Trip");
        legend.setLayout(LayoutDirection.VERTICAL);
        return chart;
    }
    
    private Component getAvgTotalTripFareHeader() {
        Span stats = new Span(
                " Average total amount per trip (fees, tolls, non-cash tips, surcharges) "); 
        stats.addClassNames("text-xl", "mt-m");
        return stats;
    }
    
    private Chart getAvgTotalTripFareChart() {
        Chart chart = new Chart(ChartType.COLUMN);
        DataSeries yellowTotal = new DataSeries();
        DataSeries greenTotal = new DataSeries();
        yellowTotal.setName("Yellow Taxi Total Trip Amount (Avg)");
        greenTotal.setName("Green Taxi Total Trip Amount (Avg)");
        yellowTotal.setPlotOptions(yellowOpts);
        greenTotal.setPlotOptions(greenOpts);
        
        yellowTotal.add(new DataSeriesItem("YellowTrip",service.avgTotalAmtYellow()));
        greenTotal.add(new DataSeriesItem("GreenTrip",service.avgTotalAmtGreen()));
        chart.getConfiguration().setSeries(yellowTotal,greenTotal);
        
        YAxis yaxis = new YAxis();
        yaxis.setTitle("Fare amount (US Dollars)");
        Configuration conf = chart.getConfiguration();
        conf.addyAxis(yaxis);
        Legend legend = conf.getLegend();
        legend.getTitle().setText("Type of Trip");
        legend.setLayout(LayoutDirection.VERTICAL);
        return chart;
    }
    
    /*private Component getTotalTripsPerMonthHeader() {
        Span stats = new Span(
                " Trip distance per month "); 
        stats.addClassNames("text-xl", "mt-m");
        return stats;
    }
    
    private Chart getTotalTripsPerMonthChart() {
        Chart chart = new Chart(ChartType.COLUMN);
        DataSeries yellowTotal = new DataSeries();
        DataSeries greenTotal = new DataSeries();
        LocalDate end = service.getEnd();
        LocalDate iter = service.getStart();
        ArrayList<Integer> yellowData = new ArrayList<Integer>();
        ArrayList<Integer> greenData = new ArrayList<Integer>();
        yellowTotal.setName("Yellow Taxi Trips Per Month");
        greenTotal.setName("Green Taxi Trips Per Month");
        
        while (iter.isBefore(end)) {
        	yellowData.add(service.getYellowTripsPerMonth(iter));
            greenData.add(service.getGreenTripsPerMonth(iter));
            iter=iter.plusMonths(1);
        }
        
        Number[] yellowNum = (Number[]) yellowData.toArray();
        Number[] greenNum = (Number[]) greenData.toArray();
        yellowTotal.setData(yellowNum);
        greenTotal.setData(greenNum);
        chart.getConfiguration().setSeries(yellowTotal,greenTotal);
        
        Configuration conf = chart.getConfiguration();
        Legend legend = conf.getLegend();
        legend.getTitle().setText("Type of Trip");
        legend.setLayout(LayoutDirection.VERTICAL);
        return chart;
    }*/
}