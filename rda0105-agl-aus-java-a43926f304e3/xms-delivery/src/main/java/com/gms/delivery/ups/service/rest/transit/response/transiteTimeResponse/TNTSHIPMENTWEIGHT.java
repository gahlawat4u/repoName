package com.gms.delivery.ups.service.rest.transit.response.transiteTimeResponse;

import java.util.List;

public class TNTSHIPMENTWEIGHT {

	private List<TNTUNITOFMEASUREMENT> TNTUNITOFMEASUREMENT;

    private List<TNTWEIGHT> TNTWEIGHT;

    public void setTNTUNITOFMEASUREMENT(List<TNTUNITOFMEASUREMENT> TNTUNITOFMEASUREMENT){
        this.TNTUNITOFMEASUREMENT = TNTUNITOFMEASUREMENT;
    }
    public List<TNTUNITOFMEASUREMENT> getTNTUNITOFMEASUREMENT(){
        return this.TNTUNITOFMEASUREMENT;
    }
    public void setTNTWEIGHT(List<TNTWEIGHT> TNTWEIGHT){
        this.TNTWEIGHT = TNTWEIGHT;
    }
    public List<TNTWEIGHT> getTNTWEIGHT(){
        return this.TNTWEIGHT;
    }
}
