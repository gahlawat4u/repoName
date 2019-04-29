package com.gms.delivery.ups.service.rest.tracking;

public class Address {

	 private String CountryCode;

	    public String getCountryCode ()
	    {
	        return CountryCode;
	    }

	    public void setCountryCode (String CountryCode)
	    {
	        this.CountryCode = CountryCode;
	    }

	    @Override
	    public String toString()
	    {
	        return "Address [CountryCode = "+CountryCode+"]";
	    }
}
