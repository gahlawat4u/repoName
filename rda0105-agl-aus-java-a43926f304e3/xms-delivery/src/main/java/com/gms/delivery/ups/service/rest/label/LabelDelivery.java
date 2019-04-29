package com.gms.delivery.ups.service.rest.label;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class LabelDelivery {

	@SerializedName("LabelLinkIndicator")
    @Expose
	private String LabelLinkIndicator;

	public String getLabelLinkIndicator() {
		return LabelLinkIndicator;
	}

	public void setLabelLinkIndicator(String labelLinkIndicator) {
		LabelLinkIndicator = labelLinkIndicator;
	}
}
