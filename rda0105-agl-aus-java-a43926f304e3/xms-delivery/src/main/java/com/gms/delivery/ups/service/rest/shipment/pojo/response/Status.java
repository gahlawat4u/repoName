package com.gms.delivery.ups.service.rest.shipment.pojo.response;

public class Status {
	
	 private StatusType StatusType;

	    private StatusCode StatusCode;

	    public void setStatusType(StatusType StatusType){
	        this.StatusType = StatusType;
	    }
	    public StatusType getStatusType(){
	        return this.StatusType;
	    }
	    public void setStatusCode(StatusCode StatusCode){
	        this.StatusCode = StatusCode;
	    }
	    public StatusCode getStatusCode(){
	        return this.StatusCode;
	    }

}
