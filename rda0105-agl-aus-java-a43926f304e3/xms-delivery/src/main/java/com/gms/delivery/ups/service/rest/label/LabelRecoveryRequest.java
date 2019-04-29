package com.gms.delivery.ups.service.rest.label;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class LabelRecoveryRequest {

	@SerializedName("LabelSpecification")
	@Expose
private LabelSpecification LabelSpecification;
	
	@SerializedName("Translate")
	@Expose
private Translate Translate;
	
	@SerializedName("TrackingNumber")
	@Expose
private String TrackingNumber;
	
	@SerializedName("labelDelivery")
	@Expose
private LabelDelivery labelDelivery;
	
	@SerializedName("referenceValues")
	@Expose
private ReferenceValues referenceValues;


public ReferenceValues getReferenceValues() {
	return referenceValues;
}

public void setReferenceValues(ReferenceValues referenceValues) {
	this.referenceValues = referenceValues;
}

public LabelDelivery getLabelDelivery() {
	return labelDelivery;
}

public void setLabelDelivery(LabelDelivery labelDelivery) {
	this.labelDelivery = labelDelivery;
}

public Translate getTranslate() {
	return Translate;
}

public void setTranslate(Translate translate) {
	Translate = translate;
}

public String getTrackingNumber() {
	return TrackingNumber;
}

public void setTrackingNumber(String trackingNumber) {
	TrackingNumber = trackingNumber;
}


public LabelSpecification getLabelSpecification() {
	return LabelSpecification;
}

public void setLabelSpecification(LabelSpecification labelSpecification) {
	LabelSpecification = labelSpecification;
}

}
