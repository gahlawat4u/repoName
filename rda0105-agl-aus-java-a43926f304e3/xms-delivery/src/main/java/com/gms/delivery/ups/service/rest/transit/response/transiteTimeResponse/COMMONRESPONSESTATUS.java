package com.gms.delivery.ups.service.rest.transit.response.transiteTimeResponse;

import java.util.List;

public class COMMONRESPONSESTATUS {

	private List<COMMONCODE> COMMONCODE;

    private List<COMMONDESCRIPTION> COMMONDESCRIPTION;

    public void setCOMMONCODE(List<COMMONCODE> COMMONCODE){
        this.COMMONCODE = COMMONCODE;
    }
    public List<COMMONCODE> getCOMMONCODE(){
        return this.COMMONCODE;
    }
    public void setCOMMONDESCRIPTION(List<COMMONDESCRIPTION> COMMONDESCRIPTION){
        this.COMMONDESCRIPTION = COMMONDESCRIPTION;
    }
    public List<COMMONDESCRIPTION> getCOMMONDESCRIPTION(){
        return this.COMMONDESCRIPTION;
    }
}
