package com.gms.delivery.ups.service.rest.shipment.pojo.response;

public class Fault
{
    private Detail detail;

    private String faultcode;

    private String faultstring;

    public Detail getDetail ()
    {
        return detail;
    }

    public void setDetail (Detail detail)
    {
        this.detail = detail;
    }

    public String getFaultcode ()
    {
        return faultcode;
    }

    public void setFaultcode (String faultcode)
    {
        this.faultcode = faultcode;
    }

    public String getFaultstring ()
    {
        return faultstring;
    }

    public void setFaultstring (String faultstring)
    {
        this.faultstring = faultstring;
    }

    @Override
    public String toString()
    {
        return "ClassPojo [detail = "+detail+", faultcode = "+faultcode+", faultstring = "+faultstring+"]";
    }
}
		