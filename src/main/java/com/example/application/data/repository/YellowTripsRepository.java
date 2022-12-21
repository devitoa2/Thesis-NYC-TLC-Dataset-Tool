package com.example.application.data.repository;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.UUID;
import com.example.application.data.entity.YTripStats;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.example.application.data.entity.YellowTrips;

public interface YellowTripsRepository extends JpaRepository<YellowTrips, Long> {
	@Query("SELECT sum(Total_trips) from YellowTrips Y where Pickup_time IN :Pickup_time AND "
			+ "Pickup_date BETWEEN :stDate AND :edDate")
	int sumTotalTripsYellow(@Param("stDate") LocalDate stDate, @Param("edDate") LocalDate edDate, @Param("Pickup_time") Set<String> timeList);
	
	@Query("SELECT sum(Total_trips) from YellowTrips Y where YEAR(Pickup_date)=:tripYear")
	int sumTotalTripsYellow(@Param("tripYear") Integer year);
	
	@Query("SELECT sum(Total_trips) from YellowTrips Y where YEAR(Pickup_date)=:tripYear and MONTH(Pickup_date)=:tripMonth")
	int sumTotalTripsYellow(@Param("tripYear") Integer year,@Param("tripMonth") Integer month);
	
	@Query("SELECT avg(Trip_distance) from YellowTrips Y where Pickup_time IN :Pickup_time AND "
			+ "Pickup_date BETWEEN :stDate AND :edDate")
	int avgTripDistYellow(@Param("stDate") LocalDate stDate, @Param("edDate") LocalDate edDate, @Param("Pickup_time") Set<String> timeList);
	
	@Query("SELECT avg(Fare_amount) from YellowTrips Y where Pickup_time IN :Pickup_time AND "
			+ "Pickup_date BETWEEN :stDate AND :edDate")
	int avgFareAmtYellow(@Param("stDate") LocalDate stDate, @Param("edDate") LocalDate edDate, @Param("Pickup_time") Set<String> timeList);
	
	@Query("SELECT avg(Total_amount) from YellowTrips Y where Pickup_time IN :Pickup_time AND "
			+ "Pickup_date BETWEEN :stDate AND :edDate")
	int avgTotalAmtYellow(@Param("stDate") LocalDate stDate, @Param("edDate") LocalDate edDate, @Param("Pickup_time") Set<String> timeList);
	
	@Query("SELECT new com.example.application.data.entity.YTripStats(Y.Taxi_zone, Y.PULocationID, SUM(Y.Total_trips), "
			+ "ROUND(AVG(Y.Trip_distance),2), ROUND(AVG(Y.Fare_amount),2), ROUND(AVG(Y.Total_amount),2))from YellowTrips Y "
			+ "where Y.Pickup_time IN :Pickup_time AND Pickup_date BETWEEN :stDate AND :edDate GROUP BY Taxi_zone, PULocationID ORDER BY CAST(Taxi_zone as integer)")
	List<YTripStats> filter(@Param("stDate") LocalDate stDate, @Param("edDate") LocalDate edDate, @Param("Pickup_time") Set<String> timeList);

	
	
	//Antiquated
	@Query("select Y from YellowTrips Y " +
		      "where Pickup_date BETWEEN :stDate AND :edDate ") 
		    List<YellowTrips> search(@Param("stDate") LocalDate stDate, @Param("edDate") LocalDate edDate);
	
	@Query("SELECT sum(Total_trips) from YellowTrips Y where Pickup_time IN :Pickup_time AND "
			+ "Pickup_date IS :itDate")
	int sumTotalTripsYellowPerMonth(@Param("itDate") LocalDate itDate, @Param("Pickup_time") Set<String> timeList);
}