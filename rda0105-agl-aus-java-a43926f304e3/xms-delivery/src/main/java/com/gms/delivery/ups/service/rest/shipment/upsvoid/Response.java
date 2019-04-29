package com.gms.delivery.ups.service.rest.shipment.upsvoid;

public class Response {

	
	private TransactionReference transactionReference;
	private String ResponseStatusCode;
	private String ResponseStatusDescription;
	
	
	public TransactionReference getTransactionReference() {
		return transactionReference;
	}
	public void setTransactionReference(TransactionReference transactionReference) {
		this.transactionReference = transactionReference;
	}
	public String getResponseStatusCode() {
		return ResponseStatusCode;
	}
	public void setResponseStatusCode(String responseStatusCode) {
		ResponseStatusCode = responseStatusCode;
	}
	public String getResponseStatusDescription() {
		return ResponseStatusDescription;
	}
	public void setResponseStatusDescription(String responseStatusDescription) {
		ResponseStatusDescription = responseStatusDescription;
	}
	
	
	
}
