package com.gms.delivery.ups.service.rest.tracking;

public class ShipTo {

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
        return "ShipTo [Address = "+Address+"]";
    }
}
