package com.gms.delivery.ups.service.rest.tracking.request.pojo;


public class Request {
	
	private String RequestOption;
	private TransactionReference TransactionReference;
	
	public String getRequestOption() {
		return RequestOption;
	}
	public void setRequestOption(String requestOption) {
		RequestOption = requestOption;
	}
	public TransactionReference getTransactionReference() {
		return TransactionReference;
	}
	public void setTransactionReference(TransactionReference transactionReference) {
		TransactionReference = transactionReference;
	}
	
	

}
