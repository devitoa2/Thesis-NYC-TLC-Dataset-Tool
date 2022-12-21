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
import com.vaadin.flow.component.charts.model.XAxis;
import com.vaadin.flow.component.charts.model.YAxis;
import com.vaadin.flow.component.combobox.ComboBox;
import com.vaadin.flow.component.html.Span;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.orderedlayout.FlexComponent.Alignment;
import com.vaadin.flow.component.orderedlayout.FlexComponent.JustifyContentMode;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;

@Route(value = "allyears", layout = MainLayout.class) 
@PageTitle("Trip Data - All Years Linecharts View")
public class LineChartAllYears extends VerticalLayout {
    private final TripService service;
    
    //Inject trip service into the view as a dependency
    public LineChartAllYears(TripService service) { 
        this.service = service;
        addClassName("linechart-view");
        setDefaultHorizontalComponentAlignment(Alignment.CENTER); 
        add(getYellowTripsPerMonthByYearTitle(),getYellowTripsPerMonthByYearChart(),getGreenTripsPerMonthByYearTitle(),
        		getGreenTripsPerMonthByYearChart(),getFHVTripsPerMonthByYearTitle(),getFHVTripsPerMonthByYearChart());
    }
    
    private Component getYellowTripsPerMonthByYearTitle() {
        Span stats = new Span(
                " Monthly Yellow taxi trips from 2015 to 2022 "
                		); 
        stats.addClassNames("text-xl", "mt-m");
        return stats;
    }

    private Chart getYellowTripsPerMonthByYearChart() {
        Chart chart = new Chart(ChartType.LINE);
        DataSeries yellow2015 = new DataSeries();
        DataSeries yellow2016 = new DataSeries();
        DataSeries yellow2017 = new DataSeries();
        DataSeries yellow2018 = new DataSeries();
        DataSeries yellow2019 = new DataSeries();
        DataSeries yellow2020 = new DataSeries();
        DataSeries yellow2021 = new DataSeries();
        DataSeries yellow2022 = new DataSeries();
        yellow2015.setName("Yellow Taxi Trips in 2015");
        yellow2016.setName("Yellow Taxi Trips in 2016");
        yellow2017.setName("Yellow Taxi Trips in 2017");
        yellow2018.setName("Yellow Taxi Trips in 2018");
        yellow2019.setName("Yellow Taxi Trips in 2019");
        yellow2020.setName("Yellow Taxi Trips in 2020");
        yellow2021.setName("Yellow Taxi Trips in 2021");
        yellow2022.setName("Yellow Taxi Trips in 2022");
        
        //Pulls trip count data for all months and all years
        for (int month=1; month<=12; month++) {
        yellow2015.add(new DataSeriesItem("Yellow2015",service.countYellowTrips(2015,month)));
        yellow2016.add(new DataSeriesItem("Yellow2016",service.countYellowTrips(2016,month)));
        yellow2017.add(new DataSeriesItem("Yellow2017",service.countYellowTrips(2017,month)));
        yellow2018.add(new DataSeriesItem("Yellow2018",service.countYellowTrips(2018,month)));
        yellow2019.add(new DataSeriesItem("Yellow2019",service.countYellowTrips(2019,month)));
        yellow2020.add(new DataSeriesItem("Yellow2020",service.countYellowTrips(2020,month)));
        yellow2021.add(new DataSeriesItem("Yellow2021",service.countYellowTrips(2021,month)));
        	}
        
        //Only pulls available 2022 data 
        for (int month=1; month<=8; month++) {
        	yellow2022.add(new DataSeriesItem("Yellow2022",service.countYellowTrips(2022,month)));
        }
        chart.getConfiguration().setSeries(yellow2015,yellow2016,yellow2017,yellow2018,yellow2019,yellow2020,
        		yellow2021,yellow2022);
        XAxis xaxis = new XAxis();
        xaxis.setCategories("Jan", "Feb", "Mar", "Apr",
                            "May", "Jun", "Jul", "Aug",
                            "Sep", "Oct", "Nov", "Dec");
        xaxis.setTitle("Month");
        YAxis yaxis = new YAxis();
        yaxis.setTitle("Number of trips");
        
        Configuration conf = chart.getConfiguration();
        conf.addxAxis(xaxis);
        conf.addyAxis(yaxis);
        Legend legend = conf.getLegend();
        legend.getTitle().setText("Year of Trips");
        legend.setLayout(LayoutDirection.VERTICAL);
        return chart;
    }
    
    private Component getGreenTripsPerMonthByYearTitle() {
        Span stats = new Span(
                " Monthly Green taxi trips from 2015 to 2022 "
                		); 
        stats.addClassNames("text-xl", "mt-m");
        return stats;
    }

