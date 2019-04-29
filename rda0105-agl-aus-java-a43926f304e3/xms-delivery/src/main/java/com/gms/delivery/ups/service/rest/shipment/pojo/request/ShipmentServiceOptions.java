package com.gms.delivery.ups.service.rest.shipment.pojo.request;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ShipmentServiceOptions {

	@SerializedName("InternationalForms")
    @Expose
    private InternationalForms internationalForms;
	
	@SerializedName("SaturdayDeliveryIndicator")
    @Expose
    private String saturdayDeliveryIndicator;

	@SerializedName("DeliveryConfirmation")
    @Expose
    private DeliveryConfirmation deliveryConfirmation;
	
	
	public InternationalForms getInternationalForms() {
		return internationalForms;
	}

	public void setInternationalForms(InternationalForms internationalForms) {
		this.internationalForms = internationalForms;
	}

	public DeliveryConfirmation getDeliveryConfirmation() {
		return deliveryConfirmation;
	}

	public void setDeliveryConfirmation(DeliveryConfirmation deliveryConfirmation) {
		this.deliveryConfirmation = deliveryConfirmation;
	}
	
	public String getSaturdayDeliveryIndicator() {
		return saturdayDeliveryIndicator;
	}

	public void setSaturdayDeliveryIndicator(String saturdayDeliveryIndicator) {
		this.saturdayDeliveryIndicator = saturdayDeliveryIndicator;
	}

}
