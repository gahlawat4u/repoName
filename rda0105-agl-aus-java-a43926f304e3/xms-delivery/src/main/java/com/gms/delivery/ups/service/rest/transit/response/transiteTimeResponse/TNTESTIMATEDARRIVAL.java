package com.gms.delivery.ups.service.rest.transit.response.transiteTimeResponse;

import java.util.List;

public class TNTESTIMATEDARRIVAL {

	private List<TNTARRIVAL> TNTARRIVAL;

    private List<TNTBUSINESSDAYSINTRANSIT> TNTBUSINESSDAYSINTRANSIT;

    private List<TNTPICKUP> TNTPICKUP;

    private List<TNTDAYOFWEEK> TNTDAYOFWEEK;

    private List<TNTCUSTOMERCENTERCUTOFF> TNTCUSTOMERCENTERCUTOFF;

    private List<TNTRESTDAYS> TNTRESTDAYS;

    private List<TNTTOTALTRANSITDAYS> TNTTOTALTRANSITDAYS;

    public void setTNTARRIVAL(List<TNTARRIVAL> TNTARRIVAL){
        this.TNTARRIVAL = TNTARRIVAL;
    }
    public List<TNTARRIVAL> getTNTARRIVAL(){
        return this.TNTARRIVAL;
    }
    public void setTNTBUSINESSDAYSINTRANSIT(List<TNTBUSINESSDAYSINTRANSIT> TNTBUSINESSDAYSINTRANSIT){
        this.TNTBUSINESSDAYSINTRANSIT = TNTBUSINESSDAYSINTRANSIT;
    }
    public List<TNTBUSINESSDAYSINTRANSIT> getTNTBUSINESSDAYSINTRANSIT(){
        return this.TNTBUSINESSDAYSINTRANSIT;
    }
    public void setTNTPICKUP(List<TNTPICKUP> TNTPICKUP){
        this.TNTPICKUP = TNTPICKUP;
    }
    public List<TNTPICKUP> getTNTPICKUP(){
        return this.TNTPICKUP;
    }
    public void setTNTDAYOFWEEK(List<TNTDAYOFWEEK> TNTDAYOFWEEK){
        this.TNTDAYOFWEEK = TNTDAYOFWEEK;
    }
    public List<TNTDAYOFWEEK> getTNTDAYOFWEEK(){
        return this.TNTDAYOFWEEK;
    }
    public void setTNTCUSTOMERCENTERCUTOFF(List<TNTCUSTOMERCENTERCUTOFF> TNTCUSTOMERCENTERCUTOFF){
        this.TNTCUSTOMERCENTERCUTOFF = TNTCUSTOMERCENTERCUTOFF;
    }
    public List<TNTCUSTOMERCENTERCUTOFF> getTNTCUSTOMERCENTERCUTOFF(){
        return this.TNTCUSTOMERCENTERCUTOFF;
    }
    public void setTNTRESTDAYS(List<TNTRESTDAYS> TNTRESTDAYS){
        this.TNTRESTDAYS = TNTRESTDAYS;
    }
    public List<TNTRESTDAYS> getTNTRESTDAYS(){
        return this.TNTRESTDAYS;
    }
    public void setTNTTOTALTRANSITDAYS(List<TNTTOTALTRANSITDAYS> TNTTOTALTRANSITDAYS){
        this.TNTTOTALTRANSITDAYS = TNTTOTALTRANSITDAYS;
    }
    public List<TNTTOTALTRANSITDAYS> getTNTTOTALTRANSITDAYS(){
        return this.TNTTOTALTRANSITDAYS;
    }
}