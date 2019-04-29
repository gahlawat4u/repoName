package com.gms.delivery.ups.service.rest.tracking;

public class ActivityLocation {

	private Address Address;

    public Address getAddress ()
    {
        return Address;
    }

    public void setAddress (Address Address)
    {
        this.Address = Address;
    }

    @Override
    public String toString()
    {
        return "ActivityLocation [Address = "+Address+"]";
    }
}
