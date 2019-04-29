package com.gms.delivery.ups.service.rest.transit.response.pojo;

public class Services {

	 private String PICKUPTIME;

	    private String PICKUPDATE;

	    private String ARRIVALDATE;

	    private String SERVICEDETAIL;

	    private String ARRIVALTIME;

	    private String TOTALTRANSITDAYS;

	    private String SERVICECODE;

	    private String DAYOFWEEK;

	    private String BUSINESSDAYSINTRANSIT;

	    public String getPICKUPTIME ()
	    {
	        return PICKUPTIME;
	    }

	    public void setPICKUPTIME (String PICKUPTIME)
	    {
	        this.PICKUPTIME = PICKUPTIME;
	    }

	    public String getPICKUPDATE ()
	    {
	        return PICKUPDATE;
	    }

	    public void setPICKUPDATE (String PICKUPDATE)
	    {
	        this.PICKUPDATE = PICKUPDATE;
	    }

	    public String getARRIVALDATE ()
	    {
	        return ARRIVALDATE;
	    }

	    public void setARRIVALDATE (String ARRIVALDATE)
	    {
	        this.ARRIVALDATE = ARRIVALDATE;
	    }

	    public String getSERVICEDETAIL ()
	    {
	        return SERVICEDETAIL;
	    }

	    public void setSERVICEDETAIL (String SERVICEDETAIL)
	    {
	        this.SERVICEDETAIL = SERVICEDETAIL;
	    }

	    public String getARRIVALTIME ()
	    {
	        return ARRIVALTIME;
	    }

	    public void setARRIVALTIME (String ARRIVALTIME)
	    {
	        this.ARRIVALTIME = ARRIVALTIME;
	    }

	    public String getTOTALTRANSITDAYS ()
	    {
	        return TOTALTRANSITDAYS;
	    }

	    public void setTOTALTRANSITDAYS (String TOTALTRANSITDAYS)
	    {
	        this.TOTALTRANSITDAYS = TOTALTRANSITDAYS;
	    }

	    public String getSERVICECODE ()
	    {
	        return SERVICECODE;
	    }

	    public void setSERVICECODE (String SERVICECODE)
	    {
	        this.SERVICECODE = SERVICECODE;
	    }

	    public String getDAYOFWEEK ()
	    {
	        return DAYOFWEEK;
	    }

	    public void setDAYOFWEEK (String DAYOFWEEK)
	    {
	        this.DAYOFWEEK = DAYOFWEEK;
	    }

	    public String getBUSINESSDAYSINTRANSIT ()
	    {
	        return BUSINESSDAYSINTRANSIT;
	    }

	    public void setBUSINESSDAYSINTRANSIT (String BUSINESSDAYSINTRANSIT)
	    {
	        this.BUSINESSDAYSINTRANSIT = BUSINESSDAYSINTRANSIT;
	    }

	    @Override
	    public String toString()
	    {
	        return "ClassPojo [PICKUPTIME = "+PICKUPTIME+", PICKUPDATE = "+PICKUPDATE+", ARRIVALDATE = "+ARRIVALDATE+", SERVICEDETAIL = "+SERVICEDETAIL+", ARRIVALTIME = "+ARRIVALTIME+", TOTALTRANSITDAYS = "+TOTALTRANSITDAYS+", SERVICECODE = "+SERVICECODE+", DAYOFWEEK = "+DAYOFWEEK+", BUSINESSDAYSINTRANSIT = "+BUSINESSDAYSINTRANSIT+"]";
	    }
}
