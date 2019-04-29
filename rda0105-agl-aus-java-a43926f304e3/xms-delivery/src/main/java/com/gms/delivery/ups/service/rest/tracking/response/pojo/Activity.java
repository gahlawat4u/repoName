package com.gms.delivery.ups.service.rest.tracking.response.pojo;

public class Activity {
	
	private ActivityLocation ActivityLocation;
	private Status Status;
	private String Date;
	private String Time;
	
	public ActivityLocation getActivityLocation() {
		return ActivityLocation;
	}
	public void setActivityLocation(ActivityLocation activityLocation) {
		ActivityLocation = activityLocation;
	}
	public Status getStatus() {
		return Status;
	}
	public void setStatus(Status status) {
		Status = status;
	}
	public String getDate() {
		return Date;
	}
	public void setDate(String date) {
		Date = date;
	}
	public String getTime() {
		return Time;
	}
	public void setTime(String time) {
		Time = time;
	}

}
