package com.gms.delivery.ups.service.rest.transit.response.transiteTimeResponse;

import java.util.List;

public class TNTUNITOFMEASUREMENT {
	 private List<TNTCODE> TNTCODE;

	    public void setTNTCODE(List<TNTCODE> TNTCODE){
	        this.TNTCODE = TNTCODE;
	    }
	    public List<TNTCODE> getTNTCODE(){
	        return this.TNTCODE;
	    }
}
