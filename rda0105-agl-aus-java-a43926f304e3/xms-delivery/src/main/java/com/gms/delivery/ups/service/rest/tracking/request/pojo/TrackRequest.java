package com.gms.delivery.ups.service.rest.tracking.request.pojo;

public class TrackRequest {
	
	private Request Request;
	private String InquiryNumber;
	
	public Request getRequest() {
		return Request;
	}
	public void setRequest(Request request) {
		Request = request;
	}
	public String getInquiryNumber() {
		return InquiryNumber;
	}
	public void setInquiryNumber(String inquiryNumber) {
		InquiryNumber = inquiryNumber;
	}

}
