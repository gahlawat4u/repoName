package com.gms.delivery.ups.service.rest.transit.response.transiteTimeResponse;

import java.util.List;

public class TNTSHIPFROM {

	 private List<TNTADDRESS> TNTADDRESS;

	    public void setTNTADDRESS(List<TNTADDRESS> TNTADDRESS){
	        this.TNTADDRESS = TNTADDRESS;
	    }
	    public List<TNTADDRESS> getTNTADDRESS(){
	        return this.TNTADDRESS;
	    }
}
