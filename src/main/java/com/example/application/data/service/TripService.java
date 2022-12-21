package com.example.application.data.service;

import org.springframework.stereotype.Service;

import com.example.application.data.entity.FHVTripStats;
import com.example.application.data.entity.GTripStats;
import com.example.application.data.entity.GreenTrips;
import com.example.application.data.entity.YTripStats;
import com.example.application.data.entity.YellowTrips;
import com.example.application.data.repository.*;

import java.time.LocalDate;
import java.time.Period;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Set;

@Service 
public class TripService {

	    private final YellowTripsRepository YellowTripsRepository;
	    private final GreenTripsRepository GreenTripsRepository;
	    private final FHVTripsRepository FHVTripsRepository;
	    
	    //Global variables
	    private LocalDate Start = LocalDate.of(2022,1,1);
    	private LocalDate End = LocalDate.of(2022,8,1);
		private Set<String> timeList;
		
		//Getters and Setters
		public Set<String> getTimeList() {
			return timeList;
		}

		public void setTimeList(Set<String> timeList) {
			this.timeList = timeList;
		}

		public LocalDate getStart() {
			return Start;
		}

		public void setStart(LocalDate start) {
			Start = start;
		}

		public LocalDate getEnd() {
			return End;
		}

		public void setEnd(LocalDate end) {
			End = end;
		}

		//Injecting repositories into service to facilitate business logic in queries
		public TripService(YellowTripsRepository YellowTripsRepository,
	    		GreenTripsRepository GreenTripsRepository,FHVTripsRepository FHVTripsRepository) { 
	        this.YellowTripsRepository = YellowTripsRepository;
	        this.GreenTripsRepository = GreenTripsRepository;
	        this.FHVTripsRepository = FHVTripsRepository;
	    }
		
		//Methods to get queried trip records to fill the grids in the view
		public List<YTripStats> yfilter() {
	    	return YellowTripsRepository.filter(Start, End, timeList);
	    }
		
		public List<GTripStats> gfilter() {
	    	return GreenTripsRepository.filter(Start, End, timeList);
	    }
		
		public List<FHVTripStats> ffilter() {
	    	return FHVTripsRepository.filter(Start, End, timeList);
	    }
	    
		//Methods for Bar Charts View
		//Chart 1
	    public int countYellowTrips() {
	        return YellowTripsRepository.sumTotalTripsYellow(Start, End, timeList);
	    }
	    
	    public int countGreenTrips() {
	        return GreenTripsRepository.sumTotalTripsGreen(Start, End, timeList);
	    }
	    
	    public int countFHVTrips() {
	        return FHVTripsRepository.sumTotalTripsFHV(Start, End, timeList);
	    }
	    
	    //Chart 2
	    public int getAvgTripDistYellow() {
	        return YellowTripsRepository.avgTripDistYellow(Start, End, timeList);
	    }
	    
	    public int getAvgTripDistGreen() {
	        return GreenTripsRepository.avgTripDistGreen(Start, End, timeList);
	    }
	    
	    //Chart 3
	    public int getAvgFareAmtYellow() {
	        return YellowTripsRepository.avgFareAmtYellow(Start, End, timeList);
	    }
	    
	    public int getAvgFareAmtGreen() {
	        return GreenTripsRepository.avgFareAmtGreen(Start, End, timeList);
	    }
	    
	    //Chart 4
	    public int avgTotalAmtYellow() {
	        return YellowTripsRepository.avgTotalAmtYellow(Start, End, timeList);
	    }
	    
	    public int avgTotalAmtGreen() {
	        return GreenTripsRepository.avgTotalAmtGreen(Start, End, timeList);
	    }
	    
	    //Methods for Monthly Trips for Selected Year Line Chart
	    public int countYellowTrips(Integer year) {
	        return YellowTripsRepository.sumTotalTripsYellow(year);
	    }
	    
	    public int countGreenTrips(Integer year) {
	        return GreenTripsRepository.sumTotalTripsGreen(year);
	    }
	    
	    public int countFHVTrips(Integer year) {
	        return FHVTripsRepository.sumTotalTripsFHV(year);
	    }
	    
	    //Methods for Yearly Trips Line Chart and All Years Line Charts
	    public int countYellowTrips(Integer year, Integer month) {
	        return YellowTripsRepository.sumTotalTripsYellow(year,month);
	    }
	    
	    public int countGreenTrips(Integer year, Integer month) {
	        return GreenTripsRepository.sumTotalTripsGreen(year,month);
	    }	    
	    
	    public int countFHVTrips(Integer year, Integer month) {
	        return FHVTripsRepository.sumTotalTripsFHV(year,month);
	    }
	    
	    
		
		
		
		//Antiquated
		
		public List<YellowTrips> findAllTrips(LocalDate stDate, LocalDate edDate) {
			return YellowTripsRepository.search(stDate, edDate);
		}
    
		public List<GreenTrips> findAllGreenTrips() {
			return GreenTripsRepository.findAll();
		}
		
	    public int getYellowTripsPerMonth(LocalDate iter) {
	        return YellowTripsRepository.sumTotalTripsYellowPerMonth(iter, timeList);
	    }

	    public int getGreenTripsPerMonth(LocalDate iter) {
	        return GreenTripsRepository.sumTotalTripsGreenPerMonth(iter, timeList);
	    }
	    
	    //Beta variables, not active
    	private Period diff = Period.between(
                Start,
                End);
    	private int monthDiff = diff.getMonths();
    	
		public int getMonthDiff() {
			return monthDiff;
		}
	}
