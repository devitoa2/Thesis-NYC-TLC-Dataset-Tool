package com.example.application.data.entity;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class FHVTrips {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Long id;
	
	//private String VendorID;
	//private LocalDateTime tpep_dropoff_datetime;
	//private int Passenger_count;
	
	private long Taxi_zone;
	private String PULocationID;
	private LocalDate Pickup_date;
	private String Pickup_time;
	private int Total_trips;
	
	//private String DOLocationID;
	
	public int getTotal_trips() {
	return Total_trips;
	}
	public void setTotal_trips(int total_trips) {
	Total_trips = total_trips;
	}
	
	public LocalDate getPickup_date() {
		return Pickup_date;
	}
	public void setPickup_date(LocalDate Pickup_date) {
		this.Pickup_date = Pickup_date;
	}
	
	public String getPickup_time() {
		return Pickup_time;
	}
	public void setPickup_time(String pickup_time) {
		Pickup_time = pickup_time;
	}
	public String getPULocationID() {
		return PULocationID;
	}
	public void setPULocationID(String pULocationID) {
		PULocationID = pULocationID;
	}
	/*public String getDOLocationID() {
		return DOLocationID;
	}
	public void setDOLocationID(String dOLocationID) {
		DOLocationID = dOLocationID;
	}*/
	public long getTaxi_zone() {
		return Taxi_zone;
	}
	public void setTaxi_zone(long taxi_zone) {
		Taxi_zone = taxi_zone;
	}
}
