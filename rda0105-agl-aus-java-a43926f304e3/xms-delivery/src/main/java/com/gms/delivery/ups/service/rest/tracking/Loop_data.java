package com.gms.delivery.ups.service.rest.tracking;

public class Loop_data {

    private String Time;

    private String Date;

    private String Location;

    private String CountryCode;

    private String City;

    public String getTime ()
    {
        return Time;
    }

    public void setTime (String Time)
    {
        this.Time = Time;
    }

    public String getDate ()
    {
        return Date;
    }

    public void setDate (String Date)
    {
        this.Date = Date;
    }

    public String getLocation ()
    {
        return Location;
    }

    public void setLocation (String Location)
    {
        this.Location = Location;
    }

    public String getCountryCode ()
    {
        return CountryCode;
    }

    public void setCountryCode (String CountryCode)
    {
        this.CountryCode = CountryCode;
    }

    public String getCity ()
    {
        return City;
    }

    public void setCity (String City)
    {
        this.City = City;
    }

    @Override
    public String toString()
    {
        return "ClassPojo [Time = "+Time+", Date = "+Date+", Location = "+Location+", CountryCode = "+CountryCode+", City = "+City+"]";
    }

	
}
