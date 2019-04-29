package com.gms.delivery.ups.service.rest.label;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class LabelRecoveryRequestVo {
	public UPSSecurity getUPSSecurity() {
		return UPSSecurity;
	}
	public void setUPSSecurity(UPSSecurity uPSSecurity) {
		UPSSecurity = uPSSecurity;
	}
	public LabelRecoveryRequest getLabelRecoveryRequest() {
		return LabelRecoveryRequest;
	}
	public void setLabelRecoveryRequest(LabelRecoveryRequest labelRecoveryRequest) {
		LabelRecoveryRequest = labelRecoveryRequest;
	}
	@SerializedName("UPSSecurity")
	@Expose
	private UPSSecurity UPSSecurity;
	@SerializedName("LabelRecoveryRequest")
	@Expose
	private LabelRecoveryRequest LabelRecoveryRequest;
}
