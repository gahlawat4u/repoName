package com.gms.delivery.ups.service.rest.tracking.response.pojo;

public class ErrorResponse {

	
	private String faultstring;

    private String faultcode;

    private String faultcodens;

    private Detail detail;

    public void setFaultstring(String faultstring){
        this.faultstring = faultstring;
    }
    public String getFaultstring(){
        return this.faultstring;
    }
    public void setFaultcode(String faultcode){
        this.faultcode = faultcode;
    }
    public String getFaultcode(){
        return this.faultcode;
    }
    public void setFaultcodens(String faultcodens){
        this.faultcodens = faultcodens;
    }
    public String getFaultcodens(){
        return this.faultcodens;
    }
    public void setDetail(Detail detail){
        this.detail = detail;
    }
    public Detail getDetail(){
        return this.detail;
    }
}
