package com.gms.delivery.ups.service.rest.transit.response.pojo;

public class Response {
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
	private ResponseStatus ResponseStatus;
	private TransactionReference TransactionReference;

}
