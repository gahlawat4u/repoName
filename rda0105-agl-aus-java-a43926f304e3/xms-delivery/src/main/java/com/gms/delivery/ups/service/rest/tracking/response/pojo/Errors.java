package com.gms.delivery.ups.service.rest.tracking.response.pojo;

public class Errors {

	 private ErrorDetail ErrorDetail;

	    public void setErrorDetail(ErrorDetail ErrorDetail){
	        this.ErrorDetail = ErrorDetail;
	    }
	    public ErrorDetail getErrorDetail(){
	        return this.ErrorDetail;
	    }
}
