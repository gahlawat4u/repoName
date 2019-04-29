package com.gms.delivery.ups.service.rest.tracking.response.pojo;

public class PrimaryErrorCode {

	private String Code;

    private String Description;

    public void setCode(String Code){
        this.Code = Code;
    }
    public String getCode(){
        return this.Code;
    }
    public void setDescription(String Description){
        this.Description = Description;
    }
    public String getDescription(){
        return this.Description;
    }
}
