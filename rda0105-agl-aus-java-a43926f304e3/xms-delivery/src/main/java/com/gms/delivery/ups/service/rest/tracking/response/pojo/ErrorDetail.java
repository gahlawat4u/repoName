package com.gms.delivery.ups.service.rest.tracking.response.pojo;

public class ErrorDetail {

	private String Severity;

    private PrimaryErrorCode PrimaryErrorCode;

    public void setSeverity(String Severity){
        this.Severity = Severity;
    }
    public String getSeverity(){
        return this.Severity;
    }
    public void setPrimaryErrorCode(PrimaryErrorCode PrimaryErrorCode){
        this.PrimaryErrorCode = PrimaryErrorCode;
    }
    public PrimaryErrorCode getPrimaryErrorCode(){
        return this.PrimaryErrorCode;
    }
}
