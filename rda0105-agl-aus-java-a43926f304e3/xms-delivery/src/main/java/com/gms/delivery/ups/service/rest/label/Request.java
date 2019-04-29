package com.gms.delivery.ups.service.rest.label;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

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
@SerializedName("TransactionReference")
@Expose
private TransactionReference TransactionReference;
@SerializedName("RequestOption")
@Expose
private String RequestOption;
}
