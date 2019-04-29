package com.gms.delivery.ups.service.rest.transit.response.transiteTimeResponse;

import java.util.List;

public class TNTSERVICE {

	 private List<TNTCODE> TNTCODE;

	    private List<TNTDESCRIPTION> TNTDESCRIPTION;

	    public void setTNTCODE(List<TNTCODE> TNTCODE){
	        this.TNTCODE = TNTCODE;
	    }
	    public List<TNTCODE> getTNTCODE(){
	        return this.TNTCODE;
	    }
	    public void setTNTDESCRIPTION(List<TNTDESCRIPTION> TNTDESCRIPTION){
	        this.TNTDESCRIPTION = TNTDESCRIPTION;
	    }
	    public List<TNTDESCRIPTION> getTNTDESCRIPTION(){
	        return this.TNTDESCRIPTION;
	    }
}
