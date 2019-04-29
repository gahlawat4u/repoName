package com.gms.delivery.ups.service.rest.tracking;

public class DeliveryDateUnavailable {

	private String Description;

    private String Type;

    public String getDescription ()
    {
        return Description;
    }

    public void setDescription (String Description)
    {
        this.Description = Description;
    }

    public String getType ()
    {
        return Type;
    }

    public void setType (String Type)
    {
        this.Type = Type;
    }

    @Override
    public String toString()
    {
        return "DeliveryDateUnavailable [Description = "+Description+", Type = "+Type+"]";
    }
}
