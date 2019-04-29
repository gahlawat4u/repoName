package com.gms.delivery.ups.service.rest.label;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class LabelSpecification {
	public LabelImageFormat getLabelImageFormat() {
		return LabelImageFormat;
	}
	public void setLabelImageFormat(LabelImageFormat labelImageFormat) {
		LabelImageFormat = labelImageFormat;
	}
	public String getHTTPUserAgent() {
		return HTTPUserAgent;
	}
	public void setHTTPUserAgent(String hTTPUserAgent) {
		HTTPUserAgent = hTTPUserAgent;
	}
	public String getLabelImageFormatCode() {
		return LabelImageFormatCode;
	}
	public void setLabelImageFormatCode(String labelImageFormatCode) {
		LabelImageFormatCode = labelImageFormatCode;
	}

	@SerializedName("LabelImageFormat")
	@Expose
	private LabelImageFormat LabelImageFormat;
	@SerializedName("HTTPUserAgent")
	@Expose
	private String HTTPUserAgent;
	@SerializedName("LabelImageFormatCode")
	@Expose
	private String LabelImageFormatCode;
	

}
