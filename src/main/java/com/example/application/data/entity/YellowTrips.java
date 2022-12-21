package com.example.application.data.entity;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;

import org.hibernate.annotations.Formula;
import org.hibernate.annotations.Type;

@Entity
public class YellowTrips {
		
		@Id
		@GeneratedValue(strategy = GenerationType.IDENTITY)
    	@Column(name = "id")
    	private Long id;
		
		private long Taxi_zone;
		private String PULocationID;
		private LocalDate Pickup_date;
		private String Pickup_time;
		private int Total_trips;
		private double Trip_distance;
		private double Fare_amount;
		private double Total_amount;
		
		//private String VendorID;
		//private String DOLocationID;
		//private LocalDateTime tpep_dropoff_datetime;
		
		//private int Passenger_count;
		//private String RateCodeID;
		//private String Store_and_fwd_flag;
		//private String Payment_type;
		//private double Extra;
		//private double MTA_tax;
		//private double Improvement_surcharge;
		//private double Tip_amount;
		//private double Tolls_amount;
		//private double Congestion_Surcharge;
		//Below can be null
		//private double Airport_fee;
		
		public long getTaxi_zone() {
			return Taxi_zone;
		}
		public void setTaxi_zone(long taxi_zone) {
			Taxi_zone = taxi_zone;
		}
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

		public double getTrip_distance() {
			return Trip_distance;
		}
		public void setTrip_distance(double trip_distance) {
			Trip_distance = trip_distance;
		}
		public String getPULocationID() {
			return PULocationID;
		}
		public void setPULocationID(String pULocationID) {
			PULocationID = pULocationID;
		}

		public double getFare_amount() {
			return Fare_amount;
		}
		public void setFare_amount(double fare_amount) {
			Fare_amount = fare_amount;
		}

		public double getTotal_amount() {
			return Total_amount;
		}
		public void setTotal_amount(double total_amount) {
			Total_amount = total_amount;
		}
		
		/*public String getVendorID() {
			return VendorID;
		}
		public void setVendorID(String vendorID) {
			VendorID = vendorID;
		}
		public LocalDateTime getTpep_dropoff_datetime() {
			return tpep_dropoff_datetime;
		}
		public void setTpep_dropoff_datetime(LocalDateTime tpep_dropoff_datetime) {
			this.tpep_dropoff_datetime = tpep_dropoff_datetime;
		}
		public int getPassenger_count() {
			return Passenger_count;
		}
		public void setPassenger_count(int passenger_count) {
			Passenger_count = passenger_count;
		}
		public String getDOLocationID() {
			return DOLocationID;
		}
		public void setDOLocationID(String dOLocationID) {
			DOLocationID = dOLocationID;
		}
		public String getRateCodeID() {
			return RateCodeID;
		}
		public void setRateCodeID(String rateCodeID) {
			RateCodeID = rateCodeID;
		}
		public String getStore_and_fwd_flag() {
			return Store_and_fwd_flag;
		}
		public void setStore_and_fwd_flag(String store_and_fwd_flag) {
			Store_and_fwd_flag = store_and_fwd_flag;
		}
		public String getPayment_type() {
			return Payment_type;
		}
		public void setPayment_type(String payment_type) {
			Payment_type = payment_type;
		}
		public double getExtra() {
			return Extra;
		}
		public void setExtra(double extra) {
			Extra = extra;
		}
		public double getMTA_tax() {
			return MTA_tax;
		}
		public void setMTA_tax(double mTA_tax) {
			MTA_tax = mTA_tax;
		}
		public double getImprovement_surcharge() {
			return Improvement_surcharge;
		}
		public void setImprovement_surcharge(double improvement_surcharge) {
			Improvement_surcharge = improvement_surcharge;
		}
		public double getTip_amount() {
			return Tip_amount;
		}
		public void setTip_amount(double tip_amount) {
			Tip_amount = tip_amount;
		}
		public double getTolls_amount() {
			return Tolls_amount;
		}
		public void setTolls_amount(double tolls_amount) {
			Tolls_amount = tolls_amount;
		}
		public double getCongestion_Surcharge() {
			return Congestion_Surcharge;
		}
		public void setCongestion_Surcharge(double congestion_Surcharge) {
			Congestion_Surcharge = congestion_Surcharge;
		}
		public double getAirport_fee() {
			return Airport_fee;
		}
		public void setAirport_fee(double airport_fee) {
			Airport_fee = airport_fee;
		}*/
}
