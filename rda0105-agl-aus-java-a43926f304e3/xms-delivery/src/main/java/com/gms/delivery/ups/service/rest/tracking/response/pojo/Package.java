package com.gms.delivery.ups.service.rest.tracking.response.pojo;

import java.util.List;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown=true)
public class Package {
	
	private String TrackingNumber;
	private PackageServiceOption PackageServiceOption;
	private List<Activity> Activity;
	private PackageWeight PackageWeight;
	private Message Message;
	private ReferenceNumber ReferenceNumber;
	
	public String getTrackingNumber() {
		return TrackingNumber;
	}
	public void setTrackingNumber(String trackingNumber) {
		TrackingNumber = trackingNumber;
	}
	public PackageServiceOption getPackageServiceOption() {
		return PackageServiceOption;
	}
	public void setPackageServiceOption(PackageServiceOption packageServiceOption) {
		PackageServiceOption = packageServiceOption;
	}
	public List<Activity> getActivity() {
		return Activity;
	}
	public void setActivity(List<Activity> activity) {
		Activity = activity;
	}
	public PackageWeight getPackageWeight() {
		return PackageWeight;
	}
	public void setPackageWeight(PackageWeight packageWeight) {
		PackageWeight = packageWeight;
	}
	public Message getMessage() {
		return Message;
	}
	public void setMessage(Message message) {
		Message = message;
	}
	public ReferenceNumber getReferenceNumber() {
		return ReferenceNumber;
	}
	public void setReferenceNumber(ReferenceNumber referenceNumber) {
		ReferenceNumber = referenceNumber;
	}

}
