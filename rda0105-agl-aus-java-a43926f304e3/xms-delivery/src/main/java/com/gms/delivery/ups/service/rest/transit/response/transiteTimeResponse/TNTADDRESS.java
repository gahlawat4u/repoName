package com.gms.delivery.ups.service.rest.transit.response.transiteTimeResponse;

import java.util.List;

public class TNTADDRESS {

	private List<TNTCITY> TNTCITY;

    private List<TNTCOUNTRYCODE> TNTCOUNTRYCODE;

    private List<TNTCOUNTRY> TNTCOUNTRY;

    private List<TNTPOSTALCODE> TNTPOSTALCODE;

    public void setTNTCITY(List<TNTCITY> TNTCITY){
        this.TNTCITY = TNTCITY;
    }
    public List<TNTCITY> getTNTCITY(){
        return this.TNTCITY;
    }
    public void setTNTCOUNTRYCODE(List<TNTCOUNTRYCODE> TNTCOUNTRYCODE){
        this.TNTCOUNTRYCODE = TNTCOUNTRYCODE;
    }
    public List<TNTCOUNTRYCODE> getTNTCOUNTRYCODE(){
        return this.TNTCOUNTRYCODE;
    }
    public void setTNTCOUNTRY(List<TNTCOUNTRY> TNTCOUNTRY){
        this.TNTCOUNTRY = TNTCOUNTRY;
    }
    public List<TNTCOUNTRY> getTNTCOUNTRY(){
        return this.TNTCOUNTRY;
    }
    public void setTNTPOSTALCODE(List<TNTPOSTALCODE> TNTPOSTALCODE){
        this.TNTPOSTALCODE = TNTPOSTALCODE;
    }
    public List<TNTPOSTALCODE> getTNTPOSTALCODE(){
        return this.TNTPOSTALCODE;
    }
}
