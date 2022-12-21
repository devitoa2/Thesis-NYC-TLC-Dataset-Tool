package com.example.application.data.repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Set;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.example.application.data.entity.FHVTripStats;
import com.example.application.data.entity.FHVTrips;
import com.example.application.data.entity.GTripStats;

public interface FHVTripsRepository extends JpaRepository<FHVTrips, Long>{
	@Query("SELECT sum(Total_trips) from FHVTrips F where Pickup_time IN :Pickup_time AND "
			+ "Pickup_date BETWEEN :stDate AND :edDate")
	int sumTotalTripsFHV(@Param("stDate") LocalDate stDate, @Param("edDate") LocalDate edDate, @Param("Pickup_time") Set<String> timeList);
	
	@Query("SELECT sum(Total_trips) from FHVTrips F where YEAR(Pickup_date)=:tripYear")
	int sumTotalTripsFHV(@Param("tripYear") Integer year);
	
	@Query("SELECT sum(Total_trips) from FHVTrips F where YEAR(Pickup_date)=:tripYear and MONTH(Pickup_date)=:tripMonth")
	int sumTotalTripsFHV(@Param("tripYear") Integer year,@Param("tripMonth") Integer month);
	
	@Query("SELECT new com.example.application.data.entity.FHVTripStats(F.Taxi_zone, F.PULocationID, SUM(F.Total_trips)) "
			+ "from FHVTrips F where F.Pickup_time IN :Pickup_time AND "
			+ "F.Pickup_date BETWEEN :stDate AND :edDate GROUP BY Taxi_zone, PULocationID ORDER BY CAST(F.Taxi_zone as integer)")
	List<FHVTripStats> filter(@Param("stDate") LocalDate stDate, @Param("edDate") LocalDate edDate, @Param("Pickup_time") Set<String> timeList);
}
