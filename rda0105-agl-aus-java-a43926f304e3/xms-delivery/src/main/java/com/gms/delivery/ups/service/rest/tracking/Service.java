package com.gms.delivery.ups.service.rest.tracking;

public class Service {

	private String Description;

    private String Code;

    public String getDescription ()
    {
        return Description;
    }

    public void setDescription (String Description)
    {
        this.Description = Description;
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
        return "Service [Description = "+Description+", Code = "+Code+"]";
    }
}
