package com.gms.delivery.ups.service.rest.transit.response.transiteTimeResponse;

import java.util.List;

public class ENVELOPE {

	 private ATTRIBUTES ATTRIBUTES;

	    private List<SOAPENVHEADER> SOAPENVHEADER;

	    private List<SOAPENVBODY> SOAPENVBODY;

	    public void setATTRIBUTES(ATTRIBUTES ATTRIBUTES){
	        this.ATTRIBUTES = ATTRIBUTES;
	    }
	    public ATTRIBUTES getATTRIBUTES(){
	        return this.ATTRIBUTES;
	    }
	    public void setSOAPENVHEADER(List<SOAPENVHEADER> SOAPENVHEADER){
	        this.SOAPENVHEADER = SOAPENVHEADER;
	    }
	    public List<SOAPENVHEADER> getSOAPENVHEADER(){
	        return this.SOAPENVHEADER;
	    }
	    public void setSOAPENVBODY(List<SOAPENVBODY> SOAPENVBODY){
	        this.SOAPENVBODY = SOAPENVBODY;
	    }
	    public List<SOAPENVBODY> getSOAPENVBODY(){
	        return this.SOAPENVBODY;
	    }
}
