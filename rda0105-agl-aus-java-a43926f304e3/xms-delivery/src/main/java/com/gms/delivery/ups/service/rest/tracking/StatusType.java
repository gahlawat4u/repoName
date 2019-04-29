package com.gms.delivery.ups.service.rest.tracking;

public class StatusType {

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
        return "StatusType [Description = "+Description+", Code = "+Code+"]";
    }
}
