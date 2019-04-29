
package com.gms.delivery.ups.service.rest.shipment.pojo.response;

import com.google.gson.annotations.Expose;

public class Response {

	
	//code by rakesh sir
	
	
	 private ResponseStatus ResponseStatus;

	  public ResponseStatus getResponseStatus() { return this.ResponseStatus; }

	  public void setResponseStatus(ResponseStatus ResponseStatus) { this.ResponseStatus = ResponseStatus; }

	  private TransactionReference TransactionReference;

	  public TransactionReference getTransactionReference() { return this.TransactionReference; }

	  public void setTransactionReference(TransactionReference TransactionReference) { this.TransactionReference = TransactionReference; }

	
	//code by shahabuddin
	/*
	 private TransactionReference TransactionReference;

	    private String ResponseStatusCode;

	    private String ResponseStatusDescription;

	    public void setTransactionReference(TransactionReference TransactionReference){
	        this.TransactionReference = TransactionReference;
	    }
	    public TransactionReference getTransactionReference(){
	        return this.TransactionReference;
	    }
	    public void setResponseStatusCode(String ResponseStatusCode){
	        this.ResponseStatusCode = ResponseStatusCode;
	    }
	    public String getResponseStatusCode(){
	        return this.ResponseStatusCode;
	    }
	    public void setResponseStatusDescription(String ResponseStatusDescription){
	        this.ResponseStatusDescription = ResponseStatusDescription;
	    }
	    public String getResponseStatusDescription(){
	        return this.ResponseStatusDescription;
	    }*/
	
}