    private Chart getGreenTripsPerMonthByYearChart() {
        Chart chart = new Chart(ChartType.LINE);
        DataSeries green2015 = new DataSeries();
        DataSeries green2016 = new DataSeries();
        DataSeries green2017 = new DataSeries();
        DataSeries green2018 = new DataSeries();
        DataSeries green2019 = new DataSeries();
        DataSeries green2020 = new DataSeries();
        DataSeries green2021 = new DataSeries();
        DataSeries green2022 = new DataSeries();
        green2015.setName("Green Taxi Trips in 2015");
        green2016.setName("Green Taxi Trips in 2016");
        green2017.setName("Green Taxi Trips in 2017");
        green2018.setName("Green Taxi Trips in 2018");
        green2019.setName("Green Taxi Trips in 2019");
        green2020.setName("Green Taxi Trips in 2020");
        green2021.setName("Green Taxi Trips in 2021");
        green2022.setName("Green Taxi Trips in 2022");
        
        //Pulls trip count data for all months and all years
        for (int month=1; month<=12; month++) {
        green2015.add(new DataSeriesItem("Green2015",service.countGreenTrips(2015,month)));
        green2016.add(new DataSeriesItem("Green2016",service.countGreenTrips(2016,month)));
        green2017.add(new DataSeriesItem("Green2017",service.countGreenTrips(2017,month)));
        green2018.add(new DataSeriesItem("Green2018",service.countGreenTrips(2018,month)));
        green2019.add(new DataSeriesItem("Green2019",service.countGreenTrips(2019,month)));
        green2020.add(new DataSeriesItem("Green2020",service.countGreenTrips(2020,month)));
        green2021.add(new DataSeriesItem("Green2021",service.countGreenTrips(2021,month)));
        	}
        
        //Only pulls available 2022 data 
        for (int month=1; month<=8; month++) {
        	green2022.add(new DataSeriesItem("Green2022",service.countGreenTrips(2022,month)));
        }
        chart.getConfiguration().setSeries(green2015,green2016,green2017,green2018,green2019,green2020,
        		green2021,green2022);
        XAxis xaxis = new XAxis();
        xaxis.setCategories("Jan", "Feb", "Mar", "Apr",
                            "May", "Jun", "Jul", "Aug",
                            "Sep", "Oct", "Nov", "Dec");
        xaxis.setTitle("Month");
        
        YAxis yaxis = new YAxis();
        yaxis.setTitle("Number of trips");
        Configuration conf = chart.getConfiguration();
        conf.addxAxis(xaxis);
        conf.addyAxis(yaxis);
        Legend legend = conf.getLegend();
        legend.getTitle().setText("Year of Trips");
        legend.setLayout(LayoutDirection.VERTICAL);
        return chart;
    }
    
    private Component getFHVTripsPerMonthByYearTitle() {
        Span stats = new Span(
                " Monthly FHV taxi trips from 2015 to 2022 "
                		); 
        stats.addClassNames("text-xl", "mt-m");
        return stats;
    }

    private Chart getFHVTripsPerMonthByYearChart() {
        Chart chart = new Chart(ChartType.LINE);
        DataSeries FHV2015 = new DataSeries();
        DataSeries FHV2016 = new DataSeries();
        DataSeries FHV2017 = new DataSeries();
        DataSeries FHV2018 = new DataSeries();
        DataSeries FHV2019 = new DataSeries();
        DataSeries FHV2020 = new DataSeries();
        DataSeries FHV2021 = new DataSeries();
        DataSeries FHV2022 = new DataSeries();
        FHV2015.setName("FHV Taxi Trips in 2015");
        FHV2016.setName("FHV Taxi Trips in 2016");
        FHV2017.setName("FHV Taxi Trips in 2017");
        FHV2018.setName("FHV Taxi Trips in 2018");
        FHV2019.setName("FHV Taxi Trips in 2019");
        FHV2020.setName("FHV Taxi Trips in 2020");
        FHV2021.setName("FHV Taxi Trips in 2021");
        FHV2022.setName("FHV Taxi Trips in 2022");
        
        //Pulls trip count data for all months and all years
        for (int month=1; month<=12; month++) {
        FHV2015.add(new DataSeriesItem("FHV2015",service.countFHVTrips(2015,month)));
        FHV2016.add(new DataSeriesItem("FHV2016",service.countFHVTrips(2016,month)));
        FHV2017.add(new DataSeriesItem("FHV2017",service.countFHVTrips(2017,month)));
        FHV2018.add(new DataSeriesItem("FHV2018",service.countFHVTrips(2018,month)));
        FHV2019.add(new DataSeriesItem("FHV2019",service.countFHVTrips(2019,month)));
        FHV2020.add(new DataSeriesItem("FHV2020",service.countFHVTrips(2020,month)));
        FHV2021.add(new DataSeriesItem("FHV2021",service.countFHVTrips(2021,month)));
        	}
        
        //Only pulls available 2022 data 
        for (int month=1; month<=8; month++) {
        	FHV2022.add(new DataSeriesItem("FHV2022",service.countFHVTrips(2022,month)));
        }
        chart.getConfiguration().setSeries(FHV2015,FHV2016,FHV2017,FHV2018,FHV2019,FHV2020,
        		FHV2021,FHV2022);
        XAxis xaxis = new XAxis();
        xaxis.setCategories("Jan", "Feb", "Mar", "Apr",
                            "May", "Jun", "Jul", "Aug",
                            "Sep", "Oct", "Nov", "Dec");
        xaxis.setTitle("Month");
        
        YAxis yaxis = new YAxis();
        yaxis.setTitle("Number of trips");
        Configuration conf = chart.getConfiguration();
        conf.addxAxis(xaxis);
        conf.addyAxis(yaxis);
        Legend legend = conf.getLegend();
        legend.getTitle().setText("Year of Trips");
        legend.setLayout(LayoutDirection.VERTICAL);
        return chart;
    }

}
