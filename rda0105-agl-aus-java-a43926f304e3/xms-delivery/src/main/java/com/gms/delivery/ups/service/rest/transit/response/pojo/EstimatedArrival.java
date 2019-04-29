package com.gms.delivery.ups.service.rest.transit.response.pojo;

public class EstimatedArrival {
	public String getDayOfWeek() {
		return DayOfWeek;
	}
	public void setDayOfWeek(String dayOfWeek) {
		DayOfWeek = dayOfWeek;
	}
	public String getBusinessDaysInTransit() {
		return BusinessDaysInTransit;
	}
	public void setBusinessDaysInTransit(String businessDaysInTransit) {
		BusinessDaysInTransit = businessDaysInTransit;
	}
	public Arrival getArrival() {
		return Arrival;
	}
	public void setArrival(Arrival arrival) {
		Arrival = arrival;
	}
	public Pickup getPickup() {
		return Pickup;
	}
	public void setPickup(Pickup pickup) {
		Pickup = pickup;
	}
	
	
	private String DayOfWeek;
	private String BusinessDaysInTransit;
	private Arrival Arrival;
	private Pickup Pickup;
	

}
