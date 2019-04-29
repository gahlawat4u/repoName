package com.gms.delivery.ups.service.rest.transit.response.pojo;

public class TransactionReference {
private String CustomerContext;
private String TransactionIdentifier;


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


}
