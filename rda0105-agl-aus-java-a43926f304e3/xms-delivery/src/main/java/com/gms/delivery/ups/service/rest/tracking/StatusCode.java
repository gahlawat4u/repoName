package com.gms.delivery.ups.service.rest.tracking;

public class StatusCode {

	private String Code;

    public String getCode ()
    {
        return Code;
    }

    public void setCode (String Code)
    {
        this.Code = Code;
    }

    @Override
    public String toString()
    {
        return "StatusCode [Code = "+Code+"]";
    }
}
