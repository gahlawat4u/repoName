package com.gms.delivery.ups.service.rest.transit.pojo.response;

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
