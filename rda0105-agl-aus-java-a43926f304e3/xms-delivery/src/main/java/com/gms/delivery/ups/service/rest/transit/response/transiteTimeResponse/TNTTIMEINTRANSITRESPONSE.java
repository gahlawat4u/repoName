package com.gms.delivery.ups.service.rest.transit.response.transiteTimeResponse;

import java.util.List;

public class TNTTIMEINTRANSITRESPONSE {

	 private ATTRIBUTES ATTRIBUTES;

	    private List<COMMONRESPONSE> COMMONRESPONSE;

	    private List<TNTTRANSITRESPONSE> TNTTRANSITRESPONSE;

	    public void setATTRIBUTES(ATTRIBUTES ATTRIBUTES){
	        this.ATTRIBUTES = ATTRIBUTES;
	    }
	    public ATTRIBUTES getATTRIBUTES(){
	        return this.ATTRIBUTES;
	    }
	    public void setCOMMONRESPONSE(List<COMMONRESPONSE> COMMONRESPONSE){
	        this.COMMONRESPONSE = COMMONRESPONSE;
	    }
	    public List<COMMONRESPONSE> getCOMMONRESPONSE(){
	        return this.COMMONRESPONSE;
	    }
	    public void setTNTTRANSITRESPONSE(List<TNTTRANSITRESPONSE> TNTTRANSITRESPONSE){
	        this.TNTTRANSITRESPONSE = TNTTRANSITRESPONSE;
	    }
	    public List<TNTTRANSITRESPONSE> getTNTTRANSITRESPONSE(){
	        return this.TNTTRANSITRESPONSE;
	    }
}
