package com.gms.delivery.ups.service.rest.transit.response.transiteTimeResponse;

import java.util.List;

public class TNTINVOICELINETOTAL {

	private List<TNTCURRENCYCODE> TNTCURRENCYCODE;

    private List<TNTMONETARYVALUE> TNTMONETARYVALUE;

    public void setTNTCURRENCYCODE(List<TNTCURRENCYCODE> TNTCURRENCYCODE){
        this.TNTCURRENCYCODE = TNTCURRENCYCODE;
    }
    public List<TNTCURRENCYCODE> getTNTCURRENCYCODE(){
        return this.TNTCURRENCYCODE;
    }
    public void setTNTMONETARYVALUE(List<TNTMONETARYVALUE> TNTMONETARYVALUE){
        this.TNTMONETARYVALUE = TNTMONETARYVALUE;
    }
    public List<TNTMONETARYVALUE> getTNTMONETARYVALUE(){
        return this.TNTMONETARYVALUE;
    }
	
}
