package com.example.application.data.repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Set;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.example.application.data.entity.GTripStats;
import com.example.application.data.entity.GreenTrips;

public interface GreenTripsRepository extends JpaRepository<GreenTrips, Long> {
	@Query("SELECT sum(Total_trips) from GreenTrips G where Pickup_time IN :Pickup_time AND "
			+ "Pickup_date BETWEEN :stDate AND :edDate")
	int sumTotalTripsGreen(@Param("stDate") LocalDate stDate, @Param("edDate") LocalDate edDate, @Param("Pickup_time") Set<String> timeList);
	
	@Query("SELECT sum(Total_trips) from GreenTrips G where YEAR(Pickup_date)=:tripYear")
	int sumTotalTripsGreen(@Param("tripYear") Integer year);
	
	@Query("SELECT sum(Total_trips) from GreenTrips G where YEAR(Pickup_date)=:tripYear and MONTH(Pickup_date)=:tripMonth")
	int sumTotalTripsGreen(@Param("tripYear") Integer year,@Param("tripMonth") Integer month);
	
	@Query("SELECT sum(Total_trips) from GreenTrips Y where Pickup_time IN :Pickup_time AND "
			+ "Pickup_date IS :itDate")
	int sumTotalTripsGreenPerMonth(@Param("itDate") LocalDate itDate, @Param("Pickup_time") Set<String> timeList);
	
	@Query("SELECT avg(Trip_distance) from GreenTrips G where Pickup_time IN :Pickup_time AND "
			+ "Pickup_date BETWEEN :stDate AND :edDate")
	int avgTripDistGreen(@Param("stDate") LocalDate stDate, @Param("edDate") LocalDate edDate, @Param("Pickup_time") Set<String> timeList);
	
	@Query("SELECT avg(Fare_amount) from GreenTrips G where Pickup_time IN :Pickup_time AND "
			+ "Pickup_date BETWEEN :stDate AND :edDate")
	int avgFareAmtGreen(@Param("stDate") LocalDate stDate, @Param("edDate") LocalDate edDate, @Param("Pickup_time") Set<String> timeList);
	
	@Query("SELECT avg(Total_amount) from GreenTrips G where Pickup_time IN :Pickup_time AND "
			+ "Pickup_date BETWEEN :stDate AND :edDate")
	int avgTotalAmtGreen(@Param("stDate") LocalDate stDate, @Param("edDate") LocalDate edDate, @Param("Pickup_time") Set<String> timeList);
	
	@Query("SELECT new com.example.application.data.entity.GTripStats(G.Taxi_zone, G.PULocationID, SUM(G.Total_trips), "
			+ "ROUND(AVG(G.Trip_distance),2), ROUND(AVG(G.Fare_amount),2), ROUND(AVG(G.Total_amount),2))from GreenTrips G "
			+ "where G.Pickup_time IN :Pickup_time AND Pickup_date BETWEEN :stDate AND :edDate GROUP BY Taxi_zone, PULocationID ORDER BY CAST(Taxi_zone as integer)")
	List<GTripStats> filter(@Param("stDate") LocalDate stDate, @Param("edDate") LocalDate edDate, @Param("Pickup_time") Set<String> timeList);
}