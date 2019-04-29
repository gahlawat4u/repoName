package com.gms.delivery.ups.service.rest.transit.response.transiteTimeResponse;

import java.util.List;

public class COMMONRESPONSE {

	 private ATTRIBUTES ATTRIBUTES;

	    private List<COMMONRESPONSESTATUS> COMMONRESPONSESTATUS;

	    public void setATTRIBUTES(ATTRIBUTES ATTRIBUTES){
	        this.ATTRIBUTES = ATTRIBUTES;
	    }
	    public ATTRIBUTES getATTRIBUTES(){
	        return this.ATTRIBUTES;
	    }
	    public void setCOMMONRESPONSESTATUS(List<COMMONRESPONSESTATUS> COMMONRESPONSESTATUS){
	        this.COMMONRESPONSESTATUS = COMMONRESPONSESTATUS;
	    }
	    public List<COMMONRESPONSESTATUS> getCOMMONRESPONSESTATUS(){
	        return this.COMMONRESPONSESTATUS;
	    }
}
