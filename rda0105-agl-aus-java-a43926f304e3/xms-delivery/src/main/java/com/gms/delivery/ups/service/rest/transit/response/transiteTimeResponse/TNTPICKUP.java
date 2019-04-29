package com.gms.delivery.ups.service.rest.transit.response.transiteTimeResponse;

import java.util.List;

public class TNTPICKUP {
	 private List<TNTDATE> TNTDATE;

	    private List<TNTTIME> TNTTIME;

	    public void setTNTDATE(List<TNTDATE> TNTDATE){
	        this.TNTDATE = TNTDATE;
	    }
	    public List<TNTDATE> getTNTDATE(){
	        return this.TNTDATE;
	    }
	    public void setTNTTIME(List<TNTTIME> TNTTIME){
	        this.TNTTIME = TNTTIME;
	    }
	    public List<TNTTIME> getTNTTIME(){
	        return this.TNTTIME;
	    }
}
