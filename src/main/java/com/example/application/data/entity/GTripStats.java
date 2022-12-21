package com.example.application.data.entity;

public class GTripStats {
	private long zone;
	private String locationID;
	private long Total_trips;
	private double Trip_distance;
	private double Fare_amount;
	private double Total_amount;
	
	public GTripStats(long taxi_zone, String pLocationID, long total_trips, double trip_distance, double fare_amount, double total_amount) {
		zone = taxi_zone;
		locationID = pLocationID;
		Total_trips = total_trips;
		Trip_distance = trip_distance;
		Fare_amount = fare_amount;
		Total_amount = total_amount;
	}

	public long getTotal_trips() {
		return Total_trips;
	}

	public void setTotal_trips(long total_trips) {
		Total_trips = total_trips;
	}

	public double getFare_amount() {
		return Fare_amount;
	}

	public void setFare_amount(double fare_amount) {
		Fare_amount = fare_amount;
	}

	public String getLocationID() {
		return locationID;
	}

	public void setLocationID(String locationID) {
		this.locationID = locationID;
	}

	public double getTotal_amount() {
		return Total_amount;
	}

	public void setTotal_amount(double total_amount) {
		Total_amount = total_amount;
	}

	public double getTrip_distance() {
		return Trip_distance;
	}

	public void setTrip_distance(double trip_distance) {
		Trip_distance = trip_distance;
	}
	
	public long getZone() {
		return zone;
	}

	public void setZone(long zone) {
		this.zone = zone;
	}
}
