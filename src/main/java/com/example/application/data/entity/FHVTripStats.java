package com.example.application.data.entity;

public class FHVTripStats {
	private long zone;
	private String locationID;
	private long Total_trips;
	
	public FHVTripStats(long taxi_zone, String pLocationID, long total_trips) {
		zone = taxi_zone;
		locationID = pLocationID;
		Total_trips = total_trips;
	}

	public long getTotal_trips() {
		return Total_trips;
	}

	public void setTotal_trips(long total_trips) {
		Total_trips = total_trips;
	}

	public String getLocationID() {
		return locationID;
	}

	public void setLocationID(String locationID) {
		this.locationID = locationID;
	}

	public long getZone() {
		return zone;
	}

	public void setZone(long zone) {
		this.zone = zone;
	}
}