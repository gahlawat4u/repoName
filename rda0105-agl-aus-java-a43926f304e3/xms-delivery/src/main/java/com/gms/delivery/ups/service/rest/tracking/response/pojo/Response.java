package com.gms.delivery.ups.service.rest.tracking.response.pojo;


public class Response {
	
	private ResponseStatus ResponseStatus;
	private TransactionReference TransactionReference;
	
	public ResponseStatus getResponseStatus() {
		return ResponseStatus;
	}
	public void setResponseStatus(ResponseStatus responseStatus) {
		ResponseStatus = responseStatus;
	}
	public TransactionReference getTransactionReference() {
		return TransactionReference;
	}
	public void setTransactionReference(TransactionReference transactionReference) {
		TransactionReference = transactionReference;
	}

}
