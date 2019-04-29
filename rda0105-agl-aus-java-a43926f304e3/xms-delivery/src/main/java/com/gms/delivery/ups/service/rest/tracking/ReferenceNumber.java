package com.gms.delivery.ups.service.rest.tracking;

public class ReferenceNumber {

	private String Value;

    private String Code;

    public String getValue ()
    {
        return Value;
    }

    public void setValue (String Value)
    {
        this.Value = Value;
    }

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
        return "ReferenceNumber [Value = "+Value+", Code = "+Code+"]";
    }
}
