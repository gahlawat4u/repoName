package com.gms.delivery.ups.service.rest.transit.pojo.request;

public class TransactionReference {
	private String CustomerContext;
	public String getCustomerContext() {
		return CustomerContext;
	}
	public void setCustomerContext(String customerContext) {
		CustomerContext = customerContext;
	}
	public String getTransactionIdentifier() {
		return TransactionIdentifier;
	}
	public void setTransactionIdentifier(String transactionIdentifier) {
		TransactionIdentifier = transactionIdentifier;
	}
	private String TransactionIdentifier;

}
