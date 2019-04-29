package com.gms.delivery.ups.service.rest.transit.pojo;

public class Request {
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
	
	
	private String RequestOption;
	private TransactionReference TransactionReference;

}
