package com.gms.xms.workflow.task2.webship.voids.ups;

public class Request {

	private TransactionReference transactionReference;
	private String RequestAction;
	public TransactionReference getTransactionReference() {
		return transactionReference;
	}
	public void setTransactionReference(TransactionReference transactionReference) {
		this.transactionReference = transactionReference;
	}
	public String getRequestAction() {
		return RequestAction;
	}
	public void setRequestAction(String requestAction) {
		RequestAction = requestAction;
	}
	
}
